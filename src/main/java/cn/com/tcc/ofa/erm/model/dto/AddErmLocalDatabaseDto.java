package cn.com.tcc.ofa.erm.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/6 11:02
 */
@Data
@ApiModel(description = "新增电子资源库")
public class AddErmLocalDatabaseDto implements Serializable {
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源库code")
    private String code;

    /**
     * 资源库名称不能为空
     */
    @ApiModelProperty(value = "资源库名称", required = true)
    @NotBlank(message = "Repository name cannot be empty")
    private String defaultDatabaseName;

    @ApiModelProperty(value = "分组id")
    private Long collectionId;

    /**
     * 请选择供应商
     */
    @ApiModelProperty(value = "供应商id", required = true)
    @NotNull(message = "Please select a supplier")
    private Long providerId;

    /**
     * 请选择资源库状态
     */
    @ApiModelProperty(value = "资源库状态", required = true)
    @NotNull(message = "Please select a repository status")
    private Long status;

    /**
     * 资源库默认url不能为空
     */
    @ApiModelProperty(value = "资源库默认url", required = true)
    @NotBlank(message = "The default url of the resource library cannot be empty")
    private String defaultUrl;

    @ApiModelProperty(value = "清单是否全覆盖,0否,1是")
    private Integer titleCoverage;

    @ApiModelProperty(value = "资源库默认描述")
    private String defaultDatabaseDescription;

    @ApiModelProperty(value = "资源库公开说明")
    private String publicDatabaseNote;

    @ApiModelProperty(value = "资源库公开说明是否显示在网站")
    private Integer databaseNoteDisplay;

    @ApiModelProperty(value = "全部清单的公开说明")
    private String publicAllTitlesNote;

    @ApiModelProperty(value = "资源清单公开说明是否显示在网站")
    private Integer titlesNoteDisplay;
}
