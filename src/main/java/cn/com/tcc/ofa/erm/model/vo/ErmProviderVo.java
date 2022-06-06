package cn.com.tcc.ofa.erm.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/6 20:05
 */
@Data
@ApiModel(description = "许可协议")
public class ErmProviderVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty("供应商的名称")
    private String providerName;

    @ApiModelProperty("供应商代码")
    private String code;

    @ApiModelProperty(value = "资源库名称")
    private String defaultDatabaseName;
}
