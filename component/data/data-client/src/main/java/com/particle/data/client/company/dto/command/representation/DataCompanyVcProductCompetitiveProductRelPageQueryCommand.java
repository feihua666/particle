package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业融资产品竞品关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Data
@Schema
public class DataCompanyVcProductCompetitiveProductRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业融资产品表ID")
    private Long companyVcProductId;


    @Schema(description = "企业竞品id")
    private Long companyVcCompetitiveProductId;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
