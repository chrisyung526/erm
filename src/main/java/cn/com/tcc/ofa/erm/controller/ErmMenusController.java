package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.model.po.ErmMenus;
import cn.com.tcc.ofa.erm.service.ErmMenusService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author hsw
 * @date 2022/5/5 19:06
 */
@RestController
@Api(tags = "下拉选项管理")
@RequestMapping("/menus")
public class ErmMenusController extends BaseController {
    @Autowired
    ErmMenusService ermMenusService;

    @GetMapping("/list/{type}/{fatherId}")
    @ApiOperation(value = "根据父级id查询所有菜单", response = ErmMenus.class)
    public RestData list(@PathVariable("type") Integer type,
                         @PathVariable("fatherId") Integer fatherId) {
        List<ErmMenus> list = ermMenusService.list(new LambdaQueryWrapper<ErmMenus>()
                .eq(ErmMenus::getType, type)
                .eq(ErmMenus::getFatherId, fatherId)
                .orderByAsc(ErmMenus::getSort));
        return addRestData(list);
    }

    @GetMapping("/{type}/{fatherId}")
    @ApiOperation(value = "根据父级id查询分页列表", response = ErmMenus.class)
    public RestData page(@PathVariable("type") Integer type,
                         @PathVariable("fatherId") Integer fatherId, PageDto PageDto) {
        QueryWrapper<ErmMenus> queryWrapper = new QueryWrapper<>();
        String sortColumns = PageDto.getSortColumns();
        if (StrUtil.isNotBlank(sortColumns)) {
            queryWrapper.orderByDesc(PageDto.getSortDesc(), sortColumns)
                    .lambda()
                    .eq(ErmMenus::getType, type)
                    .eq(ErmMenus::getFatherId, fatherId);
        } else {
            queryWrapper.lambda()
                    .orderByDesc(ErmMenus::getSort)
                    .eq(ErmMenus::getType, type)
                    .eq(ErmMenus::getFatherId, fatherId);
        }
        Page<ErmMenus> page = ermMenusService.page(new Page<>(PageDto.getCurrent(), PageDto.getSize()), queryWrapper);
        return addRestData(page);
    }

    @GetMapping("/getNodeTree")
    @ApiOperation(value = "获取菜单树接口")
    public RestData getNodeTree() {
        List<Map<String, Object>> list = ermMenusService.getNodeTree();
        return addRestData(list);
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public RestData add(@RequestBody @Valid ErmMenus ermMenus, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        boolean b = ermMenusService.save(ermMenus);
        if (!b) {
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询下拉项详情", response = ErmMenus.class)
    public RestData getDetail(@PathVariable Long id) {
        ErmMenus ermMenus  = ermMenusService.getById(id);
        return addRestData(ermMenus);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "下拉项更新")
    public RestData update(@PathVariable Long id, @RequestBody @Valid ErmMenus ermMenus, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ermMenus.setId(id);
        boolean b = ermMenusService.updateById(ermMenus);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }

    @DeleteMapping("/{type}/{fatherId}/{id}")
    @ApiOperation(value = "下拉项删除")
    public RestData delete(@PathVariable Integer type,
                           @PathVariable Integer fatherId,
                           @PathVariable Long id) {
        boolean b = ermMenusService.removeByMenus(type, fatherId, id);
        if(!b){
            throw new RestException(RestExceptionEnum.DELETE_ERROR_CODE);
        }
        return addRestData();
    }
}
