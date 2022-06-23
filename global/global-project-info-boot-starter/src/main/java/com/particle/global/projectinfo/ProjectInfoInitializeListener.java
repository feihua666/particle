package com.particle.global.projectinfo;

/**
 * <p>
 * 项目信息初始化完成监听
 * </p>
 * 主要用于使用项目信息的方式通知
 * @author yangwei
 * @since 2022-05-31 20:02
 */
public interface ProjectInfoInitializeListener {
	/**
	 * 初始化成功调用
	 * @param projectInfo
	 */
	void onInitialize(ProjectInfo projectInfo);
}
