package com.particle.lowcode.domain.generator;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.common.domain.ValueObjRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;

/**
 * <p>
 * 低代码模型 额外扩展json 值对象
 * </p>
 *
 * @author yw
 * @since 2024-06-30 19:23:23
 */
@Data
public class LowcodeModelExtJson extends ValueObjRoot {

	/**
	 * 关系模型，其中一个字段的描述信息
	 */
	private Rel relPropertyOne;

	/**
	 * 关系模型，另一个字段的描述信息
	 */
	private Rel relPropertyTwo;

	/**
	 * 转换为json字符串
	 * @return
	 */
	public String toJsonStr() {
		return JsonTool.toJsonStr(this);
	}

	public static LowcodeModelExtJson create(Rel relPropertyOne, Rel relPropertyTwo) {
		LowcodeModelExtJson lowcodeModelExtJson = new LowcodeModelExtJson();
		lowcodeModelExtJson.relPropertyOne = relPropertyOne;
		lowcodeModelExtJson.relPropertyTwo = relPropertyTwo;

		return lowcodeModelExtJson;
	}



	public static LowcodeModelExtJson create(String extJson) {
		if (StrUtil.isEmpty(extJson)) {
			return null;
		}
		return JSONUtil.toBean(extJson, LowcodeModelExtJson.class);

	}
	/**
	 * 主要用于关系模型的主体分配使用
	 */
	@Data
	public static class Rel{
		/**
		 * 实体属性名称，一般首字母小写，同{@link LowcodeModelItem#propertyName}
		 */
		private String propertyName;
		/**
		 * 实体属性名称，{@link Rel#propertyName} 首字母大写
		 */
		private String propertyNameEnEntity;

		/**
		 * 字段注释,同{@link LowcodeModelItem#commentSimple}
		 */
		private String commentSimple;


		/**
		 * 实体属性的主体注释，如：角色id，则为角色
		 */
		private String commentMain;


		/**
		 * 实体属性名称的主体名称，如：propertyName=roleId,则propertyNameMain=Role，一般首字母大写
		 */
		private String propertyNameMainEnEntity;
		/**
		 * 实体属性名称的主体名称，{@link Rel#propertyNameMainEnEntity} 的首字母小写
		 */
		private String propertyNameMainEnEntityVar;

		/**
		 * 创建一个空对象
		 * @return
		 */
		public static Rel createEmpty(){
			return new Rel();
		}
	}
}