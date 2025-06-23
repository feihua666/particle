package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标许可人 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicensePersonQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业知识产权商标许可表id")
    private Long companyIprTrademarkLicenseId;


    @Schema(description = "原始许可人名称")
    private String licenseName;


    @Schema(description = "中文许可人名称")
    private String licenseNameCn;


    @Schema(description = "英文许可人名称")
    private String licenseNameEn;


    @Schema(description = "是否为被许可人")
    private Boolean isLicensed;


    @Schema(description = "数据md5")
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
