package com.particle.global.security.security.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("密码信息")
public class PasswordInfo implements Serializable {

	@ApiModelProperty("pwd id")
	private Long id;

	@ApiModelProperty("登录标识id")
	private Long identifierId;
	@ApiModelProperty("是否过期")
	private Boolean isExpired;
	@ApiModelProperty("过期原因")
	private String expiredReason;
	@ApiModelProperty("过期时间")
	private LocalDateTime expireAt;

	@ApiModelProperty("是否需要修改密码")
	private Boolean isNeedUpdate;

	@ApiModelProperty("提示修改密码消息内容")
	private String needUpdateMessage;

	@ApiModelProperty("提示修改密码消息内容")
	private LocalDateTime pwdModifiedAt;

	@ApiModelProperty("复杂度，数字越高越复杂，取值 1-100")
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
