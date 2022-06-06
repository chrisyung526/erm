package cn.com.tcc.ofa.erm.service;

import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalTitleDto;
import cn.com.tcc.ofa.erm.model.po.ErmLocalTitle;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 本地资源清单表 服务类
 * </p>
 *
 * @author hsw
 * @since 2022-05-05
 */
public interface ErmLocalTitleService extends IService<ErmLocalTitle> {

    /**
     * 分页列表
     * @param dto
     * @param pageDto
     * @return
     */
    IPage<ErmLocalTitle> pageList(ErmLocalTitleDto dto, PageDto pageDto);

    /**
     * 上传清单文件
     * @param file
     * @param databaseId
     * @return
     */
    boolean uploadTitle(MultipartFile file, Long databaseId);

    /**
     * 保存资源清单
     * @param ermLocalTitle
     * @return
     */
    boolean saveErmLocalTitle(ErmLocalTitle ermLocalTitle);
}
