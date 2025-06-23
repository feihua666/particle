package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权地理标识核准公告 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Data
@Schema
public class DataCompanyIprGeograApproveAnnouncementQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业知识产权地理标识id")
    private Long companyIprGeograId;


    @Schema(description = "核准公告编号")
    private String approvePublicNo;


    @Schema(description = "企业名称")
    private String companyName;


    @Schema(description = "核准地址")
    private String approveAddress;


    @Schema(description = "核准法人代表")
    private String approveLegalPersonName;


    @Schema(description = "产品名称")
    private String productName;


    @Schema(description = "核准商标")
    private String approveTrademark;


    @Schema(description = "核准日期")
    private LocalDate approveDate;
    

    @Schema(description = "核准备注")
    private String approveRemark;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
