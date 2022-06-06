package cn.com.tcc.ofa.erm.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hsw
 * @date 2022/5/6 20:05
 */
@Data
@ApiModel(description = "许可协议")
public class ErmLicenseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "许可协议名称")
    private String name;

    @ApiModelProperty(value = "资源库名称")
    private String databaseName;

    @ApiModelProperty("许可协议类型，关联下拉选项表")
    private String type;

    @ApiModelProperty("许可协议类型Id，关联下拉选项表")
    private Long typeId;

    @ApiModelProperty("许可协议状态，关联下拉选项表")
    private String status;

    @ApiModelProperty("许可协议状态Id，关联下拉选项表")
    private Long statusId;

    @ApiModelProperty("许可协议开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    @ApiModelProperty("许可协议结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    @ApiModelProperty("创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("更新日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateDate;
}
