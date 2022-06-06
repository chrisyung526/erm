package cn.com.tcc.ofa.erm.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/5 14:14
 */
@Data
@ApiModel(description = "电子资源库")
public class ErmLocalDatabaseDto implements Serializable {
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(value = "过滤词规则")
    private Integer filterRule;

    @ApiModelProperty(value = "过滤值")
    private String filterValue;

    @ApiModelProperty(value = "资源库状态")
    private Long status;

    @ApiModelProperty(value = "资源库分组id")
    private Long collectionId;

    @ApiModelProperty(value = "发布状态")
    private Integer approvalStatus;

    @ApiModelProperty(value = "生效状态")
    private Integer effectiveStatus;

    @ApiModelProperty(value = "排序字段")
    private String sortColumns;

    @ApiModelProperty(value = "是否倒序")
    private boolean sortDesc;

    @ApiModelProperty(value = "供应商id")
    private Long providerId;

    @ApiModelProperty(value = "许可协议id")
    private Long licenseId;

    @ApiModelProperty(value = "是否查询关联的资源库,0否,1是")
    private Integer isAssociated;
}
