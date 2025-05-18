package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业裁判文书当事人 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentPartyPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "裁判文书表id")
    private Long companyJudgmentDocumentId;


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