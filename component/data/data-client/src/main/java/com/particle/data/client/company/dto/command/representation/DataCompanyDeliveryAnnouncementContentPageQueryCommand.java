package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业送达公告内容 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementContentPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业送达公告id")
    private Long companyDeliveryAnnouncementId;


    @Schema(description = "公告内容")
    private String content;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
