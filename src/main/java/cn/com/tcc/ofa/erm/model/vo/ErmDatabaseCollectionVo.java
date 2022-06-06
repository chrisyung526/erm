package cn.com.tcc.ofa.erm.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/18 17:51
 */
@Data
@ApiModel(description = "存储资源分组名称")
public class ErmDatabaseCollectionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源库id")
    private Long databaseId;

    @ApiModelProperty("资源分子名称")
    private String collectionName;
}
