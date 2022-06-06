package cn.com.tcc.ofa.erm.mapper;

import cn.com.tcc.ofa.erm.model.dto.ErmDatabaseLicenseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalDatabaseDto;
import cn.com.tcc.ofa.erm.model.po.ErmLocalDatabase;
import cn.com.tcc.ofa.erm.model.vo.ErmDatabaseLicenseVo;
import cn.com.tcc.ofa.erm.model.vo.ErmLocalDatabaseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hsw
 * @date 2022/4/29 10:47
 */
@Mapper
public interface ErmLocalDatabaseMapper extends BaseMapper<ErmLocalDatabase> {
    /**
     * 列表分页
     * @param page
     * @param dto
     * @return
     */
    IPage<ErmLocalDatabaseVo> pageList(Page<ErmLocalDatabaseVo> page, @Param("dto") ErmLocalDatabaseDto dto);

    /**
     * 获取所有资源库不分页
     * @param dto
     * @return
     */
    List<ErmLocalDatabaseVo> pageList(@Param("dto") ErmLocalDatabaseDto dto);

    /**
     * 分页查询关联的许可协议列表
     * @param page
     * @param dto
     * @return
     */
    IPage<ErmDatabaseLicenseVo> pageLicenses(Page<ErmDatabaseLicenseVo> page, @Param("dto") ErmDatabaseLicenseDto dto);

}
