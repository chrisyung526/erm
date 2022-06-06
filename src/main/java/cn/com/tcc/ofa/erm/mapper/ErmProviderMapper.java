package cn.com.tcc.ofa.erm.mapper;

import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.model.dto.ErmProviderDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmProviderDto;
import cn.com.tcc.ofa.erm.model.po.ErmProvider;
import cn.com.tcc.ofa.erm.model.vo.ErmProviderDatabaseVo;
import cn.com.tcc.ofa.erm.model.vo.ErmProviderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 供应商表 Mapper 接口
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Mapper
public interface ErmProviderMapper extends BaseMapper<ErmProvider> {

    /**
     * 分页列表
     * @param page
     * @param dto
     * @return
     */
    IPage<ErmProviderVo> pageList(Page<ErmProviderVo> page, @Param("dto") ErmProviderDto dto);

    /**
     * 查询供应商下的资源库
     * @param page
     * @param dto
     * @return
     */
    IPage<ErmProviderDatabaseVo> pageResources(PageDto page, @Param("dto") ErmProviderDatabaseDto dto);
}
