package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>
 * 期刊分期表
 * </p>
 *
 * @author hsw
 * @since 2022-05-12
 */
@Data
@TableName("erm_title_holding")
@ApiModel(value = "ErmTitleHolding对象", description = "期刊馆藏表")
public class ErmTitleHolding extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "开始日期", required = true)
    @TableField("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "Start date cannot be empty")
    private Date startDate;

    @ApiModelProperty(value = "结束日期", required = true)
    @TableField("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "End date cannot be empty")
    private Date endDate;

    @ApiModelProperty(value = "访问url", required = true)
    @NotBlank(message = "Access url cannot be empty")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "期刊id", required = true)
    @NotNull(message = "Journal id cannot be empty")
    @TableField("title_id")
    private Long titleId;

}
