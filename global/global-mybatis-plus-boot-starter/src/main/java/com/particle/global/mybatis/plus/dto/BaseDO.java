package com.particle.global.mybatis.plus.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.particle.global.dto.basic.DO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import lombok.Data;

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
	@TableField(fill = FieldFill.UPDATE)
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
	@TableField(exist = false)
	private Object updateControl;
	/**
	 * 在调用特定添加方法时使用，可以用来作为额外参数处理
	 * @see 暂未用到
	 */
	@TableField(exist = false)
	private Object addControl;
	/**
	 * 在调用特写删除方法时使用，可以用来作为额外参数处理
	 * @see 暂未用到
	 */
	@TableField(exist = false)
	private Object deleteControl;
	/**
	 * 在调用特写查询方法时使用，可以用来作为额外参数处理
	 * @see IBaseService#getQueryWrapper(com.particle.global.dto.basic.QueryCommand)
	 * @see IBaseService#annotationSupportUpdateWrapper(com.baomidou.mybatisplus.core.conditions.update.Update, java.lang.Object)
	 */
	@TableField(exist = false)
	private Object queryControl;


}
