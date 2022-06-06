package cn.com.tcc.ofa.erm.model.po;

import cn.com.tcc.ofa.erm.model.po.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 资源库和分组中间表
 * </p>
 *
 * @author hsw
 * @since 2022-05-18
 */
@Getter
@Setter
@TableName("erm_database_collection_ref")
@ApiModel(value = "ErmDatabaseCollectionRef对象", description = "资源库和分组中间表")
public class ErmDatabaseCollectionRef extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("分组id")
    @TableField("collection_id")
    private Long collectionId;

    @ApiModelProperty("资源库id")
    @TableField("database_id")
    private Long databaseId;


}
