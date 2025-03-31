package com.particle.global.dto.basic;

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
	@Ignore
	@Schema(description = "数据范围结束",hidden = true)
	private DataConstraintContext dataConstraintContext;

	/**
	 * 统一添加一个登录用户id以文件后续使用
	 */
	@Ignore
	@Schema(description = "当前登录用户id",hidden = true)
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
	 * 空的设置方法，防止前端传值
	 * @param dataConstraintDataObject
	 */
	public void setDataConstraintDataObject(String dataConstraintDataObject) {

	}

	/**
	 * 设置登录用户id
	 * luid = loginUserId 的缩写
	 * @param loginUserId
	 */
	public void luid(Long loginUserId) {
		this.loginUserId = loginUserId;
	}
	/**
	 * 空的设置方法，防止前端传值
	 * @param loginUserId
	 */
	public void setLoginUserId(Long loginUserId) {

	}
}
