package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业全貌 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-05 09:52:21
 */
@Data
@Schema
public class DataCompanyAllExWarehouseQueryCommand extends AbstractBaseQueryCommand {

    @NotNull(message = "企业ID 不能为空")
    @Schema(description = "企业ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "是否包括企业标识信息")
    private Boolean isIncludeCompanyUnique;

    @Schema(description = "是否包括企业基本信息")
    private Boolean isIncludeBasic;

    @Schema(description = "企业年报信息")
    private DataCompanyAnnualReportExWarehouseQueryCommand annualReportAllQuery;

    @Schema(description = "是否包括企业年报信息")
    private Boolean isIncludeAnnualReportAll;

    @Schema(description = "企业立案信息")
    private DataCompanyCaseFilingExWarehouseQueryCommand caseFilingQuery;

    @Schema(description = "是否包括企业立案信息")
    private Boolean isIncludeCaseFiling;

    @Schema(description = "企业法院公告信息")
    private DataCompanyCourtAnnouncementExWarehouseQueryCommand courtAnnouncementQuery;

    @Schema(description = "是否包括企业法院公告信息")
    private Boolean isIncludeCourtAnnouncement;

    @Schema(description = "企业荣誉资质信息")
    private DataCompanyHonorQualificationExWarehouseQueryCommand honorQualificationQuery;

    @Schema(description = "是否包括企业荣誉资质信息")
    private Boolean isIncludeHonorQualification;

    @Schema(description = "企业知识产权专利信息")
    private DataCompanyIprPatentExWarehouseQueryCommand iprPatentAllQuery;

    @Schema(description = "是否包括企业知识产权专利信息")
    private Boolean isIncludeIprPatentAll;

    @Schema(description = "企业被执行人信息")
    private DataCompanyJudgmentDebtorExWarehouseQueryCommand judgmentDebtorQuery;

    @Schema(description = "是否包括企业被执行人信息")
    private Boolean isIncludeJudgmentDebtor;

    @Schema(description = "企业失信被执行人信息")
    private DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand discreditedJudgmentDebtorQuery;

    @Schema(description = "是否包括企业失信被执行人信息")
    private Boolean isIncludeDiscreditedJudgmentDebtor;

    @Schema(description = "企业裁判文书信息")
    private DataCompanyJudgmentDocumentExWarehouseQueryCommand judgmentDocumentQuery;

    @Schema(description = "是否包括企业裁判文书信息")
    private Boolean isIncludeJudgmentDocument;

    @Schema(description = "企业开庭公告信息")
    private DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand openCourtAnnouncementQuery;

    @Schema(description = "是否包括企业开庭公告信息")
    private Boolean isIncludeOpenCourtAnnouncement;

    @Schema(description = "企业行政处罚信息")
    private DataCompanyPunishmentExWarehouseQueryCommand punishmentQuery;

    @Schema(description = "是否包括企业行政处罚信息")
    private Boolean isIncludePunishment;

    @Schema(description = "企业限制高消费信息")
    private DataCompanyRestrictHighConsumeExWarehouseQueryCommand restrictHighConsumeQuery;

    @Schema(description = "是否包括企业限制高消费信息")
    private Boolean isIncludeRestrictHighConsume;

    @Schema(description = "企业严重违法信息")
    private DataCompanySeriousIllegalExWarehouseQueryCommand seriousIllegalQuery;

    @Schema(description = "是否包括企业严重违法信息")
    private Boolean isIncludeSeriousIllegal;

    @Schema(description = "企业股东信息")
    private DataCompanyShareholderExWarehouseQueryCommand shareholderQuery;

    @Schema(description = "是否包括企业股东信息")
    private Boolean isIncludeShareholder;

    @Schema(description = "是否包括企业统计信息")
    private Boolean isIncludeStatistic;

    @Schema(description = "企业融资信息")
    private DataCompanyVcFinancingExWarehouseQueryCommand vcFinancingQuery;

    @Schema(description = "是否包括企业融资信息")
    private Boolean isIncludeVcFinancing;

    @Schema(description = "企业融资产品信息")
    private DataCompanyVcProductExWarehouseQueryCommand vcProductQuery;

    @Schema(description = "是否包括企业融资产品信息")
    private Boolean isIncludeVcProduct;

    @Schema(description = "是否包括企业投资机构信息")
    private Boolean isIncludeVcInvestInstitution;

    @Schema(description = "企业经营异常信息")
    private DataCompanyAbnormalExWarehouseQueryCommand abnormalQuery;

    @Schema(description = "是否包括企业经营异常信息")
    private Boolean isIncludeAbnormal;

    public static DataCompanyAllExWarehouseQueryCommand create(Long companyId) {
        DataCompanyAllExWarehouseQueryCommand command = new DataCompanyAllExWarehouseQueryCommand();
        command.companyId = companyId;
        return command;
    }
}
