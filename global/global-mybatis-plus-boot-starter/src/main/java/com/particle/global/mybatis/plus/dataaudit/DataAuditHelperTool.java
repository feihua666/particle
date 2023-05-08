package com.particle.global.mybatis.plus.dataaudit;

import cn.hutool.core.annotation.AnnotationUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.dataaudit.audit.DataAuditCollectTool;
import com.particle.global.dataaudit.audit.IDataAuditHandler;
import com.particle.global.mybatis.plus.GlobalMybatisPlusAutoConfiguration;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.tool.spring.SpringContextHolder;

/**
 * <p>
 * mybatis plus 层面，迂回实现数据审计发布，主要是不强制依赖 全局数据审计组件 global-data-audit-boot-starter，当没有引入时，不影响使用逻辑
 * 该类已条件初始化bean 参见 {@link GlobalMybatisPlusAutoConfiguration#dataAuditHelperTool()}
 * </p>
 *
 * @author yangwei
 * @since 2023-05-07 19:58
 */
public class DataAuditHelperTool {

	private static Boolean isInstanceNull = false;

	/**
	 * 获取DO的表名
	 * @param doClass
	 * @return
	 */
	public static String getDoTableName(Class doClass) {
		String tableName = null;
		TableName annotation = AnnotationUtil.getAnnotation(doClass, TableName.class);
		if (annotation != null) {
			tableName = annotation.value();
		}
		return tableName;
	}
	/**
	 * 获取数据实体表名
	 * @param oldVersion
	 * @param newVersion
	 * @param defaultDataTableName
	 * @return
	 */
	public static String getDoTableName(Object oldVersion, Object newVersion,String defaultDataTableName) {
		if (defaultDataTableName == null) {
			if (oldVersion instanceof BaseDO) {
				defaultDataTableName = getDoTableName(oldVersion.getClass());
			}
		}
		if (defaultDataTableName == null) {
			if (newVersion instanceof BaseDO) {
				defaultDataTableName = getDoTableName(newVersion.getClass());

			}
		}
		return defaultDataTableName;
	}
	/**
	 * 获取数据实体载体名
	 * @param oldVersion
	 * @param newVersion
	 * @param defaultDataEntity
	 * @return
	 */
	public static String getDoDataEntity(Object oldVersion, Object newVersion,String defaultDataEntity) {
		if (defaultDataEntity == null) {
			if (oldVersion != null) {
				defaultDataEntity = oldVersion.getClass().getName();
			}
		}
		if (defaultDataEntity == null) {
			if (newVersion != null) {
				defaultDataEntity = newVersion.getClass().getName();
			}
		}
		return defaultDataEntity;
	}
	/**
	 * 获取数据id
	 * @param oldVersion
	 * @param newVersion
	 * @param defaultDataId
	 * @return
	 */
	public static Long getDoDataId(Object oldVersion, Object newVersion,Long defaultDataId) {

		if (defaultDataId == null) {
			if (oldVersion instanceof BaseDO) {
				defaultDataId = ((BaseDO) oldVersion).getId();
			}
		}
		if (defaultDataId == null) {
			if (newVersion instanceof BaseDO) {
				defaultDataId = ((BaseDO) newVersion).getId();
			}
		}
		return defaultDataId;
	}
	/**
	 * 数据实体数据对比
	 * @param oldVersion
	 * @param newVersion
	 */
	public static void publish(BaseDO oldVersion, BaseDO newVersion,String type) {
		publish(oldVersion, newVersion, null, null,null, type);
	}

	/**
	 * 发布对象比对事件,建议使用 {@link DataAuditHelperTool#publish(com.particle.global.dataaudit.audit.IDataAuditHandler)} 异步处理，以降低性能消耗
	 * @param oldVersion
	 * @param newVersion
	 * @param dataId
	 * @param dataTable
	 */
	public static void publish(Object oldVersion,Object newVersion,Long dataId,String dataTable,String dataEntity,String type) {

		DataAuditHelperTool instance = getInstance();
		if (instance != null) {

			dataTable = getDoTableName(oldVersion, newVersion, dataTable);

			dataEntity = getDoDataEntity(oldVersion, newVersion, dataEntity);

			dataId = getDoDataId(oldVersion, newVersion, dataId);

			DataAuditCollectTool.publish(oldVersion, newVersion, dataId, dataTable,dataEntity,type);
		}
	}

	/**
	 * 发布自定义事件
	 * @param iDataAuditHandler
	 */
	public static void publish(IDataAuditHandler iDataAuditHandler) {
		DataAuditHelperTool instance = getInstance();
		if (instance != null) {
			DataAuditCollectTool.publish(iDataAuditHandler);
		}
	}

	/**
	 * 尝试获取实例，如果没有实例，代表没有引入 全局数据审计组件 global-data-audit-boot-starter
	 * @return
	 */
	private static DataAuditHelperTool getInstance(){
		if (isInstanceNull || !DataAuditCollectTool.getIsEnabled()) {
			return null;
		}
		try {
			DataAuditHelperTool bean = SpringContextHolder.getBean(DataAuditHelperTool.class);
			return bean;
		} catch (Exception e) {
			isInstanceNull = true;
			return null;
		}
	}

	/**
	 * 可以用来简单判断是否启动审计数据记录
	 * @return
	 */
	public static boolean isEnabled() {
		return getInstance() != null;
	}
}
