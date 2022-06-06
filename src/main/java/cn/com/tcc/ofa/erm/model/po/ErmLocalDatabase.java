package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author hsw
 * @date 2022/4/29 10:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErmLocalDatabase对象", description="电子资源库")
public class ErmLocalDatabase extends BaseEntity {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 关联的中心资源库id
     */
    @ApiModelProperty(value = "关联的中心资源库id")
    private String centerDatabaseId;

    /**
     * 资源库名称
     */
    @ApiModelProperty(value = "资源库名称", required = true)
    @NotBlank(message = "Database name cannot be empty")
    private String defaultDatabaseName;

    /**
     * 资源库自定义名称
     */
    @ApiModelProperty(value = "资源库自定义名称")
    private String customDatabaseName;

    /**
     * 资源库code
     */
    @ApiModelProperty(value = "资源库code", required = true)
    @NotBlank(message = "Database code cannot be empty")
    private String code;

    /**
     * 资源库默认url
     */
    @ApiModelProperty(value = "资源库默认url", required = true)
    @NotBlank(message = "The default url of the resource library cannot be empty")
    private String defaultUrl;

    /**
     * 资源库自定义url
     */
    @ApiModelProperty(value = "资源库自定义url")
    private String customUrl;

    /**
     * 是否显示在网站上
     */
    @ApiModelProperty(value = "是否显示在网站上")
    private Integer isDisplayPortal;

    /**
     * 是否对所有清单都使用数据库默认url
     */
    @ApiModelProperty(value = "是否对所有清单都使用数据库默认url")
    private Integer isTitleUrl;

    /**
     * 清单是否全覆盖
     */
    @ApiModelProperty(value = "清单是否全覆盖")
    private Integer titleCoverage;

    /**
     * 资源库自定义覆盖从
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "资源库自定义覆盖从")
    private Date customCoverageDateFrom;

    /**
     * 资源库自定义覆盖止
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "资源库自定义覆盖止")
    private Date customCoverageDateTo;

    /**
     * 资源库状态,与下拉选项表关联
     */
    @ApiModelProperty(value = "资源库状态", required = true)
    @NotNull(message = "Database status cannot be empty")
    private Long status;

    /**
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id", required = true)
    @NotNull(message = "Provider cannot be empty")
    private Long providerId;

    /**
     * 分组id
     */
    @ApiModelProperty(value = "分组id")
    private Long collectionId;

    /**
     * 审批状态,1:未提交,2:审批中,3:已通过,4:已拒绝
     */
    @ApiModelProperty(value = "审批状态")
    private Integer approvalStatus;

    /**
     * 生效状态，0未生效，1已生效
     */
    @ApiModelProperty(value = "生效状态")
    private Integer effectiveStatus;

    @ApiModelProperty(value = "生效时间")
    private Date effectiveTime;

    /**
     * 资源库默认描述
     */
    @ApiModelProperty(value = "资源库默认描述")
    private String defaultDatabaseDescription;

    /**
     * 资源库自定义描述
     */
    @ApiModelProperty(value = "资源库自定义描述")
    private String customDatabaseDescription;

    /**
     * 数据库详细描述
     */
    @ApiModelProperty(value = "数据库详细描述")
    private String longDatabaseDescription;

    /**
     * 资源库公开说明
     */
    @ApiModelProperty(value = "资源库公开说明")
    private String publicDatabaseNote;

    /**
     * 资源库公开说明是否显示在网站
     */
    @ApiModelProperty(value = "资源库公开说明是否显示在网站")
    private Integer databaseNoteDisplay;

    /**
     * 全部清单的公开说明
     */
    @ApiModelProperty(value = "全部清单的公开说明")
    private String publicAllTitlesNote;

    /**
     * 资源清单公开说明是否显示在网站
     */
    @ApiModelProperty(value = "资源清单公开说明是否显示在网站")
    private Integer titlesNoteDisplay;

    /**
     * 授权设置
     */
    @ApiModelProperty(value = "授权设置")
    private String authorizationSettings;

    /**
     * 授权类型
     */
    @ApiModelProperty(value = "授权类型")
    private String authenticationType;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private String customerId;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String note;

    @ApiModelProperty(value = "书籍数量")
    @TableField(exist = false)
    private Integer books;

    @ApiModelProperty(value = "期刊数量")
    @TableField(exist = false)
    private Integer journals;

    @ApiModelProperty(value = "视频数量")
    @TableField(exist = false)
    private Integer videos;
}
