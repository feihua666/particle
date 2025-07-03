package com.particle.cms.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 内容访问记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Data
@Schema
public class CmsContentViewRecordQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "站点id")
    private Long cmsSiteId;


    @Schema(description = "内容id")
    private Long cmsContentId;


    @Schema(description = "访问终端设备id")
    private String deviceId;


    @Schema(description = "访问终端设备ip")
    private String ip;


    @Schema(description = "访问时间")
    private LocalDateTime viewAt;
    








}
