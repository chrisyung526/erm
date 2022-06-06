package cn.com.tcc.ofa.erm.service.impl;

import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.mapper.ErmLocalTitleMapper;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalTitleDto;
import cn.com.tcc.ofa.erm.model.po.ErmLocalTitle;
import cn.com.tcc.ofa.erm.model.po.ErmTitleHolding;
import cn.com.tcc.ofa.erm.service.ErmLocalTitleService;
import cn.com.tcc.ofa.erm.service.ErmTitleHoldingService;
import cn.com.tcc.ofa.erm.utils.MarcUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <p>
 * 本地资源清单表 服务实现类
 * </p>
 *
 * @author hsw
 * @since 2022-05-05
 */
@Service
public class ErmLocalTitleServiceImpl extends ServiceImpl<ErmLocalTitleMapper, ErmLocalTitle> implements ErmLocalTitleService {

    @Autowired
    ErmTitleHoldingService ermTitleHoldingService;

    @Override
    public IPage<ErmLocalTitle> pageList(ErmLocalTitleDto dto, PageDto pageDto) {
        Integer filterRule = dto.getFilterRule();
        String filterValue = dto.getFilterValue();
        LambdaQueryWrapper<ErmLocalTitle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(dto.getLanguage()), ErmLocalTitle::getLanguage, dto.getLanguage())
                .eq(StrUtil.isNotBlank(dto.getType()), ErmLocalTitle::getType, dto.getType())
                .eq(ErmLocalTitle::getDatabaseId, dto.getDatabaseId());

        if (filterRule != null) {
            wrapper.likeRight(filterRule == 1, ErmLocalTitle::getTitleName, filterValue)
                    .eq(filterRule == 2, ErmLocalTitle::getTitleName, filterValue)
                    .like(filterRule == 3, ErmLocalTitle::getTitleName, filterValue)
                    .eq(filterRule == 4, ErmLocalTitle::getIdentifier, filterValue);
        }
        if (dto.getStatus() != null) {
            wrapper.eq(ErmLocalTitle::getStatus, dto.getStatus());
        }

        String sortColumns = dto.getSortColumns();
        if (StrUtil.isNotBlank(sortColumns)) {
            wrapper.orderBy("titleName".equals(sortColumns), !dto.isSortDesc(), ErmLocalTitle::getTitleName)
                    .orderBy("identifier".equals(sortColumns), !dto.isSortDesc(), ErmLocalTitle::getIdentifier)
                    .orderBy("type".equals(sortColumns), !dto.isSortDesc(), ErmLocalTitle::getType)
                    .orderBy("language".equals(sortColumns), !dto.isSortDesc(), ErmLocalTitle::getLanguage)
                    .orderBy("publishDate".equals(sortColumns), !dto.isSortDesc(), ErmLocalTitle::getPublishDate);
        }

        if (pageDto == null) {
            List<ErmLocalTitle> list = list(wrapper);
            return new Page<ErmLocalTitle>().setRecords(list);
        } else {
            return page(new Page<>(pageDto.getCurrent(), pageDto.getSize()), wrapper);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadTitle(MultipartFile file, Long databaseId) {
        try {
            String suffix = FileUtil.getSuffix(file.getOriginalFilename());
            if ("mrc".equals(suffix)) {
                jxMarc(file);
                return false;
            }
            ArrayList<Map<Integer, String>> list = new ArrayList<>();
            EasyExcel.read(file.getInputStream()).sheet().registerReadListener(new AnalysisEventListener<Map<Integer, String>>() {
                @Override
                public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {
                    list.add(map);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                }
            }).doRead();
            if (CollectionUtils.isNotEmpty(list)) {
                String[] props = {"titleName", "type", "status", "identifier", "noOfCopies", "isOnlineViewLicense",
                        "eIsbn", "publisher", "language", "frequency", "author", "editor", "edition",
                        "pages", "count", "publishDate", "customDate", "videoYear", "playingTime", "outputUrl",
                        "customUrl", "outputCoverageStartDate", "outputCoverageEndDate", "customCoverageStartDate",
                        "customCoverageEndDate", "description", "publicNote", "display", "subject", "sourceId"};
                List<ErmLocalTitle> list2 = new ArrayList<>();
                for (Map<Integer, String> map : list) {
                    Set<Integer> set = map.keySet();
                    Map<String, String> map2 = new HashMap<>();
                    for (Integer key : set) {
                        map2.put(props[key], map.get(key));
                    }
                    ErmLocalTitle ermLocalTitle = BeanUtil.toBean(map2, ErmLocalTitle.class);
                    ermLocalTitle.setDatabaseId(databaseId);
                    list2.add(ermLocalTitle);
                }
                return list2.size() > 0 && saveBatch(list2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveErmLocalTitle(ErmLocalTitle ermLocalTitle) {
        List<ErmTitleHolding> ermTitleHoldings = ermLocalTitle.getErmTitleHoldings();
        boolean b = save(ermLocalTitle);
        if (b) {
            if (CollUtil.isNotEmpty(ermTitleHoldings)) {
                for (ErmTitleHolding ermTitleHolding : ermTitleHoldings) {
                    ermTitleHolding.setTitleId(ermLocalTitle.getId());
                }
                ermTitleHoldingService.saveBatch(ermTitleHoldings);
            }
        }
        return b;
    }

    /**
     * 解析marc记录
     *
     * @param file
     */
    private void jxMarc(MultipartFile file) {
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            ArrayList<String> list = new ArrayList<>();
            read = new InputStreamReader(
                    new ByteArrayInputStream(file.getBytes()), "gbk");
            bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                list.add(lineTxt);
            }

            // TODO: 2022/5/11 解析marc记录
            Map<String, String> map = MarcUtil.jxMarc(list.get(0));


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
