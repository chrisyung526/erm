package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.model.po.ErmDatabaseCollection;
import cn.com.tcc.ofa.erm.service.ErmDatabaseCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hsw
 * @date 2022/5/5 17:36
 */
@RestController
@Api(tags = "资源库分组管理")
@RequestMapping("/databaseCollection")
public class ErmDatabaseCollectionController extends BaseController {
    @Autowired
    ErmDatabaseCollectionService ermDatabaseCollectionService;

    @GetMapping("/list")
    @ApiOperation(value = "查询列表", response = ErmDatabaseCollection.class)
    public RestData list() {
        List<ErmDatabaseCollection> list = ermDatabaseCollectionService.list();
        return addRestData(list);
    }
}
