package cn.com.tcc.ofa.erm.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/16 11:01
 */
@Data
@ApiModel(description = "供应商下的资源库dto")
public class ErmProviderDatabaseDto implements Serializable {
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(value = "过滤词规则")
    private Integer filterRule;

    @ApiModelProperty(value = "过滤值")
    private String filterValue;

    @ApiModelProperty(value = "资源库状态")
    private Long status;

    @ApiModelProperty(value = "供应商id")
    private Long providerId;

    @ApiModelProperty(value = "排序字段")
    private String sortColumns;

    @ApiModelProperty(value = "是否倒序")
    private boolean sortDesc;
}
