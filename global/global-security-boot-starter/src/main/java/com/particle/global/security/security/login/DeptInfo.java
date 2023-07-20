package com.particle.global.security.security.login;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户的部门信息
 * </p>
 *
 * @author yangwei
 * @since 2023-05-30 16:25:32
 */
@Data
@Builder
@Schema(description = "部门信息")
public class DeptInfo implements Serializable {

	@Schema(description = "dept id")
	private Long id;
	@Schema(description = "dept code")
	private String code;
	@Schema(description = "dept name")
	private String name;

	@Schema(description = "dept type")
	private Long typeDictId;

	@Schema(description = "dept type value")
	private String typeDictValue;


	@Schema(description = "负责人用户id")
	private Long masterUserId;

	@Schema(description = "是否虚拟部门")
	private Boolean isVirtual;

	@Schema(description = "是否为公司")
	private Boolean isComp;

	@Schema(description = "父级id")
	private Long parentId;

	public static DeptInfo create(Long id,
								  String code,
								  String name,
								  Long typeDictId,
								  String typeDictValue,
								  Long masterUserId,
								  Boolean isVirtual,
								  Boolean isComp,
								  Long parentId
								  ) {
		return DeptInfo.builder().id(id)
				.code(code)
				.name(name)
				.typeDictId(typeDictId)
				.typeDictValue(typeDictValue)
				.masterUserId(masterUserId)
				.isVirtual(isVirtual)
				.isComp(isComp)
				.parentId(parentId)
				.build();
	}
}
