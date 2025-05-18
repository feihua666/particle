package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业开庭公告内容 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementContentVO extends AbstractBaseIdVO {

    @Schema(description = "企业开庭公告id")
    private Long companyOpenCourtAnnouncementId;
    
    @Schema(description = "公告内容")
    private String content;

	@Schema(description = "数据md5,content")
	private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}