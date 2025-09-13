package com.particle.user.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 用户扩展信息 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Data
@Schema
public class UserExtraInfoVO extends AbstractBaseIdVO {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "单位名称")
    private String orgName;

    @Schema(description = "职称")
    private String jobTitle;

    @Schema(description = "个人简介")
    private String profile;

    @Schema(description = "额外自定义非查询信息")
    private String extraInfoJson;



}
