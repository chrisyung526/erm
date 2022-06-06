package cn.com.tcc.ofa.erm.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hsw
 * @date 2022/5/7 11:03
 */
@Data
@ApiModel(description = "新增许可协议")
public class AddErmLicenseDto implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 许可协议名称不能为空
     */
    @ApiModelProperty(value = "许可协议名称", required = true)
    @NotBlank(message = "License agreement name cannot be empty")
    private String name;

    /**
     * 请选择许可协议类型
     */
    @ApiModelProperty(value = "许可协议类型Id,关联下拉选项表", required = true)
    @NotNull(message = "Please select a license agreement type")
    private Long typeId;

    @ApiModelProperty("是否是模板，0否，1是")
    private Integer isTemplate;

    @ApiModelProperty("供应商许可url")
    private String vendorLicenseUrl;

    @ApiModelProperty("供应许可url访问日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date vendorLicenseUrlDateAccessed;

    @ApiModelProperty("第二个供应商许可url")
    private String secondVendorLicenseUrl;

    @ApiModelProperty("本地许可url")
    private String localLicenseUrl;

    @ApiModelProperty("第二个本地许可url")
    private String secondLocalLicenseUrl;

    @ApiModelProperty("许可协议存放的物理地址，关联下拉选项表")
    private Long physicalLocation;

    /**
     * 请选择许可协议状态
     */
    @ApiModelProperty(value = "许可协议状态Id，关联下拉选项表", required = true)
    @NotNull(message = "Please select a license agreement status")
    private Long statusId;

    @ApiModelProperty("许可协议替换人")
    private String licenseReplacesBy;

    @ApiModelProperty("许可协议替换")
    private String licenseReplaces;

    @ApiModelProperty("许可协议执行日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date executionDate;

    /**
     * 请选择许可协议开始日期
     */
    @ApiModelProperty(value = "许可协议开始日期", required = true)
    @NotNull(message = "Please select a license agreement start date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    /**
     * 请选择许可协议结束日期
     */
    @ApiModelProperty(value = "许可协议结束日期", required = true)
    @NotNull(message = "Please select a license agreement end date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    @ApiModelProperty("许可说明")
    private String licenseNote;
}
