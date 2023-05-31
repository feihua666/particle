package com.particle.global.security.security.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("部门信息")
public class DeptInfo implements Serializable {

	@ApiModelProperty("dept id")
	private Long id;
	@ApiModelProperty("dept code")
	private String code;
	@ApiModelProperty("dept name")
	private String name;

	@ApiModelProperty("dept type")
	private Long typeDictId;

	@ApiModelProperty("dept type value")
	private String typeDictValue;


	@ApiModelProperty("负责人用户id")
	private Long masterUserId;

	@ApiModelProperty("是否虚拟部门")
	private Boolean isVirtual;

	@ApiModelProperty("是否为公司")
	private Boolean isComp;

	@ApiModelProperty("父级id")
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
