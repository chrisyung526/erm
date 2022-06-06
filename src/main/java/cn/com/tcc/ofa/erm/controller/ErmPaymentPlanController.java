package cn.com.tcc.ofa.erm.controller;

import cn.com.tcc.ofa.common.controller.BaseController;
import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.common.model.vo.RestData;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.model.po.ErmPaymentPlan;
import cn.com.tcc.ofa.erm.service.ErmPaymentPlanService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 分期管理表 前端控制器
 * </p>
 *
 * @author hsw
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/paymentPlan")
@Api(tags = "许可分期管理")
public class ErmPaymentPlanController extends BaseController {
    @Autowired
    ErmPaymentPlanService ermPaymentPlanService;

    @GetMapping("/{licenseId}")
    @ApiOperation(value = "查询许可分期详情", response = ErmPaymentPlan.class)
    public RestData getDetail(@PathVariable Long licenseId) {
        ErmPaymentPlan ermPaymentPlan = ermPaymentPlanService.getOne(
                new LambdaQueryWrapper<ErmPaymentPlan>().eq(ErmPaymentPlan::getLicenseId, licenseId));
        if (BeanUtil.isNotEmpty(ermPaymentPlan)) {
            String paymentPeriod = ermPaymentPlan.getPaymentPeriod();
            if (StrUtil.isNotBlank(paymentPeriod)) {
                ermPaymentPlan.setPaymentPeriodArr(paymentPeriod.split(","));
            }
        }
        return addRestData(ermPaymentPlan);
    }

    @PutMapping("/{licenseId}")
    @ApiOperation(value = "保存或更新许可分期")
    public RestData update(@PathVariable Long licenseId,
                           @RequestBody @Valid ErmPaymentPlan ermPaymentPlan, BindingResult result) {
        if (result.hasErrors()) {
            return this.formErrorValid(result);
        }
        ermPaymentPlan.setLicenseId(licenseId);
        String[] paymentPeriodArr = ermPaymentPlan.getPaymentPeriodArr();
        ermPaymentPlan.setPaymentPeriod(StrUtil.join(",", paymentPeriodArr));
        boolean b = ermPaymentPlanService.saveOrUpdate(ermPaymentPlan);
        if (!b) {
            throw new RestException(RestExceptionEnum.UPDATE_ERROR_CODE);
        }
        return addRestData();
    }
}
