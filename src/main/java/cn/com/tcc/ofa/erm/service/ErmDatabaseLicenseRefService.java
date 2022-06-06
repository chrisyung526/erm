package cn.com.tcc.ofa.erm.service;

import cn.com.tcc.ofa.erm.model.dto.UpdatePrevailingDto;
import cn.com.tcc.ofa.erm.model.po.ErmDatabaseLicenseRef;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 许可协议和资源库中间表 服务类
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
public interface ErmDatabaseLicenseRefService extends IService<ErmDatabaseLicenseRef> {

    /**
     * 更新许可是否在用
     * @param dto
     * @return
     */
    boolean updatePrevailing(UpdatePrevailingDto dto);
}
