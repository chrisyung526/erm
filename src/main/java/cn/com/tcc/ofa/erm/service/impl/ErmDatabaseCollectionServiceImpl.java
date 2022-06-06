package cn.com.tcc.ofa.erm.service.impl;

import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.erm.mapper.ErmDatabaseCollectionMapper;
import cn.com.tcc.ofa.erm.model.po.ErmDatabaseCollection;
import cn.com.tcc.ofa.erm.model.vo.ErmDatabaseCollectionVo;
import cn.com.tcc.ofa.erm.service.ErmDatabaseCollectionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hsw
 * @date 2022/5/5 17:38
 */
@Service
public class ErmDatabaseCollectionServiceImpl extends ServiceImpl<ErmDatabaseCollectionMapper, ErmDatabaseCollection> implements ErmDatabaseCollectionService {
    @Autowired
    ErmDatabaseCollectionMapper ermDatabaseCollectionMapper;

    @Override
    public IPage pageList(PageDto pageDto) {
        return page(new Page<>(pageDto.getCurrent(), pageDto.getSize()));
    }

    @Override
    public List<ErmDatabaseCollectionVo> getCollectionName(List<Long> databaseIds) {
        return ermDatabaseCollectionMapper.getCollectionName(databaseIds);
    }

}
