package com.particle.global.neo4j.dto.basic;

import com.particle.global.dto.basic.DO;
import com.particle.global.dto.basic.DTO;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.Data;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 图数据库存储对象基类
 * 其中G首字母为graph的首字母
 * </p>
 *
 * @author yangwei
 * @since 2023-11-14 18:10:48
 */
@Data
public class GDO extends DTO {
	private static final long serialVersionUID = 1L;


	/**
	 * 鉴于以上id字段主依赖neo4j自动生成，这里自定义一个bid即businessId的缩写来定义业务主键，因为一般放入图库的数据都在数据库中已存在，这个字段就存放数据库中的主键
	 */
	private Long bid;

	/**
	 * 创建时间
	 * {@link CreatedDate} 注解只有在开启spirngjpa审计功能时才有效
	 */
	@CreatedDate
	private LocalDateTime createAt;

	/**
	 * 创建人
	 * {@link CreatedBy} 注解只有在开启spirngjpa审计功能时才有效
	 */
	@CreatedBy
	private Long createBy;

	/**
	 * 修改时间
	 * {@link LastModifiedDate} 注解只有在开启spirngjpa审计功能时才有效
	 */
	@LastModifiedDate
	private LocalDateTime updateAt;

	/**
	 * 修改人
	 * {@link LastModifiedBy} 注解只有在开启spirngjpa审计功能时才有效
	 */
	@LastModifiedBy
	private Long updateBy;

	/**
	 * 乐观锁字段
	 * 注意：类型必须为 Long，否则报错，插入不了数据
	 */
	@Version
	private Long version;

	/**
	 * 租户id
	 */
	private Long tenantId;


	/**
	 * 鉴于只有在开启spirngjpa审计功能时添加或更新实体才有效，这里提供对应的针对添加数据时的初始化方法来替代
	 * 在添加数据时可以调用该方法以初始化数据
	 */
	public void initAddData() {

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
		INeo4jCurrentUserResolver bean = null;
		try {
			bean = SpringContextHolder.getBean(INeo4jCurrentUserResolver.class);
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
