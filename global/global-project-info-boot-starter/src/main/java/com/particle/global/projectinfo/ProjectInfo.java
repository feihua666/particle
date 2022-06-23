package com.particle.global.projectinfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目信息
 * </p>
 *
 * @author yangwei
 * @since 2022-05-19 19:22
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "particle.project-info")
public class ProjectInfo implements InitializingBean {

	private List<ProjectInfoInitializeListener> projectInfoInitializeListeners;

	public ProjectInfo( List<ProjectInfoInitializeListener> projectInfoInitializeListeners){
		this.projectInfoInitializeListeners = projectInfoInitializeListeners;
	}

	// 项目名称
	public static String NAME = "Particle";

	// 项目作者
	public static String AUTHOR = "飞华";

	// 项目版本
	public static String VERSION = "1.0.0";

	// 分支，如：git的分支
	public static String BRANCH;
	// 构建日志
	public static String BUILDDATE;


	public void setName(String name) {
		ProjectInfo.NAME = name;
	}

	public void setAuthor(String author) {
		ProjectInfo.AUTHOR = author;
	}

	public void setVersion(String version) {
		ProjectInfo.VERSION = version;
	}

	public void setBranch(String branch) {
		ProjectInfo.BRANCH = branch;
	}

	public void setBuilddate(String builddate) {
		ProjectInfo.BUILDDATE = builddate;
	}

	/**
	 * 将项目信息转为一个map
	 * @return
	 */
	public static Map<String,String> toMap(){

		Map<String,String> map = new HashMap<>();
		map.put("name", NAME);
		map.put("author", AUTHOR);
		map.put("version", VERSION);
		map.put("branch", BRANCH);
		map.put("builddate", BUILDDATE);

		return map;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("project info initialized");
		projectInfoInitializeListeners.forEach(projectInfoInitializeListener -> projectInfoInitializeListener.onInitialize(this));
	}
}
