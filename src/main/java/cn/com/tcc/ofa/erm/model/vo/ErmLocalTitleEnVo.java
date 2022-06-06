package cn.com.tcc.ofa.erm.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 电子资源清单英文导出
 * @author hsw
 * @date 2022/5/12 16:47
 */
@Data
@ApiModel(description = "电子资源清单英文导出")
public class ErmLocalTitleEnVo implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 清单名称
     */
    @ExcelProperty(value = "Title", index = 0)
    @ColumnWidth(15)
    private String titleName;

    /**
     * identifier
     */
    @ExcelProperty(value = "ISBN/ISSN", index = 1)
    @ColumnWidth(20)
    private String identifier;

    /**
     * 清单类型,期刊,书籍,视频,其他
     */
    @ExcelProperty(value = "Type", index = 2)
    @ColumnWidth(15)
    private String type;

    /**
     * 语种
     */
    @ExcelProperty(value = "Language", index = 3)
    @ColumnWidth(15)
    private String language;

    @ExcelProperty(value = "Date(s)", index = 4)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ColumnWidth(20)
    private Date publishDate;

    /**
     * 清单状态,0:禁用,1:启用
     */
    @ExcelProperty(value = "Status", index = 5)
    @ColumnWidth(15)
    private String statusStr;

    private Integer status;

}
