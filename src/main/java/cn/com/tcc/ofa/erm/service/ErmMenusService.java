package cn.com.tcc.ofa.erm.service;

import cn.com.tcc.ofa.erm.model.po.ErmMenus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author hsw
 * @date 2022/5/5 19:10
 */
public interface ErmMenusService extends IService<ErmMenus> {
    /**
     * 下拉选项删除
     * @param type
     * @param fatherId
     * @param id
     * @return
     */
    boolean removeByMenus(Integer type, Integer fatherId, Long id);

    /**
     * 获取菜单数据
     * @param parentId
     * @return
     */
    List<Map<String, Object>> getNodeTree();
}
