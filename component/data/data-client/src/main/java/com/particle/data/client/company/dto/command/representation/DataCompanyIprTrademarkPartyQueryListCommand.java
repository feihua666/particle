package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标当事人 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Data
@Schema
public class DataCompanyIprTrademarkPartyQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业知识产权商标表id")
    private Long companyIprTrademarkId;


    @Schema(description = "当事人名称原始名称")
    private String partyName;


    @Schema(description = "当事人名称英文名称")
    private String partyNameEn;


    @Schema(description = "当事人名称中文名称")
    private String partyNameCn;


    @Schema(description = "是否当事人为自然人")
    private Boolean isPartyNaturalPerson;


    @Schema(description = "当事人公司id")
    private Long partyCompanyId;


    @Schema(description = "当事人个人id")
    private Long partyCompanyPersonId;


    @Schema(description = "是否申请人")
    private Boolean isApplicant;


    @Schema(description = "是否代理人")
    private Boolean isAgent;


    @Schema(description = "原始地址")
    private String address;


    @Schema(description = "中文地址")
    private String addressCn;


    @Schema(description = "英文地址")
    private String addressEn;


    @Schema(description = "区域编码")
    private String areaCode;


    @Schema(description = "数据md5")
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
