package com.particle.global.mybatis.plus.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.particle.global.dataaudit.DataAuditAuditAutoConfiguration;
import com.particle.global.dto.basic.DO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import lombok.Data;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.PropertyName;

import java.time.LocalDateTime;

/**
 * <p>
 * 数据持久对象基础类
 * </p>
 *
 * @author yangwei
 * @since 2022-06-28 11:42
 */
@Data
public class BaseDO extends DO {


	/**
	 * 表主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;


	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createAt;

	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long createBy;

	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateAt;

	/**
	 * 修改人
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Long updateBy;

	/**
	 * 乐观锁字段
	 */
	@Version
	@TableField(fill = FieldFill.INSERT)
	private Integer version;

	/**
	 * 租户id
	 */
	private Long tenantId;

	/**
	 * 在调用特定更新方法时使用，可以用来作为额外参数处理
	 * @see IBaseServiceImpl#update(com.particle.global.mybatis.plus.dto.BaseDO)
	 * @see IBaseService#annotationSupportUpdateWrapper(com.baomidou.mybatisplus.core.conditions.update.Update, java.lang.Object)
	 */
	@DiffIgnore
	@TableField(exist = false)
	private Object updateControl;
	/**
	 * 在调用特定添加方法时使用，可以用来作为额外参数处理
	 * @see 暂未用到
	 */
	@DiffIgnore
	@TableField(exist = false)
	private Object addControl;
	/**
	 * 在调用特写删除方法时使用，可以用来作为额外参数处理
	 * @see 暂未用到
	 */
	@DiffIgnore
	@TableField(exist = false)
	private Object deleteControl;
	/**
	 * 在调用特写查询方法时使用，可以用来作为额外参数处理
	 * @see IBaseService#getQueryWrapper(com.particle.global.dto.basic.QueryCommand)
	 * @see IBaseService#annotationSupportUpdateWrapper(com.baomidou.mybatisplus.core.conditions.update.Update, java.lang.Object)
	 */
	@DiffIgnore
	@TableField(exist = false)
	private Object queryControl;

	/**
	 * 启用数据审计数据收集设置开关，默认开启
	 * global-mybatis-plus-boot-starter 中的 IBaseServiceImpl 默认硬编码了添加、修改、删除时的数据审计收集逻辑
	 * 以方便应用程序自行逻辑处理，比如：登录日志、操作日志不需要开启
	 * 仅这里修改是没用的，前提是框架层面已开启，参见 {@link DataAuditAuditAutoConfiguration}
	 */
	@DiffIgnore
	@TableField(exist = false)
	private Boolean dataAuditEnabled = true;
}
