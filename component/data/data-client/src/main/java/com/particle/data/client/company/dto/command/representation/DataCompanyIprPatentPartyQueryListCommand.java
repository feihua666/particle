package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权当事人 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Data
@Schema
public class DataCompanyIprPatentPartyQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


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


    @Schema(description = "是否发明人")
    private Boolean isInvent;


    @Schema(description = "是否代理人")
    private Boolean isAgent;


    @Schema(description = "是否代理机构")
    private Boolean isAgency;


    @Schema(description = "是否审查员")
    private Boolean isExaminer;


    @Schema(description = "是否权利人")
    private Boolean isRight;


    @Schema(description = "是否当前权利人")
    private Boolean isCurrentRight;


    @Schema(description = "地址")
    private String address;


    @Schema(description = "区域编码")
    private String areaCode;


    @Schema(description = "类型")
    private String typeName;

	@Schema(description = "代码，主要是代理机构代码")
	private String code;


    @Schema(description = "数据md5")
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}