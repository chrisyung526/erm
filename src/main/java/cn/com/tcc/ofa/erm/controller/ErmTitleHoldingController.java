package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.model.po.ErmTitleHolding;
import cn.com.tcc.ofa.erm.service.ErmTitleHoldingService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 期刊分期表 前端控制器
 * </p>
 *
 * @author hsw
 * @since 2022-05-12
 */
@RestController
@RequestMapping("/titleHolding")
@Api(tags = "期刊馆藏管理")
public class ErmTitleHoldingController extends BaseController {
    @Autowired
    ErmTitleHoldingService ermTitleHoldingService;

    @GetMapping("/list/{titleId}")
    @ApiOperation(value = "查询列表", response = ErmTitleHolding.class)
    public RestData page(@PathVariable Long titleId) {
        List<ErmTitleHolding> list = ermTitleHoldingService.list(new LambdaQueryWrapper<ErmTitleHolding>()
                .eq(ErmTitleHolding::getTitleId, titleId));
        return addRestData(list);
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public RestData add(@RequestBody @Valid ErmTitleHolding ermTitleHolding, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        boolean b = ermTitleHoldingService.save(ermTitleHolding);
        if(!b){
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询期刊馆藏详情", response = ErmTitleHolding.class)
    public RestData getDetail(@PathVariable Long id) {
        ErmTitleHolding ermTitleHolding = ermTitleHoldingService.getById(id);
        return addRestData(ermTitleHolding);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "期刊馆藏更新")
    public RestData update(@PathVariable Long id, @RequestBody @Valid ErmTitleHolding ermTitleHolding, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ermTitleHolding.setId(id);
        boolean b = ermTitleHoldingService.updateById(ermTitleHolding);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除期刊馆藏")
    public RestData delete(@PathVariable Long id) {
        boolean b = ermTitleHoldingService.removeById(id);
        if(!b){
            throw new RestException(RestExceptionEnum.DELETE_ERROR_CODE);
        }
        return addRestData();
    }
}
