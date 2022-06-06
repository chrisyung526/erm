package cn.com.tcc.ofa.erm.model.dto;

/**
 * @author hsw
 * @date 2022/5/9 11:44
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "电子资源清单")
public class ErmLocalTitleDto implements Serializable {
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(value = "过滤词规则")
    private Integer filterRule;

    @ApiModelProperty(value = "过滤值")
    private String filterValue;

    @ApiModelProperty(value = "语种")
    private String language;

    @ApiModelProperty(value = "资源库id", required = true)
    @NotNull(message = "Please pass in the Database id")
    private Long databaseId;

    @ApiModelProperty(value = "清单类型,期刊,书籍,视频,其他")
    private String type;

    @ApiModelProperty(value = "清单状态,0:禁用,1:启用")
    private Integer status;

    @ApiModelProperty(value = "排序字段")
    private String sortColumns;

    @ApiModelProperty(value = "是否倒序")
    private boolean sortDesc;
}
