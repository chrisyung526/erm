package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 分期管理表
 * </p>
 *
 * @author hsw
 * @since 2022-05-11
 */
@Data
@TableName("erm_payment_plan")
@ApiModel(value = "ErmPaymentPlan对象", description = "分期管理表")
public class ErmPaymentPlan extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("总花费")
    @TableField("total_cost")
    private String totalCost;

    @ApiModelProperty("货币单位")
    @TableField("currency")
    private Long currency;

    @ApiModelProperty("汇率")
    @TableField("exchange_rate")
    private String exchangeRate;

    @ApiModelProperty("汇率日期")
    @TableField("exchange_rate_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date exchangeRateDate;

    @ApiModelProperty("当地货币")
    @TableField("local_currency")
    private String localCurrency;

    @ApiModelProperty("转换后的当地总花费")
    @TableField("local_cost")
    private String localCost;

    @ApiModelProperty("是否定期发送电子邮件")
    @TableField("is_send_email_regularly")
    private Integer isSendEmailRegularly;

    @ApiModelProperty("分期数")
    @TableField("frequency")
    private String frequency;

    @ApiModelProperty("提前通知天数")
    @TableField("advance_notice_days")
    private Integer advanceNoticeDays;

    @ApiModelProperty("付款日期")
    @TableField("payment_period")
    private String paymentPeriod;

    @ApiModelProperty("付款日期")
    @TableField(exist = false)
    private String[] paymentPeriodArr;

    @ApiModelProperty("许可id")
    @TableField("license_id")
    private Long licenseId;

}
