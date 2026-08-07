package com.particle.global.dto.login;


import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "部门信息")
public class DeptInfo extends DTO {

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

	@Schema(description = "层级")
	private Integer level;

	public static DeptInfo create(Long id,
								  String code,
								  String name,
								  Long typeDictId,
								  String typeDictValue,
								  Long masterUserId,
								  Boolean isVirtual,
								  Boolean isComp,
								  Long parentId,
								  Integer level
								  ) {
		DeptInfo deptInfo = new DeptInfo();
		deptInfo.setId(id);
		deptInfo.setCode(code);
		deptInfo.setName(name);
		deptInfo.setTypeDictId(typeDictId);
		deptInfo.setTypeDictValue(typeDictValue);
		deptInfo.setMasterUserId(masterUserId);
		deptInfo.setIsVirtual(isVirtual);
		deptInfo.setIsComp(isComp);
		deptInfo.setParentId(parentId);
		deptInfo.setLevel(level);
		return deptInfo;
	}
}
