package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利转让信息 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Data
@Schema
public class DataCompanyIprPatentTransferVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;
    
    @Schema(description = "转移类型")
    private String transferType;
    
    @Schema(description = "部门")
    private String dept;
    
    @Schema(description = "变更前权利人")
    private String changeBeforeRightHolder;
    
    @Schema(description = "变更前地址")
    private String changeBeforeAddress;
    
    @Schema(description = "变更后权利人")
    private String changeAfterRightHolder;
    
    @Schema(description = "变更后地址")
    private String changeAfterAddress;
    
    @Schema(description = "当前权利人")
    private String currentRightHolder;
    
    @Schema(description = "当前地址")
    private String currentAddress;
    
    @Schema(description = "变更生效日期")
    private LocalDate changeEffectiveDate;

	@Schema(description = "数据md5,transfer_type + dept + change_before_right_holder + change_after_right_holder + current_right_holder")
	private String dataMd5;
        
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}