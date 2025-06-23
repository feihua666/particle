package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业主要人员 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Data
@Schema
public class DataCompanyPrimeStaffQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "姓名")
    private String staffName;


    @Schema(description = "个人表ID")
    private Long staffCompanyPersonId;


    @Schema(description = "职位名称")
    private String positionNames;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;









}
