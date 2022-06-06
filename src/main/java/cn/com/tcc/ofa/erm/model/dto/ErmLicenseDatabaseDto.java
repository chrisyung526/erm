package cn.com.tcc.ofa.erm.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/11 14:29
 */
@Data
@ApiModel(description = "许可协议下关联的资源库信息")
public class ErmLicenseDatabaseDto implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 许可协议id不能为空
     */
    @ApiModelProperty(value = "许可协议id", required = true)
    @NotNull(message = "License agreement id cannot be empty")
    private Long licenseId;

    @ApiModelProperty(value = "排序字段")
    private String sortColumns;

    @ApiModelProperty(value = "是否倒序")
    private boolean sortDesc;
}
