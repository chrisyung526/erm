package cn.com.tcc.ofa.erm.service;

import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.model.po.ErmDatabaseCollection;
import cn.com.tcc.ofa.erm.model.vo.ErmDatabaseCollectionVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author hsw
 * @date 2022/5/5 17:38
 */
public interface ErmDatabaseCollectionService extends IService<ErmDatabaseCollection> {
    /**
     * 分页列表
     * @param pageDto
     * @return
     */
    IPage pageList(PageDto pageDto);

    /**
     * 根据资源库id获取分组名称
     * @param databaseIds
     * @return
     */
    List<ErmDatabaseCollectionVo> getCollectionName(List<Long> databaseIds);
}
