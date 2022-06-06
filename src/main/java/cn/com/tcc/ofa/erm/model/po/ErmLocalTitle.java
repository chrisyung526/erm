package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 本地资源清单表
 * </p>
 *
 * @author hsw
 * @since 2022-05-05
 */
@TableName("erm_local_title")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErmLocalTitle对象", description="电子资源清单")
public class ErmLocalTitle extends BaseEntity {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 清单名称
     */
    @ApiModelProperty(value = "清单名称", required = true)
    @NotEmpty(message = "Title name cannot be empty")
    private String titleName;

    /**
     * 清单类型,期刊,书籍,视频,其他
     */
    @ApiModelProperty(value = "清单类型,期刊,书籍,视频,其他", required = true)
    @NotEmpty(message = "Title type cannot be empty")
    private String type;

    /**
     * 清单状态,0:禁用,1:启用
     */
    @ApiModelProperty(value = "清单状态,0:禁用,1:启用")
    private Integer status;

    /**
     * identifier
     */
    @ApiModelProperty(value = "identifier")
    private String identifier;

    @ApiModelProperty(value = "份数")
    private Integer noOfCopies;

    @ApiModelProperty(value = "是否在线查看许可证,0否,1是")
    private Integer isOnlineViewLicense;

    @ApiModelProperty(value = "e-isbn/e-issn")
    private String eIsbn;

    /**
     * 出版社
     */
    @ApiModelProperty(value = "出版社")
    private String publisher;

    /**
     * 语种
     */
    @ApiModelProperty(value = "语种")
    private String language;

    /**
     * 更新频率
     */
    @ApiModelProperty(value = "更新频率")
    private String frequency;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String author;

    /**
     * 编辑
     */
    @ApiModelProperty(value = "编辑")
    private String editor;

    /**
     * 发行版本
     */
    @ApiModelProperty(value = "发行版本")
    private String edition;

    /**
     * 总页码
     */
    @ApiModelProperty(value = "总页码")
    private String pages;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private String count;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishDate;

    /**
     * 自定义日期
     */
    @ApiModelProperty(value = "自定义日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date customDate;

    /**
     * 视频年份
     */
    @ApiModelProperty(value = "视频年份")
    private String videoYear;

    /**
     * 播放时间
     */
    @ApiModelProperty(value = "播放时间")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date playingTime;

    /**
     * 清单默认地址
     */
    @ApiModelProperty(value = "清单默认地址")
    private String outputUrl;

    /**
     * 清单自定义地址
     */
    @ApiModelProperty(value = "清单自定义地址")
    private String customUrl;

    /**
     * 清单覆盖范围开始日期
     */
    @ApiModelProperty(value = "清单覆盖范围开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date outputCoverageStartDate;

    /**
     * 清单覆盖范围开始日期
     */
    @ApiModelProperty(value = "清单覆盖范围结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date outputCoverageEndDate;

    /**
     * 清单自定义覆盖范围
     */
    @ApiModelProperty(value = "清单自定义覆盖范围开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date customCoverageStartDate;

    /**
     * 清单自定义覆盖范围
     */
    @ApiModelProperty(value = "清单自定义覆盖范围结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date customCoverageEndDate;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 清单公共说明
     */
    @ApiModelProperty(value = "清单公共说明")
    private String publicNote;

    /**
     * 公共说明是否显示在网站
     */
    @ApiModelProperty(value = "公共说明是否显示在网站")
    private Integer display;

    /**
     * 资源库id
     */
    @ApiModelProperty(value = "资源库id", required = true)
    @NotNull(message = "DatabaseId cannot be empty")
    private Long databaseId;

    /**
     * 学科
     */
    @ApiModelProperty(value = "学科")
    private String subject;

    /**
     * 来源id
     */
    @ApiModelProperty(value = "来源id")
    private String sourceId;

    @TableField(exist = false)
    private List<ErmTitleHolding> ermTitleHoldings;

    @TableField(exist = false)
    private Integer totalCount;
}
