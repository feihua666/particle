package com.particle.generator.domain;

/**
 * <p>
 * 四层架构适配层类型的子分类
 * 一般四层架构适配层分移动端、wap端、web端等，但有可能每一个设备终端都会做不同的业务
 * 比如后台管理不只在pc上有对应的功能，而在移动设备上也会有移动办公等也属于后台管理范畴
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 15:17
 */
public enum AdapterLogicType {
	/**
	 * 后台管理适配
	 * 一般体现在适配器命名上，加该分类的后缀，如：web.AreaAdminController 这表示是pc或平板下的后台管理控制器
	 */
	ADMIN,
	/**
	 * 前端用户适配
	 * 一般体现在适配器命名上，加该分类的后缀，如：web.AreaFrontController 这表示是pc或平板下的非后台管理控制器，用户层面使用
	 */
	FRONT
}
