package com.particle.gobal.elasticsearch.dto.basic;

import cn.hutool.core.date.DatePattern;
import com.particle.global.dto.basic.DO;
import com.particle.global.dto.basic.DTO;
import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * <p>
 * es存储对象基类
 * 其中Es首字母为elasticsearch的首字母
 * </p>
 *
 * @author yangwei
 * @since 2023-12-07 12:52:49
 */
@Data
public class EsDO extends DTO {
	private static final long serialVersionUID = 1L;

	@Id
	@Field(store = false)
	private Long id;

	/**
	 * 创建时间
	 * {@link CreatedDate} 注解只有在开启spirngjpa审计功能时才有效
	 */
	@CreatedDate
	@Field(store = true,type = FieldType.Date,format = {},pattern = DatePattern.NORM_DATETIME_PATTERN)
	private LocalDateTime createAt;

	/**
	 * 创建人
	 * {@link CreatedBy} 注解只有在开启spirngjpa审计功能时才有效
	 */
	@CreatedBy
	@Field(store = true)
	private Long createBy;

	/**
	 * 修改时间
	 * {@link LastModifiedDate} 注解只有在开启spirngjpa审计功能时才有效
	 */
	@LastModifiedDate
	@Field(store = true,type = FieldType.Date,format = {},pattern = DatePattern.NORM_DATETIME_PATTERN)
	private LocalDateTime updateAt;

	/**
	 * 修改人
	 * {@link LastModifiedBy} 注解只有在开启spirngjpa审计功能时才有效
	 */
	@LastModifiedBy
	@Field(store = true)
	private Long updateBy;

	/**
	 * 乐观锁字段
	 * 注意：类型必须为 Long，否则报错，插入不了数据
	 */
	@Version
	@Field(store = true)
	private Long version;

	/**
	 * 租户id
	 */
	@Field(store = true)
	private Long tenantId;



	/**
	 * 鉴于只有在开启spirngjpa审计功能时添加或更新实体才有效，这里提供对应的针对添加数据时的初始化方法来替代
	 * 在添加数据时可以调用该方法以初始化数据
	 */
	public void initAddData() {

		this.id = SnowflakeIdTool.nextId();
		this.createAt = LocalDateTime.now();
		this.createBy = currentUser().getCurrentUserId();
		this.tenantId = currentUser().getCurrentTenantId();
		this.version = (long)DO.INIT_VERSION;

	}
	/**
	 * 鉴于只有在开启spirngjpa审计功能时添加或更新实体才有效，这里提供对应的针对更新加数据时的初始化方法来替代
	 * 在添加数据时可以调用该方法以初始化数据
	 */
	public void initUpdateData() {

		this.updateAt = LocalDateTime.now();
		this.updateBy = currentUser().getCurrentUserId();
	}
	/**
	 * 当前登录用户id
	 * @return
	 */
	private CurrentDto currentUser() {
		IElasticsearchCurrentUserResolver bean = null;
		try {
			bean = SpringContextHolder.getBean(IElasticsearchCurrentUserResolver.class);
		} catch (Exception e) {
			// 不需要异常，可能没有定义
		}
		if (bean != null) {
			return CurrentDto.create(bean.currentUserId(), bean.currentTenantId());
		}
		return CurrentDto.empty;
	}

	@Data
	private static class CurrentDto{

		static CurrentDto empty = CurrentDto.create(null, null);


		private Long currentUserId;
		private Long currentTenantId;

		public static CurrentDto create(Long currentUserId,Long currentTenantId) {
			CurrentDto currentDto = new CurrentDto();
			currentDto.currentUserId = currentUserId;
			currentDto.currentTenantId = currentTenantId;

			return currentDto;
		}
	}
}
