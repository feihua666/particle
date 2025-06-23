package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标许可信息 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicenseQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业知识产权商标表id")
    private Long companyIprTrademarkId;


    @Schema(description = "许可期号")
    private String licenseIssueNo;


    @Schema(description = "许可页码")
    private String licensePageNo;


    @Schema(description = "许可备案号")
    private String licenseFilingNo;


    @Schema(description = "许可备案公告日期")
    private LocalDate licenseFilingPublicDate;
    

    @Schema(description = "许可期限")
    private String licenseTerm;


    @Schema(description = "许可使用的商品服务")
    private String licenseGoodService;


    @Schema(description = "数据md5")
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
