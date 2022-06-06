package cn.com.tcc.ofa.erm.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 电子资源库英文导出
 * @author hsw
 * @date 2022/5/12 16:47
 */
@Data
@ApiModel(description = "电子资源库英文导出")
public class ErmLocalDatabaseEnVo implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * 资源库名称
     */
    @ExcelProperty(value = "Database Name", index = 0)
    @ColumnWidth(20)
    private String defaultDatabaseName;

    /**
     * 自定义资源库名
     */
    @ExcelProperty(value = "Custom Database Name", index = 1)
    @ColumnWidth(20)
    private String customDatabaseName;

    /**
     * 资源库code
     */
    @ExcelProperty(value = "Code", index = 2)
    @ColumnWidth(15)
    private String code;

    /**
     * 资源库状态
     */
    @ExcelProperty(value = "Status", index = 3)
    @ColumnWidth(15)
    private String status;

    /**
     * 资源清单数量
     */
    @ExcelProperty(value = "Titles", index = 4)
    private Integer titles;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "Provider", index = 5)
    @ColumnWidth(15)
    private String providerName;

    /**
     * 分组名称
     */
    @ExcelProperty(value = "Team", index = 6)
    @ColumnWidth(15)
    private String collectionName;

    /**
     * 审批状态
     */
    @ExcelProperty(value = "Approval Status", index = 7)
    @ColumnWidth(20)
    @NumberFormat("")
    private String approvalStatusStr;

    /**
     * 生效状态
     */
    @ExcelProperty(value = "Effective Status", index = 8)
    @ColumnWidth(20)
    private String effectiveStatusStr;

    /**
     * 生效时间
     */
    @ExcelProperty(value = "Effective Time", index = 9)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd", timezone = "GMT+8")
    @ColumnWidth(20)
    private Date effectiveTime;

}
