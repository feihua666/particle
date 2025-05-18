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
 * 企业个人 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Data
@Schema
public class DataCompanyPersonVO extends AbstractBaseIdVO {

    @Schema(description = "姓名")
    private String name;
    
    @Schema(description = "证号")
    private String idNo;

	@Schema(description = "脱敏证号")
	private String idNoDesensitized;

	@Schema(description = "证号md5")
	private String idNoMd5;

	@Schema(description = "证号sha256")
	private String idNoSha256;

	@Schema(description = "证号sm3")
	private String idNoSm3;

	@Schema(description = "证号密文")
	private String idNoEncrypt;
    
    @Schema(description = "手机号")
    private String mobile;
    
    @Schema(description = "手机号运营商")
    private Long mobileOperatorDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "mobileOperatorDictId",mapValueField = "name")
    @Schema(description = "手机号运营商对应字典名称")
    private String mobileOperatorDictName;
        
    @Schema(description = "手机号1")
    private String mobile1;
    
    @Schema(description = "手机号1运营商")
    private Long mobile1OperatorDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "mobile1OperatorDictId",mapValueField = "name")
    @Schema(description = "手机号1运营商对应字典名称")
    private String mobile1OperatorDictName;
        
    @Schema(description = "手机号2")
    private String mobile2;
    
    @Schema(description = "手机号2运营商")
    private Long mobile2OperatorDictId;

	@Schema(description = "处理备注，用来标识一些处理备注")
	private String handleRemark;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "mobile2OperatorDictId",mapValueField = "name")
    @Schema(description = "手机号2运营商对应字典名称")
    private String mobile2OperatorDictName;
        
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
