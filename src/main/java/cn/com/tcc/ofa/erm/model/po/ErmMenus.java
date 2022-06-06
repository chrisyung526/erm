package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author hsw
 * @date 2022/5/5 19:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErmMenus对象", description="下拉选项")
public class ErmMenus extends BaseEntity {
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 最上层类型
     */
    @ApiModelProperty(value = "最上层类型")
    private Integer type;

    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private Integer fatherId;

    /**
     * 下拉项名称
     */
    @ApiModelProperty(value = "下拉项名称", required = true)
    @NotBlank(message = "Menus item name cannot be empty")
    private String itemName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

}
