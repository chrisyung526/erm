package cn.com.tcc.ofa.erm.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hsw
 * @date 2022/5/5 14:14
 */
@Data
@ApiModel(description = "许可协议")
public class ErmLicenseDto implements Serializable {
    private final static long serialVersionUID = 1L;

    @ApiModelProperty("许可协议类型")
    private Long type;

    @ApiModelProperty("是否是模板，0否，1是")
    private Integer isTemplate;

    @ApiModelProperty(value = "许可协议状态")
    private Long status;

    @ApiModelProperty(value = "资源库id")
    private Long databaseId;

    @ApiModelProperty(value = "是否查询关联的许可")
    private Integer isAssociated;

    @ApiModelProperty(value = "许可开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty(value = "许可结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ApiModelProperty(value = "排序字段")
    private String sortColumns;

    @ApiModelProperty(value = "是否倒序")
    private boolean sortDesc;
}
