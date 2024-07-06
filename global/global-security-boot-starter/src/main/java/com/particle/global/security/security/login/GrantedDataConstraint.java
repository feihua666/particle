package com.particle.global.security.security.login;


import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 授权的数据范围约束
 * </p>
 *
 * @author yangwei
 * @since 2022-11-28 09:54
 */
@Data
@Builder
@Schema(description = "授权的角色对应的数据范围")
public class GrantedDataConstraint implements Serializable {

	@Schema(description = "数据对象")
	private GrantedDataObject grantedDataObject;

	@Schema(description = "数据范围")
	private GrantedDataScope grantedDataScope;


	@Data
	public static class GrantedDataObject implements Serializable{

		@Schema(description = "数据对象id")
		private Long id;

		@Schema(description = "数据对象编码")
		private String code;

		@Schema(description = "数据对象名称")
		private String name;

		@Schema(description = "是否禁用")
		private Boolean isDisabled;

	}
	@Data
	public static class GrantedDataScope implements Serializable{

		@Schema(description = "数据范围id")
		private Long id;

		@Schema(description = "数据范围编码")
		private String code;

		@Schema(description = "数据范围名称")
		private String name;

		@Schema(description = "约束条件内容")
		private String constraintContent;

		/**
		 * 可取内容可参考 {@link ConstraintContentType}，建议页面管理时字典数据需要保持一致
		 */
		@Schema(description = "约束条件内容类型")
		private String constraintContentTypeDictValue;

		@Schema(description = "是否自定义")
		private Boolean isCustom;

		@Schema(description = "是否用于删除")
		private Boolean isForDelete;

		@Schema(description = "是否用于修改")
		private Boolean isForUpdate;

		@Schema(description = "是否用于查询")
		private Boolean isForQuery;

		@Schema(description = "是否用于其它")
		private Boolean isForOther;

		/**
		 * 以上基本和 {@link com.particle.dataconstraint.client.dto.data.DataScopeVO} 保持一致
		 * 以下为额外添加
		 */

		@Schema(description = "自定义的数据id")
		private List<Long> customDataIds;
	}

	/**
	 * 约束条件内容类型，可用的类型
	 */
	public static enum ConstraintContentType{
		/**
		 * 原始sql
		 */
		sql_raw,
		/**
		 * enjoy模板渲染sql
		 */
		sql_enjoy_template,
		/**
		 * groovy脚本直接处理
		 */
		groovy_script
	}
}
