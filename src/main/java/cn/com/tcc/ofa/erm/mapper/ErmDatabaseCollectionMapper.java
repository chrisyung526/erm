package cn.com.tcc.ofa.erm.mapper;

import cn.com.tcc.ofa.erm.model.po.ErmDatabaseCollection;
import cn.com.tcc.ofa.erm.model.vo.ErmDatabaseCollectionVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hsw
 * @date 2022/5/5 17:39
 */
@Mapper
public interface ErmDatabaseCollectionMapper extends BaseMapper<ErmDatabaseCollection> {
    /**
     * 根据资源库id获取分组名称
     * @param databaseIds
     * @return
     */
    List<ErmDatabaseCollectionVo> getCollectionName(List<Long> databaseIds);
}
