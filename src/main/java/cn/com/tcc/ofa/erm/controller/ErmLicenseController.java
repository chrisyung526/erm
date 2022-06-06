package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.model.dto.AddErmLicenseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLicenseDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLicenseDto;
import cn.com.tcc.ofa.erm.model.po.ErmDatabaseLicenseRef;
import cn.com.tcc.ofa.erm.model.po.ErmLicense;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseDatabaseVo;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseVo;
import cn.com.tcc.ofa.erm.service.ErmDatabaseLicenseRefService;
import cn.com.tcc.ofa.erm.service.ErmLicenseService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 许可协议表 前端控制器
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@RestController
@Api(tags = "许可协议管理")
@RequestMapping("/license")
public class ErmLicenseController extends BaseController {
    @Autowired
    ErmLicenseService ermLicenseService;

    @Autowired
    ErmDatabaseLicenseRefService ermDatabaseLicenseRefService;

    @GetMapping
    @ApiOperation(value = "查询分页列表", response = ErmLicenseVo.class)
    public RestData page(ErmLicenseDto dto, PageDto pageDto) {
        IPage<ErmLicenseVo> page = ermLicenseService.pageList(dto, pageDto);
        return addRestData(page);
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public RestData add(@RequestBody @Valid AddErmLicenseDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ErmLicense ermLicense = new ErmLicense();
        BeanUtil.copyProperties(dto, ermLicense);
        ermLicense.setType(dto.getTypeId());
        ermLicense.setStatus(dto.getStatusId());
        boolean b = ermLicenseService.save(ermLicense);
        if(!b){
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @PostMapping("/copy/{id}")
    @ApiOperation(value = "模板复制接口")
    public RestData copy(@PathVariable Long id) {
        boolean b = ermLicenseService.copy(id);
        if(!b){
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询许可协议详情", response = ErmLicense.class)
    public RestData getDetail(@PathVariable Long id) {
        ErmLicense ermLicense = ermLicenseService.getById(id);
        return addRestData(ermLicense);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "许可协议更新")
    public RestData update(@PathVariable Long id, @RequestBody @Valid ErmLicense ermLicense, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ermLicense.setId(id);
        boolean b = ermLicenseService.updateById(ermLicense);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/resources")
    @ApiOperation(value = "分页查询关联的资源库列表", response = ErmLicenseDatabaseVo.class)
    public RestData getResources(PageDto pageDto, ErmLicenseDatabaseDto dto) {
        Page<ErmLicenseDatabaseVo> page = ermLicenseService.pageResources(new Page<>(pageDto.getCurrent(), pageDto.getSize()), dto);
        return addRestData(page);
    }

    @PostMapping("/resources/{licenseId}")
    @ApiOperation(value = "新增关联资源库", response = ErmLicenseDatabaseVo.class)
    public RestData associateResource(@PathVariable Long licenseId,
                                      @RequestBody List<Long> ids) {
        ArrayList<ErmDatabaseLicenseRef> list = new ArrayList<>();
        for (Long id : ids) {
            ErmDatabaseLicenseRef ermDatabaseLicenseRef = new ErmDatabaseLicenseRef();
            ermDatabaseLicenseRef.setLicenseId(licenseId);
            ermDatabaseLicenseRef.setDatabaseId(id);
            list.add(ermDatabaseLicenseRef);
        }
        boolean b = ermDatabaseLicenseRefService.saveBatch(list);
        if(!b){
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @DeleteMapping("/resources/{licenseId}")
    @ApiOperation(value = "删除关联资源库", response = ErmLicenseDatabaseVo.class)
    public RestData removeResource(@PathVariable Long licenseId,
                                      @RequestBody List<Long> ids) {
        LambdaUpdateWrapper<ErmDatabaseLicenseRef> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ErmDatabaseLicenseRef::getLicenseId, licenseId)
                .in(ErmDatabaseLicenseRef::getDatabaseId, ids);
        boolean b = ermDatabaseLicenseRefService.remove(wrapper);
        if(!b){
            throw new RestException(RestExceptionEnum.DELETE_ERROR_CODE);
        }
        return addRestData();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除许可协议")
    public RestData delete(@RequestBody List<Long> ids) {
        boolean b = ermLicenseService.removeLicense(ids);
        if(!b){
            throw new RestException(RestExceptionEnum.DELETE_ERROR_CODE);
        }
        return addRestData();
    }
}
