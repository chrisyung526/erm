package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 供应商表
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Data
@TableName("erm_provider")
@ApiModel(value = "ErmProvider对象", description = "供应商表")
public class ErmProvider extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "供应商的名称", required = true)
    @TableField("provider_name")
    @NotBlank(message = "Provider name cannot be empty")
    private String providerName;

    @ApiModelProperty(value = "供应商代码", required = true)
    @TableField("`code`")
    @NotBlank(message = "Provider code cannot be empty")
    private String code;

    @ApiModelProperty(value = "供应商默认网址", required = true)
    @TableField("default_provider_url")
    @NotBlank(message = "Provider default URL cannot be empty")
    private String defaultProviderUrl;

    @ApiModelProperty("供应商自定义网址")
    @TableField("custom_provider_url")
    private String customProviderUrl;

    @ApiModelProperty("供应商下的资源库是否都使用此默认url,0否,1是")
    @TableField("is_url")
    private Integer isUrl;

    @ApiModelProperty("当前供应商对所有资源库的介绍")
    @TableField("public_all_database_note")
    private String publicAllDatabaseNote;

    @ApiModelProperty("供应商下的资源库是否都使用此介绍,0否,1是")
    @TableField("is_display")
    private Integer isDisplay;

}
