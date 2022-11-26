package com.particle.generator.domain;

/**
 * <p>
 * 子模块
 * 生成组件遵循，组件根（如：user、area等）和子模块（如：user-adapter、area-app等）
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 21:22
 */
public enum SubModule {
	/**
	 * 适配层
	 */
	ADAPTER,
	/**
	 * 适配层远程调用
	 */
	ADAPTER_FEIGN_CLIENT,
	/**
	 * 应用层
	 */
	APP,
	/**
	 * 应用层门面接口
	 */
	CLIENT,
	/**
	 * 领域层
	 */
	DOMAIN,
	/**
	 * 基础设施层
	 */
	INFRASTRUCTURE,
	/**
	 * 启动模块
	 */
	START;

	/**
	 * 根据 subModule 枚举变量，转换为真实的 maven module 名称后缀
	 * @return
	 */
	public String realSubModuleName(){
		return this.name().toLowerCase().replace("_", "-");
	}

	/**
	 * 将子模块名称转为 java 包路径片段
	 * @return
	 */
	public String subModuleNameToPkg(){
		return realSubModuleName().toLowerCase().replace("-", ".");

	}
}
