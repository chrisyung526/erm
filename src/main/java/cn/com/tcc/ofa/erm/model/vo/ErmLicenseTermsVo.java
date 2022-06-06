package cn.com.tcc.ofa.erm.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hsw
 * @date 2022/5/11 19:35
 */
@Data
@ApiModel(description = "许可条款")
public class ErmLicenseTermsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "许可id")
    private Long licenseId;

    @ApiModelProperty("授权用户")
    private String[] authorizedUsersArrStr;

    @ApiModelProperty("授权用户id")
    private String[] authorizedUsersArrLong;

    @ApiModelProperty("授权用户注意事项")
    private String authorizedUsersNote;

    @ApiModelProperty("并发用户")
    private String concurrentUsers;

    @ApiModelProperty("并发用户注意事项")
    private String concurrentUsersNote;

    @ApiModelProperty("合理使用条款指标")
    private String fairUseClauseIndicator;

    @ApiModelProperty("数据库保护覆盖子句指示器")
    private String databaseProtectionOverrideClauseIndicator;

    @ApiModelProperty("版权所有指标")
    private String allRightsReservedIndicator;

    @ApiModelProperty("引文要求详情")
    private String citationRequirementDetail;

    @ApiModelProperty("数字复制")
    private String digitallyCopy;

    @ApiModelProperty("数字复制说明")
    private String digitallyCopyNote;

    @ApiModelProperty("打印副本")
    private String printCopy;

    @ApiModelProperty("打印副本说明")
    private String printCopyNote;

    @ApiModelProperty("学术分享")
    private String scholarlySharing;

    @ApiModelProperty("学术分享说明")
    private String scholarlySharingNote;

    @ApiModelProperty("远程教育")
    private String distanceLearning;

    @ApiModelProperty("远程教育说明")
    private String distanceLearningNote;

    @ApiModelProperty("IIL基本规范")
    private String illGeneral;

    @ApiModelProperty("IIL电子安全规范")
    private String illSecureElectronic;

    @ApiModelProperty("IIL电子安全规范邮件")
    private String illElectronic;

    @ApiModelProperty("是否保存IIL规范记录")
    private String illRecordKeeping;

    @ApiModelProperty("是否保存IIL规范记录说明")
    private String illRecordKeepingNote;

    @ApiModelProperty("课程储备")
    private String courseReserve;

    @ApiModelProperty("课程储备说明")
    private String courseReserveNote;

    @ApiModelProperty("电子链接")
    private String electronicLink;

    @ApiModelProperty("电子链接说明")
    private String electronicLinkNote;

    @ApiModelProperty("课程打印")
    private String coursePackPrint;

    @ApiModelProperty("课程打印电子版")
    private String coursePackElectronic;

    @ApiModelProperty("课程说明")
    private String coursePackNote;

    @ApiModelProperty("远程访问")
    private String remoteAccess;

    @ApiModelProperty("远程访问说明")
    private String remoteAccessNote;

    @ApiModelProperty("其他使用限制(员工备注)")
    private String otherUseRestrictionsStaffNote;

    @ApiModelProperty("其他使用限制(公示)")
    private String otherUseRestrictionsPublicNote;

    @ApiModelProperty("永久访问权")
    private String perpetualAccessRight;

    @ApiModelProperty("永久访问权控股")
    private String perpetualAccessHoldings;

    @ApiModelProperty("永久访问说明")
    private String perpetualAccessNote;

    @ApiModelProperty("许可终止权")
    private String licenseTerminationRight;

    @ApiModelProperty("许可终止条件")
    private String licenseTerminationCondition;

    @ApiModelProperty("许可终止条件说明")
    private String licenseTerminationConditionNote;

    @ApiModelProperty("许可到期提前通知天数")
    private String licenseNoticePeriodTermination;

    @ApiModelProperty("是否提前通知")
    private String isLicenseNoticePeriodTermination;

    @ApiModelProperty("终止权说明")
    private String terminationRightNote;

    @ApiModelProperty("终止要求")
    private String terminationRequirements;

    @ApiModelProperty("终止要求说明")
    private String terminationRequirementsNote;

    @ApiModelProperty("条款说明")
    private String termsNote;

    @ApiModelProperty("本地使用条款说明")
    private String localUseTermsNote;

    @ApiModelProperty("管辖法律")
    private String governingLaw;

    @ApiModelProperty("管辖权")
    private String governingJurisdiction;

    @ApiModelProperty("适用著作权法")
    private String applicableCopyrightLaw;

    @ApiModelProperty("违约的补救期")
    private String curePeriodForBreach;

    @ApiModelProperty("违约的补救期是否生效")
    private String isCurePeriodForBreach;

    @ApiModelProperty("续订类型")
    private String renewalType;

    @ApiModelProperty("非更新通知期")
    private String nonRenewalNoticePeriod;

    @ApiModelProperty("非更新通知期是否通知")
    private String isNonRenewalNoticePeriod;

    @ApiModelProperty("存档权")
    private String archivingRight;

    @ApiModelProperty("存档格式")
    private String[] archivingFormatArrStr;

    @ApiModelProperty("存档格式id")
    private int[] archivingFormatArrInt;

    @ApiModelProperty("存档说明")
    private String archivingNote;

    @ApiModelProperty("允许预印存档")
    private String prePrintArchiveAllowed;

    @ApiModelProperty("存档条件索引")
    private int[] prePrintArchiveConditionsArrInt;

    @ApiModelProperty("存档条件")
    private String[] prePrintArchiveConditionsArrStr;

    @ApiModelProperty("预印本档案限制")
    private String prePrintArchiveRestrictions;

    @ApiModelProperty("是否启用预印本档案限制")
    private String isPrePrintArchiveRestrictions;

    @ApiModelProperty("预印本档案限制说明")
    private String prePrintArchiveNote;

    @ApiModelProperty("允许打印后存档")
    private String postPrintArchiveAllowed;

    @ApiModelProperty("印后存档条件索引")
    private int[] postPrintArchiveConditionsArrInt;

    @ApiModelProperty("印后存档条件")
    private String[] postPrintArchiveConditionsArrStr;

    @ApiModelProperty("印后存档限制")
    private String postPrintArchiveRestrictions;

    @ApiModelProperty("是否启用印后存档限制")
    private String isPostPrintArchiveRestrictions;

    @ApiModelProperty("印后存档限制说明")
    private String postPrintArchiveNote;

    @ApiModelProperty("图像、图形和表格的合并")
    private String incorporationOfImagesFiguresAndTablesRight;

    @ApiModelProperty("图像、图形和表格的合并说明")
    private String incorporationOfImagesFiguresAndTablesNote;

    @ApiModelProperty("公共表演权")
    private String publicPerformanceRight;

    @ApiModelProperty("公共表演权说明")
    private String publicPerformanceNote;

    @ApiModelProperty("培训材料权")
    private String trainingMaterialsRight;

    @ApiModelProperty("培训材料权说明")
    private String trainingMaterialsNote;
}
