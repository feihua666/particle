package com.particle.user.client.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 用户扩展信息 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Data
@Schema
public class UserExtraInfoPageQueryCommand extends AbstractBasePageQueryCommand {



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
