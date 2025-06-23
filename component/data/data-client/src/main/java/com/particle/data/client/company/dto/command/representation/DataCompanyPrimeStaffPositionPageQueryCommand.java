package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业主要人员职位 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Data
@Schema
public class DataCompanyPrimeStaffPositionPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业主要人员表ID")
    private Long companyPrimeStaffId;


    @Schema(description = "职位名称")
    private String positionName;


    @Schema(description = "职位")
    private Long positionDictId;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
