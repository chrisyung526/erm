package cn.com.tcc.ofa.erm.mapper;

import cn.com.tcc.ofa.erm.model.dto.ErmLicenseDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLicenseDto;
import cn.com.tcc.ofa.erm.model.po.ErmLicense;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseDatabaseVo;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 许可协议表 Mapper 接口
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Mapper
public interface ErmLicenseMapper extends BaseMapper<ErmLicense> {

    /**
     * 分页列表
     * @param objectPage
     * @param dto
     * @return
     */
    IPage<ErmLicenseVo> pageList(Page<Object> objectPage, @Param("dto") ErmLicenseDto dto);

    /**
     * 分页查询关联的资源库列表
     * @param page
     * @param dto
     * @return
     */
    Page<ErmLicenseDatabaseVo> pageResources(Page<ErmLicenseDatabaseVo> page, @Param("dto") ErmLicenseDatabaseDto dto);
}
