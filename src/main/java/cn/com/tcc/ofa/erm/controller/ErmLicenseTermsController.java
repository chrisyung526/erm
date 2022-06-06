package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.model.po.ErmLicenseTerms;
import cn.com.tcc.ofa.erm.model.vo.ErmLicenseTermsVo;
import cn.com.tcc.ofa.erm.service.ErmLicenseTermsService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * <p>
 * 许可条款表 前端控制器
 * </p>
 *
 * @author hsw
 * @since 2022-05-11
 */
@RestController
@RequestMapping("licenseTerms")
@Api(tags = "许可条款管理")
public class ErmLicenseTermsController extends BaseController {
    @Autowired
    ErmLicenseTermsService ermLicenseTermsService;

    @GetMapping("/{licenseId}")
    @ApiOperation(value = "查询许可条款详情", response = ErmLicenseTerms.class)
    public RestData getDetail(@PathVariable Long licenseId) {
        ErmLicenseTerms ermLicenseTerms = ermLicenseTermsService.getOne(
                new LambdaQueryWrapper<ErmLicenseTerms>().eq(ErmLicenseTerms::getLicenseId, licenseId));
        ErmLicenseTermsVo ermLicenseTermsVo = new ErmLicenseTermsVo();
        if (ermLicenseTerms != null) {
            BeanUtil.copyProperties(ermLicenseTerms, ermLicenseTermsVo);


            String authorizedUsersId = ermLicenseTerms.getAuthorizedUsersId();
            // String[] authId = authorizedUsersId.split(",");
            // long[] longs = Arrays.stream(authId).mapToLong(Long::parseLong).toArray();
            // ermLicenseTermsVo.setAuthorizedUsersArrLong(longs);
            ermLicenseTermsVo.setAuthorizedUsersArrLong(authorizedUsersId.split(","));

            String authorizedUsers = ermLicenseTerms.getAuthorizedUsers();
            if (StrUtil.isNotBlank(authorizedUsers)) {
                ermLicenseTermsVo.setAuthorizedUsersArrStr(authorizedUsers.split(","));
            }

            String archivingFormat = ermLicenseTerms.getArchivingFormat();
            String archivingFormatId = ermLicenseTerms.getArchivingFormatId();
            if (StrUtil.isNotBlank(archivingFormat)) {
                ermLicenseTermsVo.setArchivingFormatArrStr(archivingFormat.split(","));
            }

            if (StrUtil.isNotBlank(archivingFormatId)) {
                String[] split = archivingFormatId.split(",");
                int[] ints = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                ermLicenseTermsVo.setArchivingFormatArrInt(ints);
            }

            String prePrintArchiveConditions = ermLicenseTerms.getPrePrintArchiveConditions();
            String prePrintArchiveConditionsId = ermLicenseTerms.getPrePrintArchiveConditionsId();
            if (StrUtil.isNotBlank(prePrintArchiveConditions)) {
                ermLicenseTermsVo.setPrePrintArchiveConditionsArrStr(prePrintArchiveConditions.split(","));
            }

            if (StrUtil.isNotBlank(prePrintArchiveConditionsId)) {
                String[] split = prePrintArchiveConditionsId.split(",");
                int[] ints = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                ermLicenseTermsVo.setPrePrintArchiveConditionsArrInt(ints);
            }

            String postPrintArchiveConditions = ermLicenseTerms.getPostPrintArchiveConditions();
            String postPrintArchiveConditionsId = ermLicenseTerms.getPostPrintArchiveConditionsId();
            if (StrUtil.isNotBlank(postPrintArchiveConditions)) {
                ermLicenseTermsVo.setPostPrintArchiveConditionsArrStr(postPrintArchiveConditions.split(","));
            }

            if (StrUtil.isNotBlank(postPrintArchiveConditionsId)) {
                String[] split = postPrintArchiveConditionsId.split(",");
                int[] ints = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
                ermLicenseTermsVo.setPostPrintArchiveConditionsArrInt(ints);
            }
        }
        return addRestData(ermLicenseTermsVo);
    }

    @PutMapping("/{licenseId}")
    @ApiOperation(value = "保存或更新许可条款")
    public RestData update(@PathVariable Long licenseId,
            @RequestBody @Valid ErmLicenseTerms ermLicenseTerms, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ermLicenseTerms.setLicenseId(licenseId);
        boolean b = ermLicenseTermsService.saveErmLicenseTerms(ermLicenseTerms);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }
}
