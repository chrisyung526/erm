package cn.com.tcc.ofa.erm.service;

import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.model.dto.ErmDatabaseLicenseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalDatabaseDto;
import cn.com.tcc.ofa.erm.model.po.ErmLocalDatabase;
import cn.com.tcc.ofa.erm.model.vo.ErmDatabaseLicenseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author hsw
 * @date 2022/4/29 10:45
 */
public interface ErmLocalDatabaseService extends IService<ErmLocalDatabase> {
    /**
     * 分页列表
     * @param dto
     * @param pageDto
     * @return
     */
    IPage pageList(ErmLocalDatabaseDto dto, PageDto pageDto);

    /**
     * 分页查询关联的许可协议列表
     * @param page
     * @param dto
     * @return
     */
    IPage<ErmDatabaseLicenseVo> pageLicenses(Page<ErmDatabaseLicenseVo> page, ErmDatabaseLicenseDto dto);

    /**
     * 删除资源库
     * @param ids
     * @return
     */
    boolean removeDatabase(List<Long> ids);

    /**
     * 根据资源库id查询详情
     * @param id
     * @return
     */
    ErmLocalDatabase getDetail(Long id);
}
