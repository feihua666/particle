package com.particle.data.common.tool;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 处理md5相关的工具类
 * </p>
 *
 * @author yangwei
 * @since 2025/4/11 09:45
 */
public class SomeMd5Tool {
    /**
     * 企业年报行政许可 唯一数据md5
     * @param fileName
     * @return
     */
    public static String dataCompanyAnnualReportAdministrativeLicensetDataMd5(
            String fileName) {
        boolean hasValue = StrUtil.isNotEmpty(fileName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(fileName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业年报变更 唯一数据md5
     * @param changeItemName
     * @param contentBefore
     * @param contentAfter
     * @param changeDate
     * @return
     */
    public static String dataCompanyAnnualReportChangeDataMd5(
            String changeItemName,
            String contentBefore,
            String contentAfter,
            LocalDate changeDate) {
        boolean hasValue = StrUtil.isNotEmpty(changeItemName) || StrUtil.isNotEmpty(contentBefore) || StrUtil.isNotEmpty(contentAfter) || changeDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(changeItemName));
            stringBuilder.append(StrUtil.nullToEmpty(contentBefore));
            stringBuilder.append(StrUtil.nullToEmpty(contentAfter));
            String changeDateStr = LocalDateTimeUtil.formatNormal(changeDate);
            stringBuilder.append(StrUtil.nullToEmpty(changeDateStr));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }

    /**
     * 企业年报股权变更 唯一数据md5
     * @param shareholderName
     * @param percentBefore
     * @param percentAfter
     * @param changeDate
     * @return
     */
    public static String dataCompanyAnnualReportEquityDataMd5(
            String shareholderName,
            BigDecimal percentBefore,
            BigDecimal percentAfter,
            LocalDate changeDate
    ) {
        boolean hasValue = StrUtil.isNotEmpty(shareholderName) || percentBefore != null || percentAfter != null || changeDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(shareholderName));
            stringBuilder.append(StrUtil.nullToEmpty(percentBefore == null ? "" : percentBefore.toPlainString()));
            stringBuilder.append(StrUtil.nullToEmpty(percentAfter == null ? "" : percentAfter.toPlainString()));
            String changeDateStr = LocalDateTimeUtil.formatNormal(changeDate);
            stringBuilder.append(StrUtil.nullToEmpty(changeDateStr));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }

    /**
     * 企业年报对外担保 唯一数据md5
     * @param debtorName
     * @param creditorName
     * @return
     */
    public static String dataCompanyAnnualReportForeignGuaranteeDataMd5(
            String debtorName,
            String creditorName
    ){
        boolean hasValue = StrUtil.isNotEmpty(debtorName) || StrUtil.isNotEmpty(creditorName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(debtorName));
            stringBuilder.append(StrUtil.nullToEmpty(creditorName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业年报对外投资 唯一数据md5
     * @param investCompanyName
     * @return
     */
    public static String dataCompanyAnnualReportForeignInvestDataMd5(
            String investCompanyName
    ){
        boolean hasValue = StrUtil.isNotEmpty(investCompanyName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(investCompanyName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业年报股东 唯一数据md5
     * @param shareholderName
     * @return
     */
    public static String dataCompanyAnnualReportShareholderDataMd5(
            String shareholderName
    ){
        boolean hasValue = StrUtil.isNotEmpty(shareholderName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(shareholderName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业年报网站 唯一数据md5
     * @param typeName
     * @param name
     * @param url
     * @return
     */
    public static String dataCompanyAnnualReportWebsiteDataMd5(
            String typeName,
            String name,
            String url
    ){
        boolean hasValue = StrUtil.isNotEmpty(typeName) || StrUtil.isNotEmpty(name) || StrUtil.isNotEmpty(url);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(typeName));
            stringBuilder.append(StrUtil.nullToEmpty(name));
            stringBuilder.append(StrUtil.nullToEmpty(url));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业股东 唯一数据md5
     * @param shareholderName
     * @return
     */
    public static String dataCompanyShareholderDataMd5(
            String shareholderName
    ){
        boolean hasValue = StrUtil.isNotEmpty(shareholderName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(shareholderName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业立案信息 唯一数据md5
     * @param caseNo
     * @param caseReason
     * @param executeCourtName
     * @param caseTrialProcedure
     * @return
     */
    public static String dataCompanyCaseFilingDataMd5(
            String caseNo,
            String caseReason,
            String executeCourtName,
            String caseTrialProcedure
    ){
        boolean hasValue = StrUtil.isNotEmpty(caseNo)|| StrUtil.isNotEmpty(caseReason)|| StrUtil.isNotEmpty(executeCourtName)|| StrUtil.isNotEmpty(caseTrialProcedure);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(caseNo));
            stringBuilder.append(StrUtil.nullToEmpty(caseReason));
            stringBuilder.append(StrUtil.nullToEmpty(executeCourtName));
            stringBuilder.append(StrUtil.nullToEmpty(caseTrialProcedure));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业立案信息当事人 唯一数据md5
     * @param partyName
     * @return
     */
    public static String dataCompanyCaseFilingPartyDataMd5(
            String partyName
    ){
        boolean hasValue = StrUtil.isNotEmpty(partyName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(partyName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业法院公告 唯一数据md5
     * @param announcementNo
     * @param announcementType
     * @param caseNo
     * @param caseReason
     * @param publishDate
     * @return
     */
    public static String dataCompanyCourtAnnouncementDataMd5(
            String announcementNo,
            String announcementType,
            String caseNo,
            String caseReason,
            LocalDate publishDate
    ){
        boolean hasValue = StrUtil.isNotEmpty(announcementNo)|| StrUtil.isNotEmpty(announcementType)|| StrUtil.isNotEmpty(caseNo)|| StrUtil.isNotEmpty(caseReason)|| publishDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(announcementNo));
            stringBuilder.append(StrUtil.nullToEmpty(announcementType));
            stringBuilder.append(StrUtil.nullToEmpty(caseNo));
            stringBuilder.append(StrUtil.nullToEmpty(caseReason));
            String publishDateStr = LocalDateTimeUtil.formatNormal(publishDate);
            stringBuilder.append(StrUtil.nullToEmpty(publishDateStr));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业法院公内容 唯一数据md5
     * @param content
     * @return
     */
    public static String dataCompanyCourtAnnouncementContentDataMd5(
            String content
    ){
        boolean hasValue = StrUtil.isNotEmpty(content);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(content));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业法院公告当事人 唯一数据md5
     * @param partyName
     * @return
     */
    public static String dataCompanyCourtAnnouncementPartyDataMd5(
            String partyName
    ){
        boolean hasValue = StrUtil.isNotEmpty(partyName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(partyName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业征信被执行人 唯一数据md5
     * @param caseNo
     * @param dishonestExecutedPersonName
     * @param obligation
     * @param performance
     * @return
     */
    public static String dataCompanyDiscreditedJudgmentDebtorDataMd5(
            String caseNo,
            String dishonestExecutedPersonName,
            String obligation,
            String performance
    ) {
        boolean hasValue = StrUtil.isNotEmpty(caseNo) || StrUtil.isNotEmpty(dishonestExecutedPersonName) || StrUtil.isNotEmpty(obligation) || StrUtil.isNotEmpty(performance);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(caseNo));
            stringBuilder.append(StrUtil.nullToEmpty(dishonestExecutedPersonName));
            stringBuilder.append(StrUtil.nullToEmpty(obligation));
            stringBuilder.append(StrUtil.nullToEmpty(performance));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业行政处罚 唯一数据md5
     * @param companyName
     * @param punishNo
     * @param punishContent
     * @return
     */
    public static String dataCompanyPunishmentDataMd5(
            String companyName,
            String punishNo,
            String punishContent
    ) {
        boolean hasValue = StrUtil.isNotEmpty(companyName) || StrUtil.isNotEmpty(punishNo) || StrUtil.isNotEmpty(punishContent);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(companyName));
            stringBuilder.append(StrUtil.nullToEmpty(punishNo));
            stringBuilder.append(StrUtil.nullToEmpty(punishContent));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业荣誉资质 唯一数据md5
     * @param name
     * @param grade
     * @param certificateNo
     * @param publishOffice
     * @param publishTitle
     * @return
     */
    public static String dataCompanyHonorQualificationDataMd5(
            String name,
            String grade,
            String certificateNo,
            String publishOffice,
            String publishTitle
    ){
        boolean hasValue = StrUtil.isNotEmpty(name) || StrUtil.isNotEmpty(grade)
                || StrUtil.isNotEmpty(certificateNo) || StrUtil.isNotEmpty(publishOffice) || StrUtil.isNotEmpty(publishTitle);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(name));
            stringBuilder.append(StrUtil.nullToEmpty(grade));
            stringBuilder.append(StrUtil.nullToEmpty(certificateNo));
            stringBuilder.append(StrUtil.nullToEmpty(publishOffice));
            stringBuilder.append(StrUtil.nullToEmpty(publishTitle));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }

    /**
     * 企业专利当事人 唯一数据md5
     * @param partyName
     * @param isApplicant
     * @param isInvent
     * @param isAgent
     * @param isAgency
     * @param isExaminer
     * @param isRight
     * @param isCurrentRight
     * @return
     */
    public static String dataCompanyIprPatentPartyDataMd5(
            String partyName,
            Boolean isApplicant,
            Boolean isInvent,
            Boolean isAgent,
            Boolean isAgency,
            Boolean isExaminer,
            Boolean isRight,
            Boolean isCurrentRight
    ){
        boolean hasValue = StrUtil.isNotEmpty(partyName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(partyName));
            stringBuilder.append(StrUtil.nullToEmpty(isApplicant == null ? "" : isApplicant.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isInvent == null ? "" : isInvent.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isAgent == null ? "" : isAgent.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isAgency == null ? "" : isAgency.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isExaminer == null ? "" : isExaminer.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isRight == null ? "" : isRight.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isCurrentRight == null ? "" : isCurrentRight.toString()));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业专利证书 唯一数据md5
     * @param publicDate
     * @param mailNo
     * @param receiverName
     * @param receiverAddress
     * @return
     */
    public static String dataCompanyIprPatentCertificateDataMd5(
            LocalDate publicDate,
            String mailNo,
            String receiverName,
            String receiverAddress
    ){
        boolean hasValue = publicDate != null || StrUtil.isNotEmpty(mailNo)|| StrUtil.isNotEmpty(receiverName)|| StrUtil.isNotEmpty(receiverAddress);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            String publishDateStr = LocalDateTimeUtil.formatNormal(publicDate);
            stringBuilder.append(StrUtil.nullToEmpty(publishDateStr));

            stringBuilder.append(StrUtil.nullToEmpty(mailNo));
            stringBuilder.append(StrUtil.nullToEmpty(receiverName));
            stringBuilder.append(StrUtil.nullToEmpty(receiverAddress));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业专利内容 唯一数据md5
     * @param abstractContent
     * @return
     */
    public static String dataCompanyIprPatentContentDataMd5(
            String abstractContent
    ){
        boolean hasValue = StrUtil.isNotEmpty(abstractContent);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(abstractContent));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业专利法律状态 唯一数据md5
     * @param legalStatusName
     * @param legalStatusDetail
     * @param legalStatusDate
     * @return
     */
    public static String dataCompanyIprPatentLegalStatusDataMd5(
            String legalStatusName,
            String legalStatusDetail,
            LocalDate legalStatusDate
    ){
        boolean hasValue = StrUtil.isNotEmpty(legalStatusName)|| StrUtil.isNotEmpty(legalStatusDetail)|| legalStatusDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(legalStatusName));
            stringBuilder.append(StrUtil.nullToEmpty(legalStatusDetail));

            String legalStatusDateStr = LocalDateTimeUtil.formatNormal(legalStatusDate);
            stringBuilder.append(StrUtil.nullToEmpty(legalStatusDateStr));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业专利许可信息 唯一数据md5
     * @param licenseType
     * @param filingContractNo
     * @param filingContractDate
     * @param assignor
     * @param assignee
     * @return
     */
    public static String dataCompanyIprPatentLicenseDataMd5(
            String licenseType,
            String filingContractNo,
            LocalDate filingContractDate,
            String assignor,
            String assignee
    ){
        boolean hasValue = StrUtil.isNotEmpty(licenseType)|| StrUtil.isNotEmpty(filingContractNo)|| filingContractDate != null
                || StrUtil.isNotEmpty(assignor)|| StrUtil.isNotEmpty(assignee);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(licenseType));
            stringBuilder.append(StrUtil.nullToEmpty(filingContractNo));

            String filingContractDateStr = LocalDateTimeUtil.formatNormal(filingContractDate);
            stringBuilder.append(StrUtil.nullToEmpty(filingContractDateStr));
            stringBuilder.append(StrUtil.nullToEmpty(assignor));
            stringBuilder.append(StrUtil.nullToEmpty(assignee));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }

    /**
     * 企业专利通知书信息 唯一数据md5
     * @param publicDate
     * @param mailNo
     * @param receiverName
     * @param noticeType
     * @param noticeTypeDescription
     * @return
     */
    public static String dataCompanyIprPatentNoticeDataMd5(
            LocalDate publicDate,
            String mailNo,
            String receiverName,
            String noticeType,
            String noticeTypeDescription
    ){
        boolean hasValue = publicDate != null || StrUtil.isNotEmpty(mailNo)|| StrUtil.isNotEmpty(receiverName)
                || StrUtil.isNotEmpty(noticeType)|| StrUtil.isNotEmpty(noticeTypeDescription);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            String publicDateStr = LocalDateTimeUtil.formatNormal(publicDate);
            stringBuilder.append(StrUtil.nullToEmpty(publicDateStr));

            stringBuilder.append(StrUtil.nullToEmpty(mailNo));
            stringBuilder.append(StrUtil.nullToEmpty(receiverName));
            stringBuilder.append(StrUtil.nullToEmpty(noticeType));
            stringBuilder.append(StrUtil.nullToEmpty(noticeTypeDescription));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业专利缴费信息 唯一数据md5
     * @param feeType
     * @param receiptNo
     * @param payer
     * @param handleStatus
     * @param payDate
     * @return
     */
    public static String dataCompanyIprPatentPaymentDataMd5(
            String feeType,
            String receiptNo,
            String payer,
            String handleStatus,
            LocalDate payDate
    ){
        boolean hasValue = StrUtil.isNotEmpty(feeType)|| StrUtil.isNotEmpty(receiptNo)
                || StrUtil.isNotEmpty(payer)|| StrUtil.isNotEmpty(handleStatus) || payDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(feeType));
            stringBuilder.append(StrUtil.nullToEmpty(receiptNo));
            stringBuilder.append(StrUtil.nullToEmpty(payer));
            stringBuilder.append(StrUtil.nullToEmpty(handleStatus));

            String payDateStr = LocalDateTimeUtil.formatNormal(payDate);
            stringBuilder.append(StrUtil.nullToEmpty(payDateStr));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业知识产权专利转让信息 唯一数据md5
     * @param transferType
     * @param dept
     * @param changeBeforeRightHolder
     * @param changeAfterRightHolder
     * @param currentRightHolder
     * @return
     */
    public static String dataCompanyIprPatentTransferDataMd5(
            String transferType,
            String dept,
            String changeBeforeRightHolder,
            String changeAfterRightHolder,
            String currentRightHolder
    ){
        boolean hasValue = StrUtil.isNotEmpty(transferType)
                || StrUtil.isNotEmpty(dept)
                || StrUtil.isNotEmpty(changeBeforeRightHolder)
                || StrUtil.isNotEmpty(changeAfterRightHolder)
                || StrUtil.isNotEmpty(currentRightHolder);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(transferType));
            stringBuilder.append(StrUtil.nullToEmpty(dept));
            stringBuilder.append(StrUtil.nullToEmpty(changeBeforeRightHolder));
            stringBuilder.append(StrUtil.nullToEmpty(changeAfterRightHolder));
            stringBuilder.append(StrUtil.nullToEmpty(currentRightHolder));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }

    /**
     * 企业被执行人 唯一数据md5
     * @param caseNo
     * @param executedPersonName
     * @param fileCaseDate
     * @return
     */
    public static String dataCompanyJudgmentDebtorDataMd5(
            String caseNo,
            String executedPersonName,
            LocalDate fileCaseDate
    ){
        boolean hasValue = StrUtil.isNotEmpty(caseNo)
                || StrUtil.isNotEmpty(executedPersonName)
                || fileCaseDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(caseNo));
            stringBuilder.append(StrUtil.nullToEmpty(executedPersonName));

            String fileCaseDateStr = LocalDateTimeUtil.formatNormal(fileCaseDate);
            stringBuilder.append(StrUtil.nullToEmpty(fileCaseDateStr));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业裁判文书 唯一数据md5
     * @param caseNo
     * @param caseReason
     * @param caseJudgeDate
     * @param caseTrialProcedure
     * @param caseTypeName
     * @param documentTypeName
     * @return
     */
    public static String dataCompanyJudgmentDocumentDataMd5(
            String caseNo,
            String caseReason,
            LocalDate caseJudgeDate,
            String caseTrialProcedure,
            String caseTypeName,
            String documentTypeName
    ){
        boolean hasValue = StrUtil.isNotEmpty(caseNo)
                || StrUtil.isNotEmpty(caseReason)
                || caseJudgeDate != null
                || StrUtil.isNotEmpty(caseTrialProcedure)
                || StrUtil.isNotEmpty(caseTypeName)
                || StrUtil.isNotEmpty(documentTypeName)
                ;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(caseNo));
            stringBuilder.append(StrUtil.nullToEmpty(caseReason));

            String caseJudgeDateStr = LocalDateTimeUtil.formatNormal(caseJudgeDate);
            stringBuilder.append(StrUtil.nullToEmpty(caseJudgeDateStr));

            stringBuilder.append(StrUtil.nullToEmpty(caseTrialProcedure));
            stringBuilder.append(StrUtil.nullToEmpty(caseTypeName));
            stringBuilder.append(StrUtil.nullToEmpty(documentTypeName));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业裁判文书 唯一数据md5
     * @param content
     * @return
     */
    public static String dataCompanyJudgmentDocumentContentDataMd5(
            String content
    ){
        boolean hasValue = StrUtil.isNotEmpty(content);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(content));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业裁判文书当事人 唯一数据md5
     * @param partyName
     * @return
     */
    public static String dataCompanyJudgmentDocumentPartyDataMd5(
            String partyName
    ){
        boolean hasValue = StrUtil.isNotEmpty(partyName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(partyName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }

    /**
     * 企业开庭公告 唯一数据md5
     * @param caseNo
     * @param caseReason
     * @param openDate
     * @return
     */
    public static String dataCompanyOpenCourtAnnouncementDataMd5(
            String caseNo,
            String caseReason,
            LocalDate openDate
    ){
        boolean hasValue = StrUtil.isNotEmpty(caseNo)
                || StrUtil.isNotEmpty(caseReason)
                || openDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(caseNo));
            stringBuilder.append(StrUtil.nullToEmpty(caseReason));

            String openDateStr = LocalDateTimeUtil.formatNormal(openDate);
            stringBuilder.append(StrUtil.nullToEmpty(openDateStr));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业开庭公告 唯一数据md5
     * @param content
     * @return
     */
    public static String dataCompanyOpenCourtAnnouncementContentDataMd5(
            String content
    ){
        boolean hasValue = StrUtil.isNotEmpty(content);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(content));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业开庭公告当事人 唯一数据md5
     * @param partyName
     * @return
     */
    public static String dataCompanyOpenCourtAnnouncementPartyDataMd5(
            String partyName
    ){
        boolean hasValue = StrUtil.isNotEmpty(partyName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(partyName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业限制高消费 唯一数据md5
     * @param caseNo
     * @param fileCaseDate
     * @param publishDate
     * @param executeCourtName
     * @return
     */
    public static String dataCompanyRestrictHighConsumeDataMd5(
            String caseNo,
            LocalDate fileCaseDate,
            LocalDate publishDate,
            String executeCourtName
    ){
        boolean hasValue = StrUtil.isNotEmpty(caseNo)
                || fileCaseDate != null
                || publishDate != null
                || StrUtil.isNotEmpty(executeCourtName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(caseNo));

            String fileCaseDateStr = LocalDateTimeUtil.formatNormal(fileCaseDate);
            stringBuilder.append(StrUtil.nullToEmpty(fileCaseDateStr));

            String publishDateStr = LocalDateTimeUtil.formatNormal(publishDate);
            stringBuilder.append(StrUtil.nullToEmpty(publishDateStr));

            stringBuilder.append(StrUtil.nullToEmpty(executeCourtName));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业限制高消费当事人 唯一数据md5
     * @param partyName
     * @return
     */
    public static String dataCompanyRestrictHighConsumePartyDataMd5(
            String partyName
    ){
        boolean hasValue = StrUtil.isNotEmpty(partyName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(partyName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }

    /**
     * 企业严重违法 唯一数据md5
     * @param putNo
     * @param putReason
     * @param putDate
     * @param putInstituteName
     * @return
     */
    public static String dataCompanySeriousIllegalDataMd5(
            String putNo,
            String putReason,
            LocalDate putDate,
            String putInstituteName

    ){
        boolean hasValue = StrUtil.isNotEmpty(putNo)
                || StrUtil.isNotEmpty(putReason)
                || putDate != null
                || StrUtil.isNotEmpty(putInstituteName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(putNo));
            stringBuilder.append(StrUtil.nullToEmpty(putReason));
            String putDateStr = LocalDateTimeUtil.formatNormal(putDate);
            stringBuilder.append(StrUtil.nullToEmpty(putDateStr));
            stringBuilder.append(StrUtil.nullToEmpty(putInstituteName));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业融资 唯一数据md5
     * @param productName
     * @param roundName
     * @param amount
     * @param valuation
     * @param financingDate
     * @param publishTitle
     * @return
     */
    public static String dataCompanyVcFinancingDataMd5(
            String productName,
            String roundName,
            BigDecimal amount,
            String valuation,
            LocalDate financingDate,
            String publishTitle
    ){
        boolean hasValue = StrUtil.isNotEmpty(productName)
                || StrUtil.isNotEmpty(roundName)
                || amount != null
                || StrUtil.isNotEmpty(valuation)
                || financingDate != null
                || StrUtil.isNotEmpty(publishTitle);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(productName));
            stringBuilder.append(StrUtil.nullToEmpty(roundName));
            stringBuilder.append(StrUtil.nullToEmpty(amount == null ? "" : amount.toPlainString()));
            stringBuilder.append(StrUtil.nullToEmpty(valuation));

            String financingDateStr = LocalDateTimeUtil.formatNormal(financingDate);
            stringBuilder.append(StrUtil.nullToEmpty(financingDateStr));

            stringBuilder.append(StrUtil.nullToEmpty(publishTitle));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }

    /**
     * 企业投资机构 唯一数据md5
     * @param name
     * @param enName
     * @return
     */
    public static String dataCompanyVcInvestInstitutionDataMd5(
            String name,
            String enName
    ){
        boolean hasValue = StrUtil.isNotEmpty(name)
                || StrUtil.isNotEmpty(enName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(name));
            stringBuilder.append(StrUtil.nullToEmpty(enName));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业融资产品 唯一数据md5
     * @param productName
     * @return
     */
    public static String dataCompanyVcProductDataMd5(
            String productName
    ){
        boolean hasValue = StrUtil.isNotEmpty(productName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(productName));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业经营异常 唯一数据md5
     * @param putNo
     * @return
     */
    public static String dataCompanyAbnormalDataMd5(
            String putNo,
            String putReason,
            LocalDate putDate,
            String putInstituteName

    ){
        boolean hasValue = StrUtil.isNotEmpty(putNo)
                || StrUtil.isNotEmpty(putReason)
                || putDate != null
                || StrUtil.isNotEmpty(putInstituteName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(putNo));
            stringBuilder.append(StrUtil.nullToEmpty(putReason));
            String putDateStr = LocalDateTimeUtil.formatNormal(putDate);
            stringBuilder.append(StrUtil.nullToEmpty(putDateStr));
            stringBuilder.append(StrUtil.nullToEmpty(putInstituteName));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业商标质押信息 唯一数据md5
     * @param pledgor
     * @param pledgee
     * @param pledge_public_page_no
     * @return
     */
    public static String dataCompanyIprTrademarkPledgeDataMd5(
            String pledgor,
            String pledgee,
            String pledge_public_page_no
    ){
        boolean hasValue = StrUtil.isNotEmpty(pledgor)|| StrUtil.isNotEmpty(pledgee)
                || StrUtil.isNotEmpty(pledge_public_page_no);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(pledgor));
            stringBuilder.append(StrUtil.nullToEmpty(pledgee));
            stringBuilder.append(StrUtil.nullToEmpty(pledge_public_page_no));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业商标许可信息 唯一数据md5
     * @param licenseIssueNo
     * @return
     */
    public static String dataCompanyIprTrademarkLicenseDataMd5(
            String licenseIssueNo
    ){
        boolean hasValue = StrUtil.isNotEmpty(licenseIssueNo);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(licenseIssueNo));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业商标许可人信息 唯一数据md5
     * @param licenseName
     * @param isLicensed
     * @return
     */
    public static String dataCompanyIprTrademarkLicensePersonDataMd5(
            String licenseName,
            Boolean isLicensed
    ){
        boolean hasValue = StrUtil.isNotEmpty(licenseName)|| isLicensed != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(licenseName));
            stringBuilder.append(StrUtil.nullToEmpty(isLicensed == null ? null : isLicensed.toString()));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业商标转让信息 唯一数据md5
     * @param transferIssueNo
     * @return
     */
    public static String dataCompanyIprTrademarkTransferDataMd5(
            String transferIssueNo
    ){
        boolean hasValue = StrUtil.isNotEmpty(transferIssueNo);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(transferIssueNo));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业商标转让人信息 唯一数据md5
     * @param transferName
     * @param isTransferred
     * @return
     */
    public static String dataCompanyIprTrademarkTransferPersonDataMd5(
            String transferName,
            Boolean isTransferred
    ){
        boolean hasValue = StrUtil.isNotEmpty(transferName)|| isTransferred != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(transferName));
            stringBuilder.append(StrUtil.nullToEmpty(isTransferred == null ? null : isTransferred.toString()));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业商标当事人信息 唯一数据md5
     * @param partyName
     * @param isApplicant
     * @param isAgent
     * @return
     */
    public static String dataCompanyIprTrademarkPartyDataMd5(
            String partyName,
            Boolean isApplicant,
            Boolean isAgent
    ){
        boolean hasValue = StrUtil.isNotEmpty(partyName)|| isApplicant != null || isAgent != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(StrUtil.nullToEmpty(partyName));
            stringBuilder.append(StrUtil.nullToEmpty(isApplicant == null? null : isApplicant.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isAgent == null ? null : isAgent.toString()));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业植物新品种变更信息 唯一数据md5
     * @param changeDate
     * @param isApplicant
     * @param isAgent
     * @return
     */
    public static String dataCompanyIprPlantVarietyChangeDataMd5(
            LocalDate changeDate,
            String changeBefore,
            String changeAfter,
            String changeReason,
            Boolean isName,
            Boolean isTransfer,
            Boolean isCultivate,
            Boolean isApplicant,
            Boolean isAgent,
            Boolean isAgency
    ){
        boolean hasValue = changeDate != null
                || StrUtil.isNotEmpty(changeBefore)
                || StrUtil.isNotEmpty(changeAfter)
                || StrUtil.isNotEmpty(changeReason)
                || isName != null || isTransfer != null || isCultivate != null || isApplicant != null || isAgent != null || isAgency != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            String changeDateStr = LocalDateTimeUtil.formatNormal(changeDate);
            stringBuilder.append(StrUtil.nullToEmpty(changeDateStr));
            stringBuilder.append(StrUtil.nullToEmpty(changeBefore));
            stringBuilder.append(StrUtil.nullToEmpty(changeAfter));
            stringBuilder.append(StrUtil.nullToEmpty(changeReason));
            stringBuilder.append(StrUtil.nullToEmpty(isName == null? null : isName.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isTransfer == null ? null : isTransfer.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isCultivate == null ? null : isCultivate.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isApplicant == null ? null : isApplicant.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isAgent == null ? null : isAgent.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isAgency == null ? null : isAgency.toString()));

            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业企业行政许可 唯一数据md5
     * @param decision_document_no
     * @return
     */
    public static String dataCompanyAdministrativeLicenseDataMd5(
            String decision_document_no,
            LocalDate decisionDate,
            String licenceContent,
            String licenceType,
            Boolean isDataFlagGs,
            Boolean isDataFlagXyzg
    ){
        boolean hasValue = StrUtil.isNotEmpty(decision_document_no) || decisionDate != null
                || StrUtil.isNotEmpty(licenceContent)
                || StrUtil.isNotEmpty(licenceType)
                || isDataFlagGs != null || isDataFlagXyzg != null
                ;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(decision_document_no));
            String decisionDateStr = LocalDateTimeUtil.formatNormal(decisionDate);
            stringBuilder.append(StrUtil.nullToEmpty(decisionDateStr));
            stringBuilder.append(StrUtil.nullToEmpty(licenceContent));
            stringBuilder.append(StrUtil.nullToEmpty(licenceType));
            stringBuilder.append(StrUtil.nullToEmpty(isDataFlagGs == null ? null : isDataFlagGs.toString()));
            stringBuilder.append(StrUtil.nullToEmpty(isDataFlagXyzg == null ? null : isDataFlagXyzg.toString()));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业企业送达公告 唯一数据md5
     * @param caseNo
     * @param caseReason
     * @param courtName
     * @param publishDate
     * @return
     */
    public static String dataCompanyDeliveryAnnouncementDataMd5(
            String caseNo,
            String caseReason,
            String title,
            String courtName,
            LocalDate publishDate
    ){
        boolean hasValue = StrUtil.isNotEmpty(caseNo)
                || StrUtil.isNotEmpty(caseReason)
                || StrUtil.isNotEmpty(title)
                || StrUtil.isNotEmpty(courtName)
                || publishDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(caseNo));
            stringBuilder.append(StrUtil.nullToEmpty(caseReason));
            stringBuilder.append(StrUtil.nullToEmpty(title));
            stringBuilder.append(StrUtil.nullToEmpty(courtName));
            String publishDateStr = LocalDateTimeUtil.formatNormal(publishDate);
            stringBuilder.append(StrUtil.nullToEmpty(publishDateStr));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业企业终本案件 唯一数据md5
     * @param caseNo
     * @param executedPersonName
     * @param fileCaseDate
     * @return
     */
    public static String dataCompanyEndCaseDataMd5(
            String caseNo,
            String executedPersonName,
            LocalDate fileCaseDate
    ){
        boolean hasValue = StrUtil.isNotEmpty(caseNo)
                || StrUtil.isNotEmpty(executedPersonName)
                || fileCaseDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(caseNo));
            stringBuilder.append(StrUtil.nullToEmpty(executedPersonName));
            String publishDateStr = LocalDateTimeUtil.formatNormal(fileCaseDate);
            stringBuilder.append(StrUtil.nullToEmpty(publishDateStr));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业抽查检查 唯一数据md5
     * @param checkInstitute
     * @param checkTypeName
     * @param checkDate
     * @return
     */
    public static String dataCompanySpotCheckDataMd5(
            String checkInstitute,
            String checkTypeName,
            LocalDate checkDate,
            String checkResult
    ){
        boolean hasValue = StrUtil.isNotEmpty(checkInstitute)
                || StrUtil.isNotEmpty(checkTypeName)
                || checkDate != null;
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(checkInstitute));
            stringBuilder.append(StrUtil.nullToEmpty(checkTypeName));
            String publishDateStr = LocalDateTimeUtil.formatNormal(checkDate);
            stringBuilder.append(StrUtil.nullToEmpty(publishDateStr));
            stringBuilder.append(StrUtil.nullToEmpty(checkResult));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
    /**
     * 企业送达公告当事人 唯一数据md5
     * @param partyName
     * @return
     */
    public static String dataCompanyDeliveryAnnouncementPartyDataMd5(
            String partyName
    ){
        boolean hasValue = StrUtil.isNotEmpty(partyName);
        if (hasValue) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(StrUtil.nullToEmpty(partyName));
            return DigestUtil.md5Hex(stringBuilder.toString());
        }
        return null;
    }
}
