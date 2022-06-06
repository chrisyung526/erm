package cn.com.tcc.ofa.erm.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 供应商表
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Data
@ApiModel(description = "供应商dto")
public class ErmProviderDto implements Serializable {
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(value = "过滤词规则")
    private Integer filterRule;

    @ApiModelProperty(value = "过滤值")
    private String filterValue;

    @ApiModelProperty(value = "资源库id")
    private Long databaseId;

    @ApiModelProperty(value = "排序字段")
    private String sortColumns;

    @ApiModelProperty(value = "是否倒序")
    private boolean sortDesc;

}
