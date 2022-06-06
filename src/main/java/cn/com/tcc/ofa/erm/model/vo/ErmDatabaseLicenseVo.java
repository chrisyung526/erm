package cn.com.tcc.ofa.erm.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hsw
 * @date 2022/5/11 14:23
 */
@Data
@ApiModel(description = "资源库下关联的许可协议信息")
public class ErmDatabaseLicenseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty("许可协议名称")
    private String name;

    @ApiModelProperty("许可协议类型")
    private String type;

    @ApiModelProperty("许可协议状态")
    private String status;

    @ApiModelProperty("许可协议开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    @ApiModelProperty("许可协议结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    @ApiModelProperty("是否在用,0:否，1:是")
    private Integer isPrevailing;

}
