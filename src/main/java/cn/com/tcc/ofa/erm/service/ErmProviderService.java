package cn.com.tcc.ofa.erm.service;

import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.model.dto.ErmProviderDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmProviderDto;
import cn.com.tcc.ofa.erm.model.po.ErmProvider;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
public interface ErmProviderService extends IService<ErmProvider> {

    /**
     * 分页列表
     * @param dto
     * @param pageDto
     * @return
     */
    IPage pageList(ErmProviderDto dto, PageDto pageDto);

    /**
     * 批量删除供应商
     * @param ids
     * @return
     */
    boolean removeProvider(List<Long> ids);

    /**
     * 供应商下的资源库id
     * @param dto
     * @param pageDto
     * @return
     */
    IPage pageResources(ErmProviderDatabaseDto dto, PageDto pageDto);
}
