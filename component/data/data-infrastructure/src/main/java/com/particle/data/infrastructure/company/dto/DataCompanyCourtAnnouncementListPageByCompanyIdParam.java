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
public class DataCompanyCourtAnnouncementListPageByCompanyIdParam extends DTO {

    @Schema(description = "企业id")
    private Long companyId;

    @Schema(description = "公告号")
    private String announcementNo;

    @Schema(description = "案号")
    private String caseNo;

    public static DataCompanyCourtAnnouncementListPageByCompanyIdParam create(Long companyId,String announcementNo, String caseNo) {
        DataCompanyCourtAnnouncementListPageByCompanyIdParam dataCompanyCaseFilingListPageByCompanyIdParam = new DataCompanyCourtAnnouncementListPageByCompanyIdParam();
        dataCompanyCaseFilingListPageByCompanyIdParam.companyId = (companyId);
        dataCompanyCaseFilingListPageByCompanyIdParam.announcementNo = (announcementNo);
        dataCompanyCaseFilingListPageByCompanyIdParam.caseNo = (caseNo);
        return dataCompanyCaseFilingListPageByCompanyIdParam;
    }
}
