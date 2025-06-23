package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标质押信息 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Data
@Schema
public class DataCompanyIprTrademarkPledgeQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业知识产权商标表id")
    private Long companyIprTrademarkId;


    @Schema(description = "出质人")
    private String pledgor;


    @Schema(description = "质权人")
    private String pledgee;


    @Schema(description = "质权登记起始日期")
    private LocalDate pledgeRegStartDate;
    

    @Schema(description = "质权登记截止日期")
    private LocalDate pledgeRegEndDate;
    

    @Schema(description = "质权公告页号")
    private String pledgePublicPageNo;
    

    @Schema(description = "数据md5")
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
