package com.particle.data.infrastructure.company.dto;

import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 根据企业id查询商标信息 参数
 * </p>
 *
 * @author yangwei
 * @since 2025-06-16 11:50:50
 */
@Data
public class DataCompanyIprTrademarkListPageByCompanyIdParam extends DTO {

    @Schema(description = "企业id")
    private Long companyId;

    @Schema(description = "是否申请人")
    private Boolean isApplicant;

    @Schema(description = "是否代理人")
    private Boolean isAgent;

    @Schema(description = "注册号")
    private String regNo;

    @Schema(description = "申请号")
    private String applyNo;



    public static DataCompanyIprTrademarkListPageByCompanyIdParam create(Long companyId,
                                                                        Boolean isApplicant,
                                                                        Boolean isAgent,
                                                                        String regNo,
                                                                        String applyNo) {
        DataCompanyIprTrademarkListPageByCompanyIdParam command = new DataCompanyIprTrademarkListPageByCompanyIdParam();
        command.companyId = companyId;
        command.isApplicant = isApplicant;
        command.isAgent = isAgent;
        command.regNo = regNo;
        command.applyNo = applyNo;
        return command;
    }
    public static DataCompanyIprTrademarkListPageByCompanyIdParam create(Long companyId,
                                                                         String regNo,
                                                                         String applyNo) {

        return create(companyId,null,null,regNo,applyNo);
    }
}
