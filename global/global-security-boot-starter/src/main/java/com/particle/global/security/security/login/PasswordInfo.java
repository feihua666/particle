package com.particle.global.security.security.login;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户的密码信息
 * </p>
 *
 * @author yangwei
 * @since 2023-05-30 16:25:32
 */
@Data
@Builder
@Schema(description = "密码信息")
public class PasswordInfo implements Serializable {

	@Schema(description = "pwd id")
	private Long id;

	@Schema(description = "登录标识id")
	private Long identifierId;
	@Schema(description = "是否过期")
	private Boolean isExpired;
	@Schema(description = "过期原因")
	private String expiredReason;
	@Schema(description = "过期时间")
	private LocalDateTime expireAt;

	@Schema(description = "是否需要修改密码")
	private Boolean isNeedUpdate;

	@Schema(description = "提示修改密码消息内容")
	private String needUpdateMessage;

	@Schema(description = "提示修改密码消息内容")
	private LocalDateTime pwdModifiedAt;

	@Schema(description = "复杂度，数字越高越复杂，取值 1-100")
	private Integer complexity;

	public static PasswordInfo create(Long id,
									  Long identifierId,
									  Boolean isExpired,
									  String expiredReason,
									  LocalDateTime expireAt,
									  Boolean isNeedUpdate,
									  String needUpdateMessage,
									  LocalDateTime pwdModifiedAt,
									  Integer complexity
								  ) {
		return PasswordInfo.builder().id(id)
				.identifierId(identifierId)
				.isExpired(isExpired)
				.expiredReason(expiredReason)
				.expireAt(expireAt)
				.isNeedUpdate(isNeedUpdate)
				.needUpdateMessage(needUpdateMessage)
				.pwdModifiedAt(pwdModifiedAt)
				.complexity(complexity)
				.build();
	}
}
