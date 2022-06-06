package cn.com.tcc.ofa.erm.service.impl;

import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.mapper.ErmLicenseMapper;
import cn.com.tcc.ofa.erm.model.dto.ErmLicenseDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLicenseDto;
import cn.com.tcc.ofa.erm.model.po.ErmDatabaseLicenseRef;
import cn.com.tcc.ofa.erm.model.po.ErmLicense;
import cn.com.tcc.ofa.erm.model.po.ErmLicenseTerms;
import cn.com.tcc.ofa.erm.model.po.ErmPaymentPlan;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseDatabaseVo;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseVo;
import cn.com.tcc.ofa.erm.service.ErmDatabaseLicenseRefService;
import cn.com.tcc.ofa.erm.service.ErmLicenseService;
import cn.com.tcc.ofa.erm.service.ErmLicenseTermsService;
import cn.com.tcc.ofa.erm.service.ErmPaymentPlanService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 许可协议表 服务实现类
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Service
public class ErmLicenseServiceImpl extends ServiceImpl<ErmLicenseMapper, ErmLicense> implements ErmLicenseService {
    @Autowired
    ErmLicenseMapper ermLicenseMapper;

    @Autowired
    ErmLicenseTermsService ermLicenseTermsService;

    @Autowired
    ErmPaymentPlanService ermPaymentPlanService;

    @Autowired
    ErmDatabaseLicenseRefService ermDatabaseLicenseRefService;

    @Override
    public IPage<ErmLicenseVo> pageList(ErmLicenseDto dto, PageDto pageDto) {
        IPage<ErmLicenseVo> page = ermLicenseMapper.pageList(new Page<>(pageDto.getCurrent(), pageDto.getSize()), dto);
        // List<ErmLicenseVo> records = page.getRecords();
        // if (CollUtil.isNotEmpty(records)) {
        //     // id重复则拼接databaseName,并删除原来的
        //     Map<Long, String> map = new HashMap<>();
        //     ArrayList<ErmLicenseVo> list = new ArrayList<>();
        //     for (int i = 0; i < records.size(); i++) {
        //         ErmLicenseVo e = records.get(i);
        //         Long id = e.getId();
        //         if (map.containsKey(id)) {
        //             list.removeIf(ermLicenseVo -> ermLicenseVo.getId().equals(id));
        //             String databaseName = map.get(id);
        //             e.setDatabaseName(databaseName + "," + e.getDatabaseName());
        //         } else {
        //             map.put(id, e.getDatabaseName());
        //         }
        //         list.add(e);
        //     }
        //     page.setRecords(list);
        //     page.setTotal(list.size());
        // }
        return page;
    }

    @Override
    public Page<ErmLicenseDatabaseVo> pageResources(Page<ErmLicenseDatabaseVo> page, ErmLicenseDatabaseDto dto) {
        return ermLicenseMapper.pageResources(page, dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeLicense(List<Long> ids) {
        // 在用的许可协议不能删除
        List<ErmDatabaseLicenseRef> list = ermDatabaseLicenseRefService.list(
                new LambdaQueryWrapper<ErmDatabaseLicenseRef>()
                        .eq(ErmDatabaseLicenseRef::getIsPrevailing, 1)
                        .in(ErmDatabaseLicenseRef::getLicenseId, ids));
        if (CollUtil.isNotEmpty(list)) {
            throw new RestException(RestExceptionEnum.DELETE_LICENSE_ERROR_CODE);
        }
        boolean b = removeByIds(ids);
        if (b) {
            // 同步删除许可条款和许可分期
            ermLicenseTermsService.remove(new LambdaUpdateWrapper<ErmLicenseTerms>().in(ErmLicenseTerms::getLicenseId, ids));
            ermPaymentPlanService.remove(new LambdaUpdateWrapper<ErmPaymentPlan>().in(ErmPaymentPlan::getLicenseId, ids));
        }
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean copy(Long id) {
        boolean b;
        ErmLicense ermLicense = getOne(new LambdaQueryWrapper<ErmLicense>().eq(ErmLicense::getIsTemplate, 1).eq(ErmLicense::getId, id));
        if (BeanUtil.isEmpty(ermLicense)) {
            throw new RestException(RestExceptionEnum.COPY_LICENSE_ERROR_CODE);
        }

        ErmLicense license = new ErmLicense();
        BeanUtil.copyProperties(ermLicense, license);
        license.setId(null);
        license.setIsTemplate(0);
        b = save(license);

        ErmLicenseTerms ermLicenseTerms = ermLicenseTermsService.getOne(new LambdaQueryWrapper<ErmLicenseTerms>().eq(ErmLicenseTerms::getLicenseId, id));

        if (BeanUtil.isNotEmpty(ermLicenseTerms)) {
            ErmLicenseTerms licenseTerms = new ErmLicenseTerms();
            BeanUtil.copyProperties(ermLicenseTerms, licenseTerms);
            licenseTerms.setId(null);
            b = ermLicenseTermsService.save(licenseTerms);
        }
        return b;
    }
}
