package cn.com.tcc.ofa.erm.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/11 14:23
 */
@Data
@ApiModel(description = "许可协议下关联的资源库信息")
public class ErmLicenseDatabaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "资源库名称")
    private String databaseName;

    @ApiModelProperty(value = "供应商名称")
    private String providerName;

}
