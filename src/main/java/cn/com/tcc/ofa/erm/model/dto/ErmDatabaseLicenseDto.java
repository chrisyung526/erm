package cn.com.tcc.ofa.erm.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/13 10:47
 */
@Data
@ApiModel(description = "资源库下关联的许可协议信息")
public class ErmDatabaseLicenseDto implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 资源库id不能为空
     */
    @ApiModelProperty(value = "资源库id", required = true)
    @NotNull(message = "Repository id cannot be empty")
    private Long databaseId;

    @ApiModelProperty(value = "排序字段")
    private String sortColumns;

    @ApiModelProperty(value = "是否倒序")
    private boolean sortDesc;
}
