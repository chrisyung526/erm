package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 许可协议和资源库中间表
 * </p>
 *
 * @author hsw
 * @since 2022-05-06
 */
@Data
@TableName("erm_database_license_ref")
@ApiModel(value = "ErmDatabaseLicenseRef对象", description = "许可协议和资源库中间表")
public class ErmDatabaseLicenseRef {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("资源库id")
    @TableField("database_id")
    private Long databaseId;

    @ApiModelProperty("许可协议id")
    @TableField("license_id")
    private Long licenseId;

    @ApiModelProperty("是否在用,0:否，1:是")
    @TableField("is_prevailing")
    private Integer isPrevailing;

}
