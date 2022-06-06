package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chengs
 * @description 数据表基类
 * @createTime 2020/3/30 16:50
 * @refer
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "租户id")
    private Long orgId;

    @ApiModelProperty(value = "是否删除")
    @TableLogic
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "由谁更新")
    private String updateBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
