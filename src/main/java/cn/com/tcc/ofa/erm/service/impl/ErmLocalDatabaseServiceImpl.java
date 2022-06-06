package cn.com.tcc.ofa.erm.service.impl;

import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.mapper.ErmLocalDatabaseMapper;
import cn.com.tcc.ofa.erm.model.dto.ErmDatabaseLicenseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalDatabaseDto;
import cn.com.tcc.ofa.erm.model.po.ErmLocalDatabase;
import cn.com.tcc.ofa.erm.model.po.ErmLocalTitle;
import cn.com.tcc.ofa.erm.model.vo.ErmDatabaseLicenseVo;
import cn.com.tcc.ofa.erm.model.vo.ErmLocalDatabaseVo;
import cn.com.tcc.ofa.erm.service.ErmDatabaseCollectionService;
import cn.com.tcc.ofa.erm.service.ErmLocalDatabaseService;
import cn.com.tcc.ofa.erm.service.ErmLocalTitleService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hsw
 * @date 2022/4/29 10:46
 */
@Service
public class ErmLocalDatabaseServiceImpl extends ServiceImpl<ErmLocalDatabaseMapper, ErmLocalDatabase> implements ErmLocalDatabaseService {
    @Autowired
    private ErmLocalDatabaseMapper ermLocalDatabaseMapper;

    @Autowired
    private ErmLocalTitleService ermLocalTitleService;

    @Autowired
    private ErmDatabaseCollectionService ermDatabaseCollectionService;

    @Override
    public IPage pageList(ErmLocalDatabaseDto dto, PageDto pageDto) {
        // TODO: 2022/5/18 分组管理
        // IPage<ErmLocalDatabaseVo> page = ermLocalDatabaseMapper.pageList(new Page<>(pageDto.getCurrent(), pageDto.getSize()), dto);
        // List<ErmLocalDatabaseVo> list = page.getRecords();
        // if (CollUtil.isNotEmpty(list)) {
        //     List<Long> databaseIds = new ArrayList<>();
        //     for (ErmLocalDatabaseVo ErmLocalDatabaseVo : list) {
        //         databaseIds.add(ErmLocalDatabaseVo.getId());
        //     }
        //     List<ErmDatabaseCollectionVo> list1 = ermDatabaseCollectionService.getCollectionName(databaseIds);
        // }
        if (pageDto == null) {
            List<ErmLocalDatabaseVo> ermLocalDatabaseVos = ermLocalDatabaseMapper.pageList(dto);
            setTitles(ermLocalDatabaseVos);
            return new Page<ErmLocalDatabaseVo>().setRecords(ermLocalDatabaseVos);
        } else {
            IPage<ErmLocalDatabaseVo> page = ermLocalDatabaseMapper.pageList(new Page<>(pageDto.getCurrent(), pageDto.getSize()), dto);
            List<ErmLocalDatabaseVo> ermLocalDatabaseVos = setTitles(page.getRecords());
            page.setRecords(ermLocalDatabaseVos);
            return page;
        }
    }

    /**
     * 设置title总量
     * @param ermLocalDatabaseVos
     * @return
     */
    private List<ErmLocalDatabaseVo> setTitles(List<ErmLocalDatabaseVo> ermLocalDatabaseVos) {
        if (CollUtil.isNotEmpty(ermLocalDatabaseVos)) {
            for (ErmLocalDatabaseVo ermLocalDatabaseVo : ermLocalDatabaseVos) {
                LambdaQueryWrapper<ErmLocalTitle> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(ErmLocalTitle::getDatabaseId, ermLocalDatabaseVo.getId());
                int count = ermLocalTitleService.count(wrapper);
                ermLocalDatabaseVo.setTitles(count);
            }
        }
        return ermLocalDatabaseVos;
    }

    @Override
    public IPage<ErmDatabaseLicenseVo> pageLicenses(Page<ErmDatabaseLicenseVo> page, ErmDatabaseLicenseDto dto) {
        return ermLocalDatabaseMapper.pageLicenses(page, dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeDatabase(List<Long> ids) {
        // 已发布和待审批的不能删除
        LambdaQueryWrapper<ErmLocalDatabase> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(w -> w.eq(ErmLocalDatabase::getApprovalStatus, 2)
                .or()
                .eq(ErmLocalDatabase::getApprovalStatus, 3))
                .in(ErmLocalDatabase::getId, ids);
        List<ErmLocalDatabase> list = list(wrapper);
        if (CollUtil.isNotEmpty(list)) {
            throw new RestException(RestExceptionEnum.DELETE_DATABASE_ERROR_CODE);
        }
        // TODO: 2022/5/12 删除资源库逻辑
        boolean b = removeByIds(ids);
        return b;
    }

    @Override
    public ErmLocalDatabase getDetail(Long id) {
        ErmLocalDatabase ermLocalDatabase = getById(id);
        QueryWrapper<ErmLocalTitle> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) totalCount", "type").lambda().eq(ErmLocalTitle::getDatabaseId, id).groupBy(ErmLocalTitle::getType);
        List<ErmLocalTitle> list = ermLocalTitleService.list(wrapper);
        for (ErmLocalTitle ermLocalTitle : list) {
            Integer totalCount = ermLocalTitle.getTotalCount();
            String type = ermLocalTitle.getType();
            if ("Book".equals(type)) {
                ermLocalDatabase.setBooks(totalCount);
            } else if ("Journal".equals(type)) {
                ermLocalDatabase.setJournals(totalCount);
            } else if ("Video".equals(type)) {
                ermLocalDatabase.setVideos(totalCount);
            }
        }
        return ermLocalDatabase;
    }
}
