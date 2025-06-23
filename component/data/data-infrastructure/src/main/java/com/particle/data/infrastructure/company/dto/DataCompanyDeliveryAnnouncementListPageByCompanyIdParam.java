package com.particle.data.infrastructure.company.dto;

import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 根据企业id查询送达公告 参数
 * </p>
 *
 * @author yangwei
 * @since 2025-06-20 16:58:04
 */
@Data
public class DataCompanyDeliveryAnnouncementListPageByCompanyIdParam extends DTO {

    @Schema(description = "企业id")
    private Long companyId;

    @Schema(description = "案号")
    private String caseNo;

    public static DataCompanyDeliveryAnnouncementListPageByCompanyIdParam create(Long companyId, String caseNo) {
        DataCompanyDeliveryAnnouncementListPageByCompanyIdParam dataCompanyCaseFilingListPageByCompanyIdParam = new DataCompanyDeliveryAnnouncementListPageByCompanyIdParam();
        dataCompanyCaseFilingListPageByCompanyIdParam.companyId = (companyId);
        dataCompanyCaseFilingListPageByCompanyIdParam.caseNo = (caseNo);
        return dataCompanyCaseFilingListPageByCompanyIdParam;
    }
}
