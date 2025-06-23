package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业抽查检查 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Data
@Schema
public class DataCompanySpotCheckQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "检查实施机关")
    private String checkInstitute;


    @Schema(description = "检查实施机关公司id")
    private Long checkInstituteCompanyId;


    @Schema(description = "检查类型")
    private String checkTypeName;


    @Schema(description = "检查日期")
    private LocalDate checkDate;
    

    @Schema(description = "检查结果")
    private String checkResult;


    @Schema(description = "数据md5")
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
