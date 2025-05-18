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
 * 企业知识产权专利证书信息 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprPatentCertificateExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;
    
    @Schema(description = "专利证书发文日")
    private LocalDate publicDate;
        
    @Schema(description = "专利证书挂号")
    private String mailNo;
    
    @Schema(description = "专利证书收件人姓名")
    private String receiverName;
    
    @Schema(description = "专利证书收件人地址")
    private String receiverAddress;

	@Schema(description = "数据md5,public_date + mail_no + receiver_name + receiver_address")
	private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}