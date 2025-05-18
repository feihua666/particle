package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业限制高消费当事人 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Data
@Schema
public class DataCompanyRestrictHighConsumePartyVO extends AbstractBaseIdVO {

    @Schema(description = "限制高消费表id")
    private Long companyRestrictHighConsumeId;
    
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

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "partyRoleDictId",mapValueField = "name")
    @Schema(description = "当事人角色对应字典名称")
    private String partyRoleDictName;
        
    @Schema(description = "当事人描述信息")
    private String partyDescription;

	@Schema(description = "数据md5,party_name")
	private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}