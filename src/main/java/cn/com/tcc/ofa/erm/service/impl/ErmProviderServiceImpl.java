package cn.com.tcc.ofa.erm.service.impl;

import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.mapper.ErmProviderMapper;
import cn.com.tcc.ofa.erm.model.dto.ErmProviderDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmProviderDto;
import cn.com.tcc.ofa.erm.model.po.ErmLocalDatabase;
import cn.com.tcc.ofa.erm.model.po.ErmProvider;
import cn.com.tcc.ofa.erm.service.ErmLocalDatabaseService;
import cn.com.tcc.ofa.erm.service.ErmProviderService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 供应商表 服务实现类
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Service
public class ErmProviderServiceImpl extends ServiceImpl<ErmProviderMapper, ErmProvider> implements ErmProviderService {
    @Autowired
    ErmProviderMapper ermProviderMapper;

    @Autowired
    ErmLocalDatabaseService ermLocalDatabaseService;

    @Override
    public IPage pageList(ErmProviderDto dto, PageDto pageDto) {
        return ermProviderMapper.pageList(new Page<>(pageDto.getCurrent(), pageDto.getSize()), dto);
    }

    @Override
    public boolean removeProvider(List<Long> ids) {
        List<ErmLocalDatabase> list = ermLocalDatabaseService.list(new LambdaQueryWrapper<ErmLocalDatabase>().in(ErmLocalDatabase::getProviderId, ids));
        if (CollUtil.isNotEmpty(list)) {
            throw new RestException(RestExceptionEnum.DELETE_PROVIDER_ERROR_CODE);
        }
        return removeByIds(ids);
    }

    @Override
    public IPage pageResources(ErmProviderDatabaseDto dto, PageDto page) {
        return ermProviderMapper.pageResources(page, dto);
    }
}
