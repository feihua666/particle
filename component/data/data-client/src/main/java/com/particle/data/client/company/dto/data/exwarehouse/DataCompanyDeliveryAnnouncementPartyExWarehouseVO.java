package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业送达公告当事人 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementPartyExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "送达公告表id")
    private Long companyDeliveryAnnouncementId;
    
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
    
    @Schema(description = "数据md5")
    private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}