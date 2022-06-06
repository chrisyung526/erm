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
@ApiModel(description = "更新许可是否在用")
public class UpdatePrevailingDto implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 资源库id不能为空
     */
    @ApiModelProperty(value = "资源库id", required = true)
    @NotNull(message = "Repository id cannot be empty")
    private Long databaseId;

    /**
     * 在用的许可协议id不能为空
     */
    @ApiModelProperty(value = "在用的许可协议id", required = true)
    @NotNull(message = "The license agreement id in use cannot be empty")
    private Long prevailingId;

    /**
     * 更新在用的许可协议id不能为空
     */
    @ApiModelProperty(value = "不在用的许可协议", required = true)
    @NotNull(message = "Update the license agreement id in use cannot be empty")
    private Long updatePrevailingId;
}
