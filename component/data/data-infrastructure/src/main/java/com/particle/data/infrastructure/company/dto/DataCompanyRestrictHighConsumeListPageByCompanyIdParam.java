package com.particle.data.infrastructure.company.dto;

import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 根据企业id查询限制高消费 参数
 * </p>
 *
 * @author yangwei
 * @since 2025-04-22 16:29:43
 */
@Data
public class DataCompanyRestrictHighConsumeListPageByCompanyIdParam extends DTO {

    @Schema(description = "企业id")
    private Long companyId;

    @Schema(description = "当事人名称")
    private String partyName;

    public static DataCompanyRestrictHighConsumeListPageByCompanyIdParam create(Long companyId, String partyName) {
        DataCompanyRestrictHighConsumeListPageByCompanyIdParam dataCompanyCaseFilingListPageByCompanyIdParam = new DataCompanyRestrictHighConsumeListPageByCompanyIdParam();
        dataCompanyCaseFilingListPageByCompanyIdParam.companyId = (companyId);
        dataCompanyCaseFilingListPageByCompanyIdParam.partyName = (partyName);
        return dataCompanyCaseFilingListPageByCompanyIdParam;
    }
}
