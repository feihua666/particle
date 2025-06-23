package com.particle.data.adapter.company.dataapi.api;

import com.particle.common.adapter.api.AbstractBaseApiAdapter;
import com.particle.data.client.company.dto.command.representation.exwarehouse.*;
import com.particle.data.client.company.dto.data.exwarehouse.*;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.portal.OpenapiExecutePortalService;
import com.particle.global.openapi.collect.OpenapiCollectTool;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 公司数据服务接口 专门为开放接口提供入口
 * 基础路径 {@link DataCompanyDataOpenApiController#API_REQUEST_MAPPING} 中 dc 表示 data_company 的缩写，为了缩短路径
 * </p>
 *
 * @author yangwei
 * @since 2025/4/23 16:54
 */
@Tag(name = "企业数据服务开放接口")
@RestController
@RequestMapping(DataCompanyDataOpenApiController.API_REQUEST_MAPPING)
public class DataCompanyDataOpenApiController extends AbstractBaseApiAdapter {
    public static final String API_REQUEST_MAPPING = "/openapi/dc";

    @Autowired
    private OpenapiExecutePortalService openapiExecutePortalService;


    @PreAuthorize("hasAuthority('company:openapi:basic')")
    @Operation(summary = "企业基本信息接口")
    @PostMapping("/basic")
    public SingleResponse<DataCompanyBasicExWarehouseVO> basic(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,@RequestBody DataCompanyBasicExWarehouseQueryCommand dataCompanyBasicExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyBasicExWarehouseQueryCommand, null);
    }

    @PreAuthorize("hasAuthority('company:openapi:annualReportAll')")
    @Operation(summary = "企业年报信息接口")
    @PostMapping("/annualReportAll")
    public PageResponse<DataCompanyAnnualReportAllExWarehouseVO> annualReportAll(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                              @RequestBody DataCompanyAnnualReportExWarehouseQueryCommand dataCompanyAnnualReportExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyAnnualReportExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:caseFilling')")
    @Operation(summary = "企业立案信息接口")
    @PostMapping("/caseFilling")
    public PageResponse<DataCompanyCaseFilingExWarehouseVO> caseFilling(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                        @RequestBody DataCompanyCaseFilingExWarehouseQueryCommand dataCompanyCaseFilingExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyCaseFilingExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:courtAnnouncement')")
    @Operation(summary = "企业法院公告信息接口")
    @PostMapping("/courtAnnouncement")
    public PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> courtAnnouncement(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                     @RequestBody DataCompanyCourtAnnouncementExWarehouseQueryCommand dataCompanyCourtAnnouncementExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyCourtAnnouncementExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:discreditedJudgmentDebtor')")
    @Operation(summary = "企业征信被执行人信息接口")
    @PostMapping("/discreditedJudgmentDebtor")
    public PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> discreditedJudgmentDebtor(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                                     @RequestBody DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:honorQualification')")
    @Operation(summary = "企业荣誉资质信息接口")
    @PostMapping("/honorQualification")
    public PageResponse<DataCompanyHonorQualificationExWarehouseVO> honorQualification(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       @RequestBody DataCompanyHonorQualificationExWarehouseQueryCommand dataCompanyHonorQualificationExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyHonorQualificationExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:judgmentDebtor')")
    @Operation(summary = "企业被执行人信息接口")
    @PostMapping("/judgmentDebtor")
    public PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> judgmentDebtor(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                               @RequestBody DataCompanyJudgmentDebtorExWarehouseQueryCommand dataCompanyJudgmentDebtorExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyJudgmentDebtorExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:judgmentDocument')")
    @Operation(summary = "企业裁判文书信息接口")
    @PostMapping("/judgmentDocument")
    public PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> judgmentDocument(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                   @RequestBody DataCompanyJudgmentDocumentExWarehouseQueryCommand dataCompanyJudgmentDocumentExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyJudgmentDocumentExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:openCourtAnnouncement')")
    @Operation(summary = "企业开庭公告信息接口")
    @PostMapping("/openCourtAnnouncement")
    public PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> openCourtAnnouncement(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                             @RequestBody DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:punishment')")
    @Operation(summary = "企业行政处罚信息接口")
    @PostMapping("/punishment")
    public PageResponse<DataCompanyPunishmentExWarehouseVO> punishment(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                       @RequestBody DataCompanyPunishmentExWarehouseQueryCommand dataCompanyPunishmentExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyPunishmentExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:restrictHighConsume')")
    @Operation(summary = "企业限制高消费信息接口")
    @PostMapping("/restrictHighConsume")
    public PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> restrictHighConsume(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                         @RequestBody DataCompanyRestrictHighConsumeExWarehouseQueryCommand dataCompanyRestrictHighConsumeExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyRestrictHighConsumeExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:seriousIllegal')")
    @Operation(summary = "企业严重违法信息接口")
    @PostMapping("/seriousIllegal")
    public PageResponse<DataCompanySeriousIllegalExWarehouseVO> seriousIllegal(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                               @RequestBody DataCompanySeriousIllegalExWarehouseQueryCommand dataCompanySeriousIllegalExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanySeriousIllegalExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:shareholder')")
    @Operation(summary = "企业股东信息接口")
    @PostMapping("/shareholder")
    public PageResponse<DataCompanyShareholderExWarehouseVO> shareholder(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                         @RequestBody DataCompanyShareholderExWarehouseQueryCommand dataCompanyShareholderExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyShareholderExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:vcFinancing')")
    @Operation(summary = "企业融资信息接口")
    @PostMapping("/vcFinancing")
    public PageResponse<DataCompanyVcFinancingExWarehouseVO> vcFinancing(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                         @RequestBody DataCompanyVcFinancingExWarehouseQueryCommand dataCompanyVcFinancingExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyVcFinancingExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:vcProduct')")
    @Operation(summary = "企业融资产品信息接口")
    @PostMapping("/vcProduct")
    public PageResponse<DataCompanyVcProductExWarehouseVO> vcProduct(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                     @RequestBody DataCompanyVcProductExWarehouseQueryCommand dataCompanyVcProductExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyVcProductExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:iprPatentAll')")
    @Operation(summary = "企业知识产权专利信息接口")
    @PostMapping("/iprPatentAll")
    public PageResponse<DataCompanyIprPatentAllExWarehouseVO> iprPatentAll(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                     @RequestBody DataCompanyIprPatentExWarehouseQueryCommand dataCompanyIprPatentExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyIprPatentExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:abnormal')")
    @Operation(summary = "企业经营异常信息接口")
    @PostMapping("/abnormal")
    public PageResponse<DataCompanyAbnormalExWarehouseVO> abnormal(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                   @RequestBody DataCompanyAbnormalExWarehouseQueryCommand dataCompanyAbnormalExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyAbnormalExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:statistic')")
    @Operation(summary = "企业统计信息接口")
    @PostMapping("/statistic")
    public SingleResponse<DataCompanyStatisticExWarehouseVO> basic(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                   @RequestBody DataCompanyStatisticExWarehouseQueryCommand dataCompanyStatisticExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyStatisticExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:companyAll')")
    @Operation(summary = "企业全貌接口")
    @PostMapping("/companyAll")
    public SingleResponse<DataCompanyAllExWarehouseVO> companyAll(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                   @RequestBody DataCompanyAllExWarehouseQueryCommand dataCompanyAllExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:iprTrademarkAll')")
    @Operation(summary = "企业知识产权商标信息接口")
    @PostMapping("/iprTrademarkAll")
    public PageResponse<DataCompanyIprTrademarkAllExWarehouseVO> iprTrademarkAll(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                   @RequestBody DataCompanyIprTrademarkExWarehouseQueryCommand dataCompanyIprTrademarkExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyIprTrademarkExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:iprSoftwareCopyright')")
    @Operation(summary = "企业知识产权软件著作接口")
    @PostMapping("/iprSoftwareCopyright")
    public PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> iprSoftwareCopyright(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                            @RequestBody DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:iprWorkCopyright')")
    @Operation(summary = "企业知识产权作品著作接口")
    @PostMapping("/iprWorkCopyright")
    public PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> iprWorkCopyright(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                @RequestBody DataCompanyIprWorkCopyrightExWarehouseQueryCommand dataCompanyIprWorkCopyrightExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyIprWorkCopyrightExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:iprGeogra')")
    @Operation(summary = "企业知识产权地理标识接口")
    @PostMapping("/iprGeogra")
    public PageResponse<DataCompanyIprGeograExWarehouseVO> iprGeogra(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                     @RequestBody DataCompanyIprGeograExWarehouseQueryCommand dataCompanyIprGeograExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyIprGeograExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:iprIntegratedCircuit')")
    @Operation(summary = "企业知识产权集成电路接口")
    @PostMapping("/iprIntegratedCircuit")
    public PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> iprIntegratedCircuit(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                           @RequestBody DataCompanyIprIntegratedCircuitExWarehouseQueryCommand dataCompanyIprIntegratedCircuitExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyIprIntegratedCircuitExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:iprPlantVariety')")
    @Operation(summary = "企业知识产权植物新品种接口")
    @PostMapping("/iprPlantVariety")
    public PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> iprPlantVariety(@RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                 @RequestBody DataCompanyIprPlantVarietyExWarehouseQueryCommand dataCompanyIprPlantVarietyExWarehouseQueryCommand){
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyIprPlantVarietyExWarehouseQueryCommand, null);
    }
    @PreAuthorize("hasAuthority('company:openapi:administrativeLicense')")
    @Operation(summary = "企业行政许可接口")
    @PostMapping("/administrativeLicense")
    public PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> administrativeLicense(
            @RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
            @RequestBody DataCompanyAdministrativeLicenseExWarehouseQueryCommand dataCompanyAdministrativeLicenseExWarehouseQueryCommand) {
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyAdministrativeLicenseExWarehouseQueryCommand, null);
    }

    @PreAuthorize("hasAuthority('company:openapi:deliveryAnnouncement')")
    @Operation(summary = "企业送达公告接口")
    @PostMapping("/deliveryAnnouncement")
    public PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> deliveryAnnouncement(
            @RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
            @RequestBody DataCompanyDeliveryAnnouncementExWarehouseQueryCommand dataCompanyDeliveryAnnouncementExWarehouseQueryCommand) {
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyDeliveryAnnouncementExWarehouseQueryCommand, null);
    }

    @PreAuthorize("hasAuthority('company:openapi:endCase')")
    @Operation(summary = "企业终本案件接口")
    @PostMapping("/endCase")
    public PageResponse<DataCompanyEndCaseExWarehouseVO> endCase(
            @RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
            @RequestBody DataCompanyEndCaseExWarehouseQueryCommand dataCompanyEndCaseExWarehouseQueryCommand) {
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyEndCaseExWarehouseQueryCommand, null);
    }

    @PreAuthorize("hasAuthority('company:openapi:equityPledge')")
    @Operation(summary = "企业股权质押接口")
    @PostMapping("/equityPledge")
    public PageResponse<DataCompanyEquityPledgeExWarehouseVO> equityPledge(
            @RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
            @RequestBody DataCompanyEquityPledgeExWarehouseQueryCommand dataCompanyEquityPledgeExWarehouseQueryCommand) {
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyEquityPledgeExWarehouseQueryCommand, null);
    }

    @PreAuthorize("hasAuthority('company:openapi:iprPledge')")
    @Operation(summary = "企业知识产权质押接口")
    @PostMapping("/iprPledge")
    public PageResponse<DataCompanyIprPledgeExWarehouseVO> iprPledge(
            @RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
            @RequestBody DataCompanyIprPledgeExWarehouseQueryCommand dataCompanyIprPledgeExWarehouseQueryCommand) {
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyIprPledgeExWarehouseQueryCommand, null);
    }

    @PreAuthorize("hasAuthority('company:openapi:primeStaff')")
    @Operation(summary = "企业主要人员接口")
    @PostMapping("/primeStaff")
    public PageResponse<DataCompanyPrimeStaffExWarehouseVO> primeStaff(
            @RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
            @RequestBody DataCompanyPrimeStaffExWarehouseQueryCommand dataCompanyPrimeStaffExWarehouseQueryCommand) {
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanyPrimeStaffExWarehouseQueryCommand, null);
    }

    @PreAuthorize("hasAuthority('company:openapi:spotCheck')")
    @Operation(summary = "企业抽查检查接口")
    @PostMapping("/spotCheck")
    public PageResponse<DataCompanySpotCheckExWarehouseVO> spotCheck(
            @RequestBody DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
            @RequestBody DataCompanySpotCheckExWarehouseQueryCommand dataCompanySpotCheckExWarehouseQueryCommand) {
        return doExecute(dataCompanyExWarehouseQueryCommand, dataCompanySpotCheckExWarehouseQueryCommand, null);
    }
    /**
     * 执行开放接口
     * @param param
     * @param ex1Param
     * @param ex2Param
     * @return
     * @param <T>
     */
    private <T> T doExecute(Object param,Object ex1Param,Object ex2Param) {
        OpenapiCommand openapiCommand = OpenapiCommand.create(param);
        openapiCommand.setEx1Param(ex1Param);
        openapiCommand.setEx2Param(ex2Param);
        // 理论上这里不可能为 null，否则可能是一个bug
        OpenapiContext context = OpenapiCollectTool.getContext();
        return openapiExecutePortalService.execute(openapiCommand,context);
    }
}
