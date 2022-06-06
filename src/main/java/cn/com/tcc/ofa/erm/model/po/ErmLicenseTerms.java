package cn.com.tcc.ofa.erm.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 许可条款表
 * </p>
 *
 * @author hsw
 * @since 2022-05-11
 */
@Data
@TableName("erm_license_terms")
@ApiModel(value = "ErmLicenseTerms对象", description = "许可条款表")
public class ErmLicenseTerms extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 许可id不能为空
     */
    @ApiModelProperty(value = "许可id")
    // @NotNull(message = "license id cannot be empty")
    @TableField("license_id")
    private Long licenseId;

    /**
     * 授权用户不能为空
     */
    @ApiModelProperty(value = "授权用户")
    @TableField("authorized_users")
    // @NotBlank(message = "Authorized user cannot be empty")
    private String authorizedUsers;

    /**
     * 授权用户id不能为空
     */
    @ApiModelProperty(value = "授权用户id", required = true)
    @TableField("authorized_users_id")
    // @NotNull(message = "Authorized user id cannot be empty")
    private String authorizedUsersId;

    @ApiModelProperty("授权用户注意事项")
    @TableField("authorized_users_note")
    private String authorizedUsersNote;

    @ApiModelProperty("并发用户")
    @TableField("concurrent_users")
    private String concurrentUsers;

    @ApiModelProperty("并发用户注意事项")
    @TableField("concurrent_users_note")
    private String concurrentUsersNote;

    @ApiModelProperty("合理使用条款指标")
    @TableField("fair_use_clause_indicator")
    private String fairUseClauseIndicator;

    @ApiModelProperty("数据库保护覆盖子句指示器")
    @TableField("database_protection_override_clause_indicator")
    private String databaseProtectionOverrideClauseIndicator;

    @ApiModelProperty("版权所有指标")
    @TableField("all_rights_reserved_indicator")
    private String allRightsReservedIndicator;

    @ApiModelProperty("引文要求详情")
    @TableField("citation_requirement_detail")
    private String citationRequirementDetail;

    @ApiModelProperty("数字复制")
    @TableField("digitally_copy")
    private String digitallyCopy;

    @ApiModelProperty("数字复制说明")
    @TableField("digitally_copy_note")
    private String digitallyCopyNote;

    @ApiModelProperty("打印副本")
    @TableField("print_copy")
    private String printCopy;

    @ApiModelProperty("打印副本说明")
    @TableField("print_copy_note")
    private String printCopyNote;

    @ApiModelProperty("学术分享")
    @TableField("scholarly_sharing")
    private String scholarlySharing;

    @ApiModelProperty("学术分享说明")
    @TableField("scholarly_sharing_note")
    private String scholarlySharingNote;

    @ApiModelProperty("远程教育")
    @TableField("distance_learning")
    private String distanceLearning;

    @ApiModelProperty("远程教育说明")
    @TableField("distance_learning_note")
    private String distanceLearningNote;

    @ApiModelProperty("IIL基本规范")
    @TableField("ILL_general")
    private String illGeneral;

    @ApiModelProperty("IIL电子安全规范")
    @TableField("ILL_secure_electronic")
    private String illSecureElectronic;

    @ApiModelProperty("IIL电子安全规范邮件")
    @TableField("ILL_electronic")
    private String illElectronic;

    @ApiModelProperty("是否保存IIL规范记录")
    @TableField("ILL_record_keeping")
    private String illRecordKeeping;

    @ApiModelProperty("是否保存IIL规范记录说明")
    @TableField("ILL_record_keeping_note")
    private String illRecordKeepingNote;

    @ApiModelProperty("课程储备")
    @TableField("course_reserve")
    private String courseReserve;

    @ApiModelProperty("课程储备说明")
    @TableField("course_reserve_note")
    private String courseReserveNote;

    @ApiModelProperty("电子链接")
    @TableField("electronic_link")
    private String electronicLink;

    @ApiModelProperty("电子链接说明")
    @TableField("electronic_link_note")
    private String electronicLinkNote;

    @ApiModelProperty("课程打印")
    @TableField("course_pack_print")
    private String coursePackPrint;

    @ApiModelProperty("课程打印电子版")
    @TableField("course_pack_electronic")
    private String coursePackElectronic;

    @ApiModelProperty("课程说明")
    @TableField("course_pack_note")
    private String coursePackNote;

    @ApiModelProperty("远程访问")
    @TableField("remote_access")
    private String remoteAccess;

    @ApiModelProperty("远程访问说明")
    @TableField("remote_access_note")
    private String remoteAccessNote;

    @ApiModelProperty("其他使用限制(员工备注)")
    @TableField("other_use_restrictions_staff_note")
    private String otherUseRestrictionsStaffNote;

    @ApiModelProperty("其他使用限制(公示)")
    @TableField("other_use_restrictions_public_note")
    private String otherUseRestrictionsPublicNote;

    @ApiModelProperty("永久访问权")
    @TableField("perpetual_access_right")
    private String perpetualAccessRight;

    @ApiModelProperty("永久访问权控股")
    @TableField("perpetual_access_holdings")
    private String perpetualAccessHoldings;

    @ApiModelProperty("永久访问说明")
    @TableField("perpetual_access_note")
    private String perpetualAccessNote;

    @ApiModelProperty("许可终止权")
    @TableField("license_termination_right")
    private String licenseTerminationRight;

    @ApiModelProperty("许可终止条件")
    @TableField("license_termination_condition")
    private String licenseTerminationCondition;

    @ApiModelProperty("许可终止条件说明")
    @TableField("license_termination_condition_note")
    private String licenseTerminationConditionNote;

    @ApiModelProperty("许可到期提前通知天数")
    @TableField("license_notice_period_termination")
    private String licenseNoticePeriodTermination;

    @ApiModelProperty("是否提前通知")
    @TableField("is_license_notice_period_termination")
    private String isLicenseNoticePeriodTermination;

    @ApiModelProperty("终止权说明")
    @TableField("termination_right_note")
    private String terminationRightNote;

    @ApiModelProperty("终止要求")
    @TableField("termination_requirements")
    private String terminationRequirements;

    @ApiModelProperty("终止要求说明")
    @TableField("termination_requirements_note")
    private String terminationRequirementsNote;

    @ApiModelProperty("条款说明")
    @TableField("terms_note")
    private String termsNote;

    @ApiModelProperty("本地使用条款说明")
    @TableField("local_use_terms_note")
    private String localUseTermsNote;

    @ApiModelProperty("管辖法律")
    @TableField("governing_law")
    private String governingLaw;

    @ApiModelProperty("管辖权")
    @TableField("governing_jurisdiction")
    private String governingJurisdiction;

    @ApiModelProperty("适用著作权法")
    @TableField("applicable_copyright_law")
    private String applicableCopyrightLaw;

    @ApiModelProperty("违约的补救期")
    @TableField("cure_period_for_breach")
    private String curePeriodForBreach;

    @ApiModelProperty("违约的补救期是否生效")
    @TableField("is_cure_period_for_breach")
    private String isCurePeriodForBreach;

    @ApiModelProperty("续订类型")
    @TableField("renewal_type")
    private String renewalType;

    @ApiModelProperty("非更新通知期")
    @TableField("non_renewal_notice_period")
    private String nonRenewalNoticePeriod;

    @ApiModelProperty("非更新通知期是否通知")
    @TableField("is_non_renewal_notice_period")
    private String isNonRenewalNoticePeriod;

    @ApiModelProperty("存档权")
    @TableField("archiving_right")
    private String archivingRight;

    @ApiModelProperty("存档格式")
    @TableField("archiving_format")
    private String archivingFormat;

    @ApiModelProperty("存档格式Id")
    @TableField("archiving_format_id")
    private String archivingFormatId;

    @ApiModelProperty("存档说明")
    @TableField("archiving_note")
    private String archivingNote;

    @ApiModelProperty("允许预印存档")
    @TableField("pre_print_archive_allowed")
    private String prePrintArchiveAllowed;

    @ApiModelProperty("存档条件")
    @TableField("pre_print_archive_conditions")
    private String prePrintArchiveConditions;

    @ApiModelProperty("存档条件Id")
    @TableField("pre_print_archive_conditions_id")
    private String prePrintArchiveConditionsId;

    @ApiModelProperty("预印本档案限制")
    @TableField("pre_print_archive_restrictions")
    private String prePrintArchiveRestrictions;

    @ApiModelProperty("是否启用预印本档案限制")
    @TableField("is_pre_print_archive_restrictions")
    private String isPrePrintArchiveRestrictions;

    @ApiModelProperty("预印本档案限制说明")
    @TableField("pre_print_archive_note")
    private String prePrintArchiveNote;

    @ApiModelProperty("允许打印后存档")
    @TableField("post_print_archive_allowed")
    private String postPrintArchiveAllowed;

    @ApiModelProperty("印后存档条件")
    @TableField("post_print_archive_conditions")
    private String postPrintArchiveConditions;

    @ApiModelProperty("印后存档条件Id")
    @TableField("post_print_archive_conditions_id")
    private String postPrintArchiveConditionsId;

    @ApiModelProperty("印后存档限制")
    @TableField("post_print_archive_restrictions")
    private String postPrintArchiveRestrictions;

    @ApiModelProperty("是否启用印后存档限制")
    @TableField("is_post_print_archive_restrictions")
    private String isPostPrintArchiveRestrictions;

    @ApiModelProperty("印后存档限制说明")
    @TableField("post_print_archive_note")
    private String postPrintArchiveNote;

    @ApiModelProperty("图像、图形和表格的合并")
    @TableField("incorporation_of_images_figures_and_tables_right")
    private String incorporationOfImagesFiguresAndTablesRight;

    @ApiModelProperty("图像、图形和表格的合并说明")
    @TableField("incorporation_of_images_figures_and_tables_note")
    private String incorporationOfImagesFiguresAndTablesNote;

    @ApiModelProperty("公共表演权")
    @TableField("public_performance_right")
    private String publicPerformanceRight;

    @ApiModelProperty("公共表演权说明")
    @TableField("public_performance_note")
    private String publicPerformanceNote;

    @ApiModelProperty("培训材料权")
    @TableField("training_materials_right")
    private String trainingMaterialsRight;

    @ApiModelProperty("培训材料权说明")
    @TableField("training_materials_note")
    private String trainingMaterialsNote;

    @ApiModelProperty("授权用户id")
    @TableField(exist = false)
    private Long[] authorizedUsersArrLong;

    @ApiModelProperty("授权用户")
    @TableField(exist = false)
    private String[] authorizedUsersArrStr;

    @ApiModelProperty("存档格式id")
    @TableField(exist = false)
    private Integer[] archivingFormatArrInt;

    @ApiModelProperty("存档格式")
    @TableField(exist = false)
    private String[] archivingFormatArrStr;

    @ApiModelProperty("存档条件id")
    @TableField(exist = false)
    private Integer[] prePrintArchiveConditionsArrInt;

    @ApiModelProperty("存档条件")
    @TableField(exist = false)
    private String[] prePrintArchiveConditionsArrStr;

    @ApiModelProperty("印后存档条件id")
    @TableField(exist = false)
    private Integer[] postPrintArchiveConditionsArrInt;

    @ApiModelProperty("印后存档条件")
    @TableField(exist = false)
    private String[] postPrintArchiveConditionsArrStr;

}
