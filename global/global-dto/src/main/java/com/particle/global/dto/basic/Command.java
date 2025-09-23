package com.particle.global.dto.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.light.share.mybatis.anno.Ignore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 指令基础类
 * 该指令应该是应用层门面的入参使用
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 14:42
 */
@Data
public class Command extends DTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据范围结束，数据对象编码
	 */
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Ignore
	@Schema(description = "数据范围结束",hidden = true, accessMode = Schema.AccessMode.READ_ONLY)
	private DataConstraintContext dataConstraintContext;

	/**
	 * 统一添加一个登录用户id以方便后续使用
	 */
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Ignore
	@Schema(description = "当前登录用户id",hidden = true, accessMode = Schema.AccessMode.READ_ONLY)
	private Long loginUserId;
	/**
	 * 设置
	 * dcdo = dataConstraintDataObject 的缩写
	 * @param dataConstraintDataObject
	 */
	public DataConstraintContext dcdo(String dataConstraintDataObject, String action) {
		this.dataConstraintContext = DataConstraintContext.create(dataConstraintDataObject,action);
		return this.dataConstraintContext;
	}

	/**
	 * 设置登录用户id
	 * luid = loginUserId 的缩写
	 * @param loginUserId
	 */
	public void luid(Long loginUserId) {
		this.loginUserId = loginUserId;
	}
}
