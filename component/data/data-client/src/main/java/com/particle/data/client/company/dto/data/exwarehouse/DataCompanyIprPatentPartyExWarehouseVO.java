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
 * 企业知识产权专利当事人 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprPatentPartyExWarehouseVO extends AbstractBaseIdVO {

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

    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq;

    @Schema(description = "数据md5")
    private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;


}
