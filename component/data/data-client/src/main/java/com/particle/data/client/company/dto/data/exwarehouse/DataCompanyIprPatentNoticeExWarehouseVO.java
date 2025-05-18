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
 * 企业知识产权专利通知书信息 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprPatentNoticeExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;
    
    @Schema(description = "通知发文日期")
    private LocalDate publicDate;
        
    @Schema(description = "通知挂号")
    private String mailNo;
    
    @Schema(description = "通知收件人姓名")
    private String receiverName;
    
    @Schema(description = "通知书类型")
    private String noticeType;
    
    @Schema(description = "通知书类型说明")
    private String noticeTypeDescription;

	@Schema(description = "数据md5,public_date + mail_no + receiver_name + notice_type + notice_type_description")
	private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}