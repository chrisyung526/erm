package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>
 * 许可协议表
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Data
@TableName("erm_license")
@ApiModel(value = "ErmLicense对象", description = "许可协议表")
public class ErmLicense extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 许可协议名称不能为空
     */
    @ApiModelProperty(value = "许可协议名称", required = true)
    @NotBlank(message = "License agreement name cannot be empty")
    @TableField("`name`")
    private String name;

    /**
     * 请选择许可协议类型
     */
    @ApiModelProperty(value = "许可协议类型", required = true)
    @NotNull(message = "Please select a license agreement type")
    @TableField("`type`")
    private Long type;

    @ApiModelProperty("是否是模板，0否，1是")
    @TableField("is_template")
    private Integer isTemplate;

    @ApiModelProperty("供应商许可url")
    @TableField("vendor_license_url")
    private String vendorLicenseUrl;

    @ApiModelProperty("供应许可url访问日期")
    @TableField("vendor_license_url_date_accessed")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date vendorLicenseUrlDateAccessed;

    @ApiModelProperty("第二个供应商许可url")
    @TableField("second_vendor_license_url")
    private String secondVendorLicenseUrl;

    @ApiModelProperty("本地许可url")
    @TableField("local_license_url")
    private String localLicenseUrl;

    @ApiModelProperty("第二个本地许可url")
    @TableField("second_local_license_url")
    private String secondLocalLicenseUrl;

    @ApiModelProperty("许可协议存放的物理地址")
    @TableField("physical_location")
    private Integer physicalLocation;

    /**
     * 请选择许可协议状态
     */
    @ApiModelProperty(value = "许可协议状态，关联下拉选项表", required = true)
    @NotNull(message = "Please select a license agreement status")
    @TableField("`status`")
    private Long status;

    /**
     * 请选择审核人
     */
    @ApiModelProperty(value = "审核人", required = true)
    @NotNull(message = "Please select a reviewer")
    @TableField("reviewer")
    private Long reviewer;

    @ApiModelProperty("是否向审核人发送邮件，0否，1是")
    @TableField("is_send_email_reviewer")
    private Integer isSendEmailReviewer;

    @ApiModelProperty("审核说明")
    @TableField("reviewer_note")
    private String reviewerNote;

    @ApiModelProperty("许可协议替换人")
    @TableField("license_replaces_by")
    private String licenseReplacesBy;

    @ApiModelProperty("许可协议替换")
    @TableField("license_replaces")
    private String licenseReplaces;

    @ApiModelProperty("许可协议执行日期")
    @TableField("execution_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date executionDate;

    /**
     * 请选择许可协议开始日期
     */
    @ApiModelProperty(value = "许可协议开始日期", required = true)
    @TableField("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "Please select a license agreement start date")
    private Date startDate;

    /**
     * 请选择许可协议开始日期
     */
    @ApiModelProperty(value = "许可协议结束日期", required = true)
    @TableField("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "Please select a license agreement end date")
    private Date endDate;

    @ApiModelProperty("提前通知天数")
    @TableField("advance_notify_days")
    private Integer advanceNotifyDays;

    @ApiModelProperty("是否定时发送邮件，0否，1是")
    @TableField("is_send_email_regularly")
    private Integer isSendEmailRegularly;

    @ApiModelProperty("许可期限")
    @TableField("license_duration")
    private Integer licenseDuration;

    @ApiModelProperty("许可说明")
    @TableField("license_note")
    private String licenseNote;

}
