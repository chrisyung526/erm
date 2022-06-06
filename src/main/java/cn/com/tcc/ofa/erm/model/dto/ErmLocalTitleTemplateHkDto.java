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
public class ErmLocalTitleTemplateHkDto implements Serializable {
    /**
     * 清单名称
     */
    @ExcelProperty(value = "清單名稱", index = 0)
    @ColumnWidth(15)
    private String titleName;

    /**
     * 清单类型,期刊,书籍,视频,其他
     */
    @ExcelProperty(value = "清單類型", index = 1)
    @ColumnWidth(15)
    private String type;

    /**
     * 清单状态,1:启动,0:禁用，填数字!
     */
    @ExcelProperty(value = "清單狀態,1:啟動,0:禁用，填數字!", index = 2)
    @ColumnWidth(30)
    private Integer status;

    /**
     * identifier
     */
    @ExcelProperty(value = "清單ISSN/ISBN", index = 3)
    @ColumnWidth(20)
    private String identifier;

    @ExcelProperty(value = "份數", index = 4)
    private Integer noOfCopies;

    @ExcelProperty(value = "是否在線查看許可證,0否,1是", index = 5)
    @ColumnWidth(30)
    private Integer isOnlineViewLicense;

    @ExcelProperty(value = "e-isbn/e-issn", index = 6)
    @ColumnWidth(20)
    private String eIsbn;

    /**
     * 出版社
     */
    @ExcelProperty(value = "出版社", index = 7)
    private String publisher;

    /**
     * 语种
     */
    @ExcelProperty(value = "語種", index = 8)
    private String language;

    /**
     * 更新频率
     */
    @ExcelProperty(value = "更新頻率", index = 9)
    @ColumnWidth(15)
    private String frequency;

    /**
     * 作者
     */
    @ExcelProperty(value = "作者", index = 10)
    @ColumnWidth(20)
    private String author;

    /**
     * 编辑，多个编辑用逗号分割
     */
    @ExcelProperty(value = "編輯", index = 11)
    @ColumnWidth(20)
    private String editor;

    /**
     * 发行版本
     */
    @ExcelProperty(value = "發行版本", index = 12)
    @ColumnWidth(15)
    private String edition;

    /**
     * 总页码
     */
    @ExcelProperty(value = "總頁碼", index = 13)
    private String pages;

    /**
     * 总数
     */
    @ExcelProperty(value = "總數", index = 14)
    private String count;

    /**
     * 发布日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "發佈日期", index = 15)
    @ColumnWidth(15)
    private Date publishDate;

    /**
     * 自定义日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "自定義日期", index = 16)
    @ColumnWidth(15)
    private Date customDate;

    /**
     * 视频年份
     */
    @ExcelProperty(value = "視頻年份", index = 17)
    @ColumnWidth(15)
    private String videoYear;

    /**
     * 播放时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ExcelProperty(value = "播放日期", index = 18)
    @ColumnWidth(15)
    private Date playingTime;

    /**
     * 清单默认地址
     */
    @ExcelProperty(value = "清單默認地址", index = 19)
    @ColumnWidth(20)
    private String outputUrl;

    /**
     * 清单自定义地址
     */
    @ExcelProperty(value = "清單自定義地址", index = 20)
    @ColumnWidth(20)
    private String customUrl;

    /**
     * 清单覆盖范围开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "清單覆蓋範圍開始日期", index = 21)
    @ColumnWidth(30)
    private Date outputCoverageStartDate;

    /**
     * 清单覆盖范围开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "清單覆蓋範圍結束日期", index = 22)
    @ColumnWidth(30)
    private Date outputCoverageEndDate;

    /**
     * 清单自定义覆盖范围开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "清單自定義覆蓋範圍開始日期", index = 23)
    @ColumnWidth(30)
    private Date customCoverageStartDate;

    /**
     * 清单自定义覆盖范围结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ExcelProperty(value = "清單自定義覆蓋範圍結束日期", index = 24)
    @ColumnWidth(30)
    private Date customCoverageEndDate;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述", index = 25)
    private String description;

    /**
     * 清单公共说明
     */
    @ExcelProperty(value = "清單公共說明", index = 26)
    @ColumnWidth(20)
    private String publicNote;

    /**
     * 公共说明是否显示在网站
     */
    @ExcelProperty(value = "公共說明是否顯示在網站0:否,1:是,填數字!", index = 27)
    @ColumnWidth(30)
    private Integer display;

    /**
     * 学科
     */
    @ExcelProperty(value = "學科", index = 28)
    private String subject;

    /**
     * 来源id
     */
    @ExcelProperty(value = "來源id", index = 29)
    private String sourceId;
}
