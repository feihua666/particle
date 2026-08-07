package com.particle.crawler.client.definition.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 爬虫项目 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Data
@Schema
public class CrawlerProjectVO extends AbstractBaseIdVO {

    @Schema(description = "项目名称")
    private String name;

    @Schema(description = "配置参数json")
    private String configJson;

    @Schema(description = "归属用户id")
    private Long userId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "userId",mapValueField = "nickname")
    @Schema(description = "归属用户昵称")
    private String userNickname;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "userId",mapValueField = "avatar")
    @Schema(description = "归属用户头像")
    private String userAvatar;

    @Schema(description = "是否公开")
    private Boolean isPublic;

    @Schema(description = "描述")
    private String remark;



}
