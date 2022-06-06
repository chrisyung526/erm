package cn.com.tcc.ofa.erm.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hsw
 * @date 2022/5/9 19:02
 */
@Data
public class ErmLocalTitleTemplateEnDto implements Serializable {
    /**
     * 清单名称
     */
    @ExcelProperty(value = "Title", index = 0)
    @ColumnWidth(15)
    private String titleName;

    /**
     * 清单类型,期刊,书籍,视频,其他
     */
    @ExcelProperty(value = "Type", index = 1)
    @ColumnWidth(15)
    private String type;

    /**
     * 清单状态,1:启动,0:禁用，填数字!
     */
    @ExcelProperty(value = "Status,1:Enabled,0:Disabled，Please enter the number!", index = 2)
    @ColumnWidth(30)
    private Integer status;

    /**
     * identifier
     */
    @ExcelProperty(value = "Title ISSN/ISBN", index = 3)
    @ColumnWidth(20)
    private String identifier;

    @ExcelProperty(value = "Fraction", index = 4)
    private Integer noOfCopies;

    @ExcelProperty(value = "Whether to view the license online,0:No,1:Yes", index = 5)
    @ColumnWidth(30)
    private Integer isOnlineViewLicense;

    @ExcelProperty(value = "e-isbn/e-issn", index = 6)
    @ColumnWidth(20)
    private String eIsbn;

    /**
     * 出版社
     */
    @ExcelProperty(value = "Publisher", index = 7)
    private String publisher;

    /**
     * 语种
     */
    @ExcelProperty(value = "Language", index = 8)
    private String language;

    /**
     * 更新频率
     */
    @ExcelProperty(value = "Update frequency", index = 9)
    @ColumnWidth(15)
    private String frequency;

    /**
     * 作者，多个作者用逗号分割
     */
    @ExcelProperty(value = "Author", index = 10)
    @ColumnWidth(15)
    private String author;

    /**
     * 编辑，多个编辑用逗号分割
     */
    @ExcelProperty(value = "Edits", index = 11)
    @ColumnWidth(15)
    private String editor;

    /**
     * 发行版本
     */
    @ExcelProperty(value = "Release version", index = 12)
    @ColumnWidth(15)
    private String edition;

    /**
     * 总页码
     */
    @ExcelProperty(value = "Pages", index = 13)
    private String pages;

    /**
     * 总数
     */
    @ExcelProperty(value = "Count", index = 14)
    private String count;

    /**
     * 发布日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "Publish Date", index = 15)
    @ColumnWidth(15)
    private Date publishDate;

    /**
     * 自定义日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "Custom Date", index = 16)
    @ColumnWidth(15)
    private Date customDate;

    /**
     * 视频年份
     */
    @ExcelProperty(value = "Video Year", index = 17)
    @ColumnWidth(15)
    private String videoYear;

    /**
     * 播放时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ExcelProperty(value = "Playing Time", index = 18)
    @ColumnWidth(15)
    private Date playingTime;

    /**
     * 清单默认地址
     */
    @ExcelProperty(value = "Output Url", index = 19)
    @ColumnWidth(20)
    private String outputUrl;

    /**
     * 清单自定义地址
     */
    @ExcelProperty(value = "Custom Url", index = 20)
    @ColumnWidth(20)
    private String customUrl;

    /**
     * 清单覆盖范围开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "Output Coverage Start Date", index = 21)
    @ColumnWidth(30)
    private Date outputCoverageStartDate;

    /**
     * 清单覆盖范围开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "Output Coverage End Date", index = 22)
    @ColumnWidth(30)
    private Date outputCoverageEndDate;

    /**
     * 清单自定义覆盖范围开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "Custom Coverage Start Date", index = 23)
    @ColumnWidth(30)
    private Date customCoverageStartDate;

    /**
     * 清单自定义覆盖范围结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "Custom Coverage End Date", index = 24)
    @ColumnWidth(30)
    private Date customCoverageEndDate;

    /**
     * 描述
     */
    @ExcelProperty(value = "Description", index = 25)
    private String description;

    /**
     * 清单公共说明
     */
    @ExcelProperty(value = "Public Note", index = 26)
    @ColumnWidth(20)
    private String publicNote;

    /**
     * 公共说明是否显示在网站
     */
    @ExcelProperty(value = "display,0:No,1:Yes,Please enter the number!", index = 27)
    @ColumnWidth(30)
    private Integer display;

    /**
     * 学科
     */
    @ExcelProperty(value = "Subject", index = 28)
    private String subject;

    /**
     * 来源id
     */
    @ExcelProperty(value = "Source Id", index = 29)
    private String sourceId;
}
