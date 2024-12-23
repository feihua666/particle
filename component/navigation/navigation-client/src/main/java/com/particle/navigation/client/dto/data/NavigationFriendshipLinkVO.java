package com.particle.navigation.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 导航友情链接 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Data
@Schema
public class NavigationFriendshipLinkVO extends AbstractBaseIdVO {

    @Schema(description = "网站名称")
    private String name;

    @Schema(description = "网站标题")
    private String title;

    @Schema(description = "网站logo图标地址")
    private String logoUrl;

    @Schema(description = "网站地址")
    private String url;

    @Schema(description = "收录时间")
    private LocalDateTime collectionAt;

    @Schema(description = "是否已发布")
    private Boolean isPublished;

    @Schema(description = "下架原因")
    private String unpublishedReason;

    @Schema(description = "描述")
    private String remark;



}
