package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业融资历史投资机构关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Data
@Schema
public class DataCompanyVcFinancingInvestInstitutionRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业融资表ID")
    private Long companyVcFinancingId;


    @Schema(description = "企业投资机构表id")
    private Long companyVcInvestInstitutionId;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
