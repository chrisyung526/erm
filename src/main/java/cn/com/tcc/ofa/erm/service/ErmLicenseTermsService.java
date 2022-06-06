package cn.com.tcc.ofa.erm.service;

import cn.com.tcc.ofa.erm.model.po.ErmLicenseTerms;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 许可条款表 服务类
 * </p>
 *
 * @author hsw
 * @since 2022-05-11
 */
public interface ErmLicenseTermsService extends IService<ErmLicenseTerms> {

    /**
     * 保存或更新许可条款
     * @param ermLicenseTerms
     * @return
     */
    boolean saveErmLicenseTerms(ErmLicenseTerms ermLicenseTerms);
}
