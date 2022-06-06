package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.model.dto.ErmProviderDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmProviderDto;
import cn.com.tcc.ofa.erm.model.po.ErmProvider;
import cn.com.tcc.ofa.erm.model.vo.ErmProviderVo;
import cn.com.tcc.ofa.erm.service.ErmProviderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 供应商表 前端控制器
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@RestController
@RequestMapping("/provider")
@Api(tags = "供应商管理")
public class ErmProviderController extends BaseController {
    @Autowired
    ErmProviderService ermProviderService;

    @GetMapping
    @ApiOperation(value = "分页列表", response = ErmProviderVo.class)
    public RestData page(ErmProviderDto dto, PageDto pageDto) {
        IPage page = ermProviderService.pageList(dto, pageDto);
        return addRestData(page);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询所有供应商", response = ErmProvider.class)
    public RestData list() {
        List<ErmProvider> list = ermProviderService.list();
        return addRestData(list);
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public RestData add(@RequestBody @Valid ErmProvider ermProvider, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        boolean b = ermProviderService.save(ermProvider);
        if (!b) {
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除供应商")
    public RestData delete(@RequestBody List<Long> ids) {
        boolean b = ermProviderService.removeProvider(ids);
        if (!b) {
            throw new RestException(RestExceptionEnum.DELETE_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询供应商详情", response = ErmProvider.class)
    public RestData getDetail(@PathVariable Long id) {
        ErmProvider ermProvider = ermProviderService.getById(id);
        return addRestData(ermProvider);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "供应商更新")
    public RestData update(@PathVariable Long id, @RequestBody @Valid ErmProvider ermProvider, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ermProvider.setId(id);
        boolean b = ermProviderService.updateById(ermProvider);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/database")
    @ApiOperation(value = "查询供应商下的资源库列表")
    public RestData getDatabases(ErmProviderDatabaseDto dto, PageDto pageDto) {
        IPage page = ermProviderService.pageResources(dto, pageDto);
        return addRestData(page);
    }
}
