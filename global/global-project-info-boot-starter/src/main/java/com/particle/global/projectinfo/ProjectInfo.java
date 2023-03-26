package com.particle.global.projectinfo;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
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

	private GitProperties gitProperties;
	private BuildProperties buildProperties;

	public ProjectInfo( List<ProjectInfoInitializeListener> projectInfoInitializeListeners){
		this.projectInfoInitializeListeners = projectInfoInitializeListeners;
	}

	// 项目名称
	public static String NAME;

	// 项目作者
	public static String AUTHOR;

	// 项目版本
	public static String VERSION;

	// 分支，如：git的分支
	public static String BRANCH;
	// 构建日志
	public static String BUILDDATE;
	// 最后提交信息
	public static String LASTCOMMINTMESSAGE;


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
		map.put("lastcommintmessage", LASTCOMMINTMESSAGE);

		return map;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		fill();
		log.debug("project info initialized");
		projectInfoInitializeListeners.forEach(projectInfoInitializeListener -> projectInfoInitializeListener.onInitialize(this));
	}

	private void fill() {
		if (StrUtil.isEmpty(NAME) && buildProperties != null) {
			NAME = buildProperties.getName();
		}

		if (StrUtil.isEmpty(AUTHOR) && gitProperties != null) {
			AUTHOR = gitProperties.get("commit.user.name");
		}

		if (StrUtil.isEmpty(VERSION) && buildProperties != null) {
			VERSION = buildProperties.getVersion();
		}
		if (StrUtil.isEmpty(BRANCH) && gitProperties != null) {
			BRANCH = gitProperties.getBranch();
		}
		if (StrUtil.isEmpty(BUILDDATE) && buildProperties != null) {
			BUILDDATE = buildProperties.getTime().toString();
		}
		if (StrUtil.isEmpty(BUILDDATE) && gitProperties != null) {
			BUILDDATE = gitProperties.getInstant("build.time").toString();
		}
		if (gitProperties != null) {
			LASTCOMMINTMESSAGE = gitProperties.get("commit.message.full");
		}
		// 填充默认值
		fillDefault();
	}

	public void fillDefault(){
		if (StrUtil.isEmpty(NAME)) {
			NAME = "Particle";
		}
		if (StrUtil.isEmpty(AUTHOR)) {
			AUTHOR = "飞华";
		}
	}

	public GitProperties getGitProperties() {
		return gitProperties;
	}

	@Autowired(required = false)
	public void setGitProperties(GitProperties gitProperties) {
		this.gitProperties = gitProperties;
	}

	public BuildProperties getBuildProperties() {
		return buildProperties;
	}
	@Autowired(required = false)
	public void setBuildProperties(BuildProperties buildProperties) {
		this.buildProperties = buildProperties;
	}
}
