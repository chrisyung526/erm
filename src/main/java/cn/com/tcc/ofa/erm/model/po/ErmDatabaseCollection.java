package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author hsw
 * @date 2022/5/5 17:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErmDatabaseCollection对象", description="电子资源库分组")
public class ErmDatabaseCollection extends BaseEntity {
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 分组名称
     */
    @ApiModelProperty(value = "分组名称")
    private String name;
}
