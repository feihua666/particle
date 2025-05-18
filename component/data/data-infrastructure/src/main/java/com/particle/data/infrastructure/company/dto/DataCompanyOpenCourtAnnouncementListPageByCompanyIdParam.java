package com.particle.data.infrastructure.company.dto;

import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 根据企业id查询法院公告 参数
 * </p>
 *
 * @author yangwei
 * @since 2025/4/17 17:41
 */
@Data
public class DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam extends DTO {

    @Schema(description = "企业id")
    private Long companyId;


    @Schema(description = "案号")
    private String caseNo;

    public static DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam create(Long companyId, String caseNo) {
        DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam dataCompanyCaseFilingListPageByCompanyIdParam = new DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam();
        dataCompanyCaseFilingListPageByCompanyIdParam.companyId = (companyId);
        dataCompanyCaseFilingListPageByCompanyIdParam.caseNo = (caseNo);
        return dataCompanyCaseFilingListPageByCompanyIdParam;
    }
}
