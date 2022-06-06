package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.dto.PageDto;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalTitleDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalTitleTemplateCnDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalTitleTemplateEnDto;
import cn.com.tcc.ofa.erm.model.dto.ErmLocalTitleTemplateHkDto;
import cn.com.tcc.ofa.erm.model.po.ErmLocalTitle;
import cn.com.tcc.ofa.erm.model.vo.ErmLocalTitleEnVo;
import cn.com.tcc.ofa.erm.service.ErmLocalTitleService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 本地资源清单表 前端控制器
 * </p>
 *
 * @author hsw
 * @since 2022-05-05
 */
@RestController
@RequestMapping("/localTitle")
@Api(tags = "本地资源清单管理")
public class ErmLocalTitleController extends BaseController {
    @Autowired
    ErmLocalTitleService ermLocalTitleService;

    @GetMapping
    @ApiOperation(value = "查询分页列表", response = ErmLocalTitle.class)
    public RestData page(ErmLocalTitleDto dto, PageDto pageDto) {
        IPage<ErmLocalTitle> page = ermLocalTitleService.pageList(dto, pageDto);
        return addRestData(page);
    }

    @GetMapping("/export")
    @ApiOperation(value = "资源清单导出")
    public void export(HttpServletRequest request, HttpServletResponse response, ErmLocalTitleDto dto) throws IOException {
        IPage<ErmLocalTitle> page = ermLocalTitleService.pageList(dto, null);
        List<ErmLocalTitle> ermLocalTitles = page.getRecords();
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = System.currentTimeMillis() + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        String jsonStr = JSONUtil.toJsonStr(ermLocalTitles);
        List<ErmLocalTitleEnVo> ermLocalTitleEnVos = JSONUtil.toList(jsonStr, ErmLocalTitleEnVo.class);
        if (CollUtil.isNotEmpty(ermLocalTitleEnVos)) {
            for (ErmLocalTitleEnVo ermLocalTitleEnVo : ermLocalTitleEnVos) {
                Integer status = ermLocalTitleEnVo.getStatus();
                if (0 == status) {
                    ermLocalTitleEnVo.setStatusStr("Disabled");
                } else {
                    ermLocalTitleEnVo.setStatusStr("Enabled");
                }
            }
        }
        // 根据用户传入字段 假设我们要忽略 date
        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("status");
        EasyExcel.write(response.getOutputStream(), ErmLocalTitleEnVo.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("title").doWrite(ermLocalTitleEnVos);
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

    @PutMapping("/status")
    @ApiOperation(value = "批量编辑状态")
    public RestData editStatus(@RequestBody Map<String, Object> map) {
        List<Long> ids = new ArrayList<>();
        Object obj = map.get("ids");
        Object status = map.get("status");
        if (BeanUtil.isEmpty(obj) || BeanUtil.isEmpty(status)) {
            throw new RestException(RestExceptionEnum.EDIT_STATUS_ERROR_CODE);
        }
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                ids.add(Long.valueOf(o.toString()));
            }
        }
        LambdaUpdateWrapper<ErmLocalTitle> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ErmLocalTitle::getStatus, Long.valueOf(status.toString())).in(ErmLocalTitle::getId, ids);
        boolean b = ermLocalTitleService.update(wrapper);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/languageAndType/{type}")
    @ApiOperation(value = "获取语种和类型")
    public RestData getLanguageAndType(@PathVariable Integer type) {
        LambdaQueryWrapper<ErmLocalTitle> queryWrapper = new LambdaQueryWrapper<>();
        if (1 == type) {
            queryWrapper.groupBy(ErmLocalTitle::getLanguage)
                    .select(ErmLocalTitle::getLanguage);
        } else if (2 == type) {
            queryWrapper.groupBy(ErmLocalTitle::getType)
                    .select(ErmLocalTitle::getType);
        }
        List<ErmLocalTitle> list = ermLocalTitleService.list(queryWrapper);
        return addRestData(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询电子资源清单详情", response = ErmLocalTitle.class)
    public RestData getDetail(@PathVariable Long id) {
        ErmLocalTitle ermLocalTitle = ermLocalTitleService.getById(id);
        return addRestData(ermLocalTitle);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "电子资源清单更新")
    public RestData update(@PathVariable Long id, @RequestBody @Valid ErmLocalTitle ermLocalTitle, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ermLocalTitle.setId(id);
        boolean b = ermLocalTitleService.updateById(ermLocalTitle);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public RestData add(@RequestBody @Valid ErmLocalTitle ermLocalTitle, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        boolean b = ermLocalTitleService.saveErmLocalTitle(ermLocalTitle);
        if (!b) {
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @GetMapping("/downloadTemplate")
    @ApiOperation(value = "下载模板")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("资源清单模板", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        String header = request.getHeader("Accept-Language");
        if ("zh-CN".equals(header)) {
            ArrayList<ErmLocalTitleTemplateCnDto> list = new ArrayList<>();
            EasyExcel.write(response.getOutputStream(), ErmLocalTitleTemplateCnDto.class).sheet("模板").doWrite(list);
        } else if ("zh-HK".equals(header)) {
            ArrayList<ErmLocalTitleTemplateHkDto> list = new ArrayList<>();
            EasyExcel.write(response.getOutputStream(), ErmLocalTitleTemplateHkDto.class).sheet("模板").doWrite(list);
        } else {
            ArrayList<ErmLocalTitleTemplateEnDto> list = new ArrayList<>();
            EasyExcel.write(response.getOutputStream(), ErmLocalTitleTemplateEnDto.class).sheet("模板").doWrite(list);
        }
    }

    @PostMapping("/uploadTitle/{databaseId}")
    @ApiOperation(value = "上传清单文件")
    public RestData uploadTitle(MultipartFile file, @PathVariable("databaseId") Long databaseId) {
        if (file == null || file.isEmpty()) {
            throw new RestException(-1, "10000");
        }
        boolean b = ermLocalTitleService.uploadTitle(file, databaseId);
        if (!b) {
            throw new RestException(RestExceptionEnum.ADD_ERROR_CODE);
        }
        return addRestData();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除资源清单")
    public RestData delete(@RequestBody List<Long> ids) {
        boolean b = ermLocalTitleService.removeByIds(ids);
        if (!b) {
            throw new RestException(RestExceptionEnum.DELETE_ERROR_CODE);
        }
        return addRestData();
    }
}
