package cn.com.tcc.ofa.erm.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hsw
 * @date 2022/5/5 14:20
 */
@Data
@ApiModel(description = "电子资源库管理")
public class ErmLocalDatabaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "资源库名")
    private String defaultDatabaseName;

    @ApiModelProperty(value = "自定义资源库名")
    private String customDatabaseName;

    @ApiModelProperty(value = "资源库code")
    private String code;

    @ApiModelProperty(value = "资源库状态")
    private String status;

    @ApiModelProperty(value = "资源清单数量")
    private Integer titles;

    @ApiModelProperty(value = "供应商名称")
    private String providerName;

    @ApiModelProperty(value = "分组名称")
    private String collectionName;

    @ApiModelProperty(value = "审批状态")
    private Integer approvalStatus;

    @ApiModelProperty(value = "审批状态")
    private String approvalStatusStr;

    @ApiModelProperty(value = "生效状态")
    private Integer effectiveStatus;

    @ApiModelProperty(value = "生效状态")
    private String effectiveStatusStr;

    @ApiModelProperty(value = "生效时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd", timezone = "GMT+8")
    private Date effectiveTime;
}
