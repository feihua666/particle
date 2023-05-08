package com.particle.global.dataaudit.op.dto;

import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 当前操作日志数据传输对象
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 20:51
 */
@Data
public class OpLogDTO extends DTO {

	/**
	 * 预先生成的持久化id，持久化时请使用该id，因为这个id可能存在子级关联
	 */
	private Long id;

	/**
	 * 操作名称 取自 {@link OpLog#name()}
	 */
	private String name;

	/**
	 * 模块 取自 {@link OpLog#module()}
	 */
	private String module;

	/**
	 * 操作类型 取自 {@link OpLog#type()}
	 */
	private String type;

	/**
	 * 预先生成的持久化父id，持久化时请使用该id，因为这个id可能存在父级关联
	 */
	private Long parentId;


	/**
	 * 数据id，一般为数据主键
	 * 针对数据审计而言，一般数据审计修改一个数据主体或能连带多个数据，这里只保留数据主体
	 */
	private Long mainDataId;
	/**
	 * 数据对应的表名
	 * 针对数据审计而言，一般数据审计修改一个数据主体或能连带多个数据，这里只保留数据主体
	 */
	private String mainDataTable;

	/**
	 * 数据主体，
	 * 对数据库的：如果是数据实体，可以是数据的实现类名
	 * 对其它 比如 redis 这里可以是数据key等
	 * 针对数据审计而言，一般数据审计修改一个数据主体或能连带多个数据，这里只保留数据主体
	 */
	private String mainDataEntity;

	public static OpLogDTO create(Long id,String name, String module, String type,Long parentId,Long mainDataId,String mainDataTable,String mainDataEntity) {
		OpLogDTO opLogDTO = new OpLogDTO();

		opLogDTO.setId(id);
		opLogDTO.setName(name);
		opLogDTO.setModule(module);
		opLogDTO.setType(type);
		opLogDTO.setParentId(parentId);
		opLogDTO.setMainDataId(mainDataId);
		return opLogDTO;
	}
}
