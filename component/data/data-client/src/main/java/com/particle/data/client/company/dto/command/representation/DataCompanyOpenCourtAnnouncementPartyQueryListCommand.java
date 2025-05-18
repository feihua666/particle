package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业开庭公告当事人 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementPartyQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "开庭公告表id")
    private Long companyOpenCourtAnnouncementId;


    @Schema(description = "当事人名称")
    private String partyName;


    @Schema(description = "是否法人为自然人")
    private Boolean isPartyNaturalPerson;


    @Schema(description = "当事人公司id")
    private Long partyCompanyId;


    @Schema(description = "当事人个人id")
    private Long partyCompanyPersonId;


    @Schema(description = "当事人角色")
    private Long partyRoleDictId;


    @Schema(description = "当事人描述信息")
    private String partyDescription;

	@Schema(description = "数据md5,party_name")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}