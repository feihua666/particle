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
	 * 设置
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
}
