package com.particle.data.infrastructure.company.dto;

import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 根据企业id查询专利信息 参数
 * </p>
 *
 * @author yangwei
 * @since 2025/4/17 17:41
 */
@Data
public class DataCompanyIprPatentListPageByCompanyIdParam extends DTO {

    @Schema(description = "企业id")
    private Long companyId;

    @Schema(description = "是否申请人")
    private Boolean isApplicant;

    @Schema(description = "是否发明人")
    private Boolean isInvent;

    @Schema(description = "是否代理人")
    private Boolean isAgent;

    @Schema(description = "是否代理机构")
    private Boolean isAgency;

    @Schema(description = "是否审查员")
    private Boolean isExaminer;

    @Schema(description = "是否权利人")
    private Boolean isRight;

    @Schema(description = "是否当前权利人")
    private Boolean isCurrentRight;

    @Schema(description = "原始申请号")
    private String applyNo;

    @Schema(description = "原始公布号")
    private String publicNo;

    public static DataCompanyIprPatentListPageByCompanyIdParam create(Long companyId,
                                                                      Boolean isApplicant,
                                                                      Boolean isInvent,
                                                                      Boolean isAgent,
                                                                      Boolean isAgency,
                                                                      Boolean isExaminer,
                                                                      Boolean isRight,
                                                                      Boolean isCurrentRight,
                                                                      String applyNo,String publicNo) {
        DataCompanyIprPatentListPageByCompanyIdParam dataCompanyCaseFilingListPageByCompanyIdParam = new DataCompanyIprPatentListPageByCompanyIdParam();
        dataCompanyCaseFilingListPageByCompanyIdParam.companyId = (companyId);
        dataCompanyCaseFilingListPageByCompanyIdParam.isApplicant = (isApplicant);
        dataCompanyCaseFilingListPageByCompanyIdParam.isInvent = (isInvent);
        dataCompanyCaseFilingListPageByCompanyIdParam.isAgent = (isAgent);
        dataCompanyCaseFilingListPageByCompanyIdParam.isAgency = (isAgency);
        dataCompanyCaseFilingListPageByCompanyIdParam.isExaminer = (isExaminer);
        dataCompanyCaseFilingListPageByCompanyIdParam.isRight = (isRight);
        dataCompanyCaseFilingListPageByCompanyIdParam.isCurrentRight = (isCurrentRight);
        dataCompanyCaseFilingListPageByCompanyIdParam.applyNo = (applyNo);
        dataCompanyCaseFilingListPageByCompanyIdParam.publicNo = (publicNo);
        return dataCompanyCaseFilingListPageByCompanyIdParam;
    }
    public static DataCompanyIprPatentListPageByCompanyIdParam create(Long companyId,
                                                                      String applyNo,String publicNo) {

        return create(companyId,null,null,null,null,null,null,null,applyNo,publicNo);
    }
}
