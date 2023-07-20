package com.particle.user.client.identifier.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class UserIdentifierQueryListCommand extends AbstractBaseQueryCommand {

    @Schema(description = "用户ID")
    private Long userId;

    @Like
    @Schema(description = "登录标识，左前缀匹配")
    private String identifier;

    @Schema(description = "授权类型,字典id")
    private Long identityTypeDictId;

    @Schema(description = "锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @Schema(description = "unionId，支持第三方登录unionId")
    private String unionId;

    @Schema(description = "是否过期")
    private Boolean isExpired;

    @Schema(description = "分组标识")
    private String groupFlag;


}
