package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.model.vo.RestData;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hsw
 * @date 2022/4/29 10:47
 */
@RestController
@Api(tags = "测试接口")
public class TestController extends BaseController{


    @GetMapping("/test")
    public RestData test(){
        return addRestData();
    }

}
