package cn.com.tcc.ofa.erm.service.impl;

import cn.com.tcc.ofa.erm.mapper.ErmDatabaseLicenseRefMapper;
import cn.com.tcc.ofa.erm.model.dto.UpdatePrevailingDto;
import cn.com.tcc.ofa.erm.model.po.ErmDatabaseLicenseRef;
import cn.com.tcc.ofa.erm.service.ErmDatabaseLicenseRefService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 许可协议和资源库中间表 服务实现类
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Service
public class ErmDatabaseLicenseRefServiceImpl extends ServiceImpl<ErmDatabaseLicenseRefMapper, ErmDatabaseLicenseRef> implements ErmDatabaseLicenseRefService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePrevailing(UpdatePrevailingDto dto) {
        ErmDatabaseLicenseRef ref = new ErmDatabaseLicenseRef();
        ref.setDatabaseId(dto.getDatabaseId());
        ref.setIsPrevailing(1);
        ref.setLicenseId(dto.getUpdatePrevailingId());
        LambdaUpdateWrapper<ErmDatabaseLicenseRef> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ErmDatabaseLicenseRef::getIsPrevailing, 0)
                .eq(ErmDatabaseLicenseRef::getDatabaseId, dto.getDatabaseId())
                .eq(ErmDatabaseLicenseRef::getLicenseId, dto.getPrevailingId());
        boolean b = update(wrapper);
        if (b) {
            wrapper.clear();
            wrapper.set(ErmDatabaseLicenseRef::getIsPrevailing, 0)
                    .eq(ErmDatabaseLicenseRef::getDatabaseId, dto.getDatabaseId())
                    .eq(ErmDatabaseLicenseRef::getLicenseId, dto.getPrevailingId());
            update(wrapper);
        }
        return b;
    }
}
