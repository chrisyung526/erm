package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.model.dto.AddErmLocalDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmDatabaseLicenseDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalDatabaseDto;
import cn.com.tcc.ofa.erm.model.dto.UpdatePrevailingDto;
import cn.com.tcc.ofa.erm.model.po.ErmDatabaseLicenseRef;
import cn.com.tcc.ofa.erm.model.po.ErmLocalDatabase;
import cn.com.tcc.ofa.erm.model.vo.ErmDatabaseLicenseVo;
import cn.com.tcc.ofa.erm.model.vo.ErmLocalDatabaseEnVo;
import cn.com.tcc.ofa.erm.model.vo.ErmLocalDatabaseVo;
import cn.com.tcc.ofa.erm.service.ErmDatabaseLicenseRefService;
import cn.com.tcc.ofa.erm.service.ErmLocalDatabaseService;
import cn.com.tcc.ofa.erm.service.ErmLocalTitleService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hsw
 * @date 2022/4/29 10:47
 */
@RestController
@Api(tags = "本地资源库管理")
@RequestMapping("/localDatabase")
public class ErmLocalDatabaseController extends BaseController {
    @Autowired
    ErmLocalDatabaseService ermLocalDatabaseService;

    @Autowired
    ErmLocalTitleService ermLocalTitleService;

    @Autowired
    ErmDatabaseLicenseRefService ermDatabaseLicenseRefService;

    @GetMapping
    @ApiOperation(value = "查询分页列表", response = ErmLocalDatabaseVo.class)
    public RestData page(ErmLocalDatabaseDto dto, PageDto pageDto) {
        IPage page = ermLocalDatabaseService.pageList(dto, pageDto);
        return addRestData(page);
    }

    @GetMapping("/export")
    @ApiOperation(value = "资源库导出")
    public void export(HttpServletRequest request, HttpServletResponse response, ErmLocalDatabaseDto dto) throws IOException {
        IPage page = ermLocalDatabaseService.pageList(dto, null);
        List ermLocalDatabaseVos = page.getRecords();
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = System.currentTimeMillis() + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        String jsonStr = JSONUtil.toJsonStr(ermLocalDatabaseVos);
        List<ErmLocalDatabaseEnVo> ermLocalDatabaseEnVos = JSONUtil.toList(jsonStr, ErmLocalDatabaseEnVo.class);
        EasyExcel.write(response.getOutputStream(), ErmLocalDatabaseEnVo.class).sheet("database").doWrite(ermLocalDatabaseEnVos);
        // TODO: 2022/5/12 Excel国际化
        // String header = request.getHeader("Accept-Language");
        // if ("zh-CN".equals(header)) {
        //     String jsonStr = JSONUtil.toJsonStr(ermLocalTitles);
        //     List<ErmLocalTitleEnVo> ermLocalTitleEnVos = JSONUtil.toList(jsonStr, ErmLocalTitleEnVo.class);
        //     EasyExcel.write(response.getOutputStream(), ErmLocalTitleEnVo.class).sheet("title").doWrite(ermLocalTitleCnVos);
        // } else if ("zh-HK".equals(header)) {
        //     ArrayList<ErmLocalTitleTemplateHkDto> list = new ArrayList<>();
        //     EasyExcel.write(response.getOutputStream(), ErmLocalTitleTemplateHkDto.class).sheet("资源清单").doWrite(list);
        // }
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public RestData add(@RequestBody @Valid AddErmLocalDatabaseDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        if (StrUtil.isBlank(dto.getCode())) {
            // 生成Code
            String defaultDatabaseName = dto.getDefaultDatabaseName();
            String first = null;
            if (defaultDatabaseName.length() >= 3) {
                first = defaultDatabaseName.substring(0, 3);
            } else {
                first = defaultDatabaseName;
            }
            long l = System.currentTimeMillis();
            dto.setCode(first + l);
        }
        ErmLocalDatabase ermLocalDatabase = new ErmLocalDatabase();
        BeanUtil.copyProperties(dto, ermLocalDatabase);
        boolean b = ermLocalDatabaseService.save(ermLocalDatabase);
        if (!b) {
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @PutMapping("/status")
    @ApiOperation(value = "批量编辑状态")
    public RestData editStatus(@RequestBody Map<String, Object> map) {
        List<Long> ids = new ArrayList<>();
        Object obj = map.get("ids");
        Object menuId = map.get("menuId");
        if (BeanUtil.isEmpty(obj) && BeanUtil.isEmpty(menuId)) {
            throw new RestException(RestExceptionEnum.EDIT_STATUS_ERROR_CODE);
        }
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                ids.add(Long.valueOf(o.toString()));
            }
        }
        LambdaUpdateWrapper<ErmLocalDatabase> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ErmLocalDatabase::getStatus, Long.valueOf(menuId.toString())).in(ErmLocalDatabase::getId, ids);
        boolean b = ermLocalDatabaseService.update(wrapper);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }

    @PutMapping("/approvalStatus")
    @ApiOperation(value = "批量编辑审批状态")
    public RestData updateApprovalStatus(@RequestBody Map<String, Object> map) {
        List<Long> ids = new ArrayList<>();
        Object obj = map.get("ids");
        Object approvalStatus = map.get("approvalStatus");
        if (BeanUtil.isEmpty(obj) || BeanUtil.isEmpty(approvalStatus)) {
            throw new RestException(RestExceptionEnum.EDIT_APPROVAL_STATUS_ERROR_CODE);
        }
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                ids.add(Long.valueOf(o.toString()));
            }
        }
        LambdaUpdateWrapper<ErmLocalDatabase> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ErmLocalDatabase::getApprovalStatus, Integer.valueOf(approvalStatus.toString())).in(ErmLocalDatabase::getId, ids);
        boolean b = ermLocalDatabaseService.update(wrapper);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        // TODO: 2022/5/6 发送邮件提交审批发布逻辑
        return addRestData();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询电子资源库详情", response = ErmLocalDatabase.class)
    public RestData getDetail(@PathVariable Long id) {
        ErmLocalDatabase ermLocalDatabase = ermLocalDatabaseService.getDetail(id);
        return addRestData(ermLocalDatabase);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "电子资源库更新")
    public RestData update(@PathVariable Long id, @RequestBody @Valid ErmLocalDatabase ermLocalDatabase, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ermLocalDatabase.setId(id);
        boolean b = ermLocalDatabaseService.updateById(ermLocalDatabase);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除资源库")
    public RestData delete(@RequestBody List<Long> ids) {
        boolean b = ermLocalDatabaseService.removeDatabase(ids);
        if (!b) {
            throw new RestException(RestExceptionEnum.DELETE_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/licenses")
    @ApiOperation(value = "分页查询关联的许可协议列表", response = ErmDatabaseLicenseVo.class)
    public RestData getLicenses(PageDto pageDto, ErmDatabaseLicenseDto dto) {
        IPage<ErmDatabaseLicenseVo> page = ermLocalDatabaseService.pageLicenses(new Page<>(pageDto.getCurrent(), pageDto.getSize()), dto);
        return addRestData(page);
    }

    @GetMapping("/notAssociatedLicenses")
    @ApiOperation(value = "分页查询未关联的许可协议列表", response = ErmDatabaseLicenseVo.class)
    public RestData licenses(PageDto pageDto, ErmDatabaseLicenseDto dto) {
        IPage<ErmDatabaseLicenseVo> page = ermLocalDatabaseService.pageLicenses(new Page<>(pageDto.getCurrent(), pageDto.getSize()), dto);
        return addRestData(page);
    }

    @PostMapping("/licenses/{databaseId}")
    @ApiOperation(value = "资源库关联许可协议")
    public RestData associateLicense(@PathVariable Long databaseId,
                                     @RequestBody List<String> ids) {
        ArrayList<ErmDatabaseLicenseRef> list = new ArrayList<>();
        for (String id : ids) {
            ErmDatabaseLicenseRef ermDatabaseLicenseRef = new ErmDatabaseLicenseRef();
            ermDatabaseLicenseRef.setDatabaseId(databaseId);
            ermDatabaseLicenseRef.setLicenseId(Long.valueOf(id));
            list.add(ermDatabaseLicenseRef);
        }
        boolean b = ermDatabaseLicenseRefService.saveBatch(list);
        if (!b) {
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @DeleteMapping("/license/{databaseId}")
    @ApiOperation(value = "删除关联许可协议")
    public RestData removeLicense(@PathVariable Long databaseId,
                                  @RequestBody List<Long> ids) {
        LambdaUpdateWrapper<ErmDatabaseLicenseRef> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ErmDatabaseLicenseRef::getDatabaseId, databaseId)
                .in(ErmDatabaseLicenseRef::getLicenseId, ids);
        boolean b = ermDatabaseLicenseRefService.remove(wrapper);
        if (!b) {
            throw new RestException(RestExceptionEnum.DELETE_ERROR_CODE);
        }
        return addRestData();
    }

    @PutMapping("/license")
    @ApiOperation(value = "修改关联许可为在用")
    public RestData removeLicense(@RequestBody @Valid UpdatePrevailingDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        boolean b = ermDatabaseLicenseRefService.updatePrevailing(dto);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }
}
