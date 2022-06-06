package cn.com.tcc.ofa.erm.service;

import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLicenseDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLicenseDto;
import cn.com.tcc.ofa.erm.model.po.ErmLicense;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseDatabaseVo;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 许可协议表 服务类
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
public interface ErmLicenseService extends IService<ErmLicense> {

    /**
     * 分页列表
     * @param dto
     * @param pageDto
     * @return
     */
    IPage<ErmLicenseVo> pageList(ErmLicenseDto dto, PageDto pageDto);

    /**
     * 分页查询关联的资源库列表
     * @param objectPage
     * @param dto
     * @return
     */
    Page<ErmLicenseDatabaseVo> pageResources(Page<ErmLicenseDatabaseVo> objectPage, ErmLicenseDatabaseDto dto);

    /**
     * 删除许可协议
     * @param ids
     * @return
     */
    boolean removeLicense(List<Long> ids);

    /**
     * 模板复制接口
     * @param id
     * @return
     */
    boolean copy(Long id);
}
