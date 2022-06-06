package cn.com.tcc.ofa.erm.service.impl;

import cn.com.tcc.ofa.erm.mapper.ErmLicenseTermsMapper;
import cn.com.tcc.ofa.erm.model.po.ErmLicenseTerms;
import cn.com.tcc.ofa.erm.service.ErmLicenseTermsService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 许可条款表 服务实现类
 * </p>
 *
 * @author hsw
 * @since 2022-05-11
 */
@Service
public class ErmLicenseTermsServiceImpl extends ServiceImpl<ErmLicenseTermsMapper, ErmLicenseTerms> implements ErmLicenseTermsService {

    @Override
    public boolean saveErmLicenseTerms(ErmLicenseTerms ermLicenseTerms) {
        Long[] authorizedUsersArrLong = ermLicenseTerms.getAuthorizedUsersArrLong();
        String[] authorizedUsersArrStr = ermLicenseTerms.getAuthorizedUsersArrStr();
        ermLicenseTerms.setAuthorizedUsers(StrUtil.join(",", authorizedUsersArrStr));
        ermLicenseTerms.setAuthorizedUsersId(StrUtil.join(",", authorizedUsersArrLong));

        Integer[] archivingFormatArrInt = ermLicenseTerms.getArchivingFormatArrInt();
        String[] archivingFormatArrStr = ermLicenseTerms.getArchivingFormatArrStr();
        ermLicenseTerms.setArchivingFormat(StrUtil.join(",", archivingFormatArrStr));
        ermLicenseTerms.setArchivingFormatId(StrUtil.join(",", archivingFormatArrInt));

        Integer[] prePrintArchiveConditionsArrInt = ermLicenseTerms.getPrePrintArchiveConditionsArrInt();
        String[] prePrintArchiveConditionsArrStr = ermLicenseTerms.getPrePrintArchiveConditionsArrStr();
        ermLicenseTerms.setPrePrintArchiveConditions(StrUtil.join(",", prePrintArchiveConditionsArrStr));
        ermLicenseTerms.setPrePrintArchiveConditionsId(StrUtil.join(",", prePrintArchiveConditionsArrInt));

        Integer[] postPrintArchiveConditionsArrInt = ermLicenseTerms.getPostPrintArchiveConditionsArrInt();
        String[] postPrintArchiveConditionsArrStr = ermLicenseTerms.getPostPrintArchiveConditionsArrStr();
        ermLicenseTerms.setPostPrintArchiveConditions(StrUtil.join(",", postPrintArchiveConditionsArrStr));
        ermLicenseTerms.setPostPrintArchiveConditionsId(StrUtil.join(",", postPrintArchiveConditionsArrInt));
        return saveOrUpdate(ermLicenseTerms);
    }
}
