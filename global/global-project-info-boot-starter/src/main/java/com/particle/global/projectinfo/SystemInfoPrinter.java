package com.particle.global.projectinfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统信息打印
 * </p>
 *
 * @author yangwei
 * @since 2022-05-31 20:52
 */
@Slf4j
@Component
public class SystemInfoPrinter {


	private ProjectInfo projectInfo;
	private RunningInfo runningInfo;

	/**
	 * 打印 banner、访问地址、项目信息
	 */
	public void print(){
		// banner
		OnRunningBanner.banner_success(ProjectInfo.NAME,ProjectInfo.VERSION);

		// 项目信息，包括构建信息，分支等
		Map<String, String> map = ProjectInfo.toMap();
		logMapInfo(map,"Application  is running! Project Info:",false);
		// 运行信息 访问地址
		logMapInfo(runningInfo.toMap(),"Application  is running! Access URLs:",false);
	}

	private void logMapInfo(Map<String, String> map,String title,boolean logSingleLine) {

		Integer integer = map.keySet().stream().map(String::length).max(Comparator.comparingInt(Integer::intValue)).get();
		List<String> lines = new ArrayList<>();
		lines.add("=============================================================");
		lines.add(title);
		for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
			lines.add(alignLeft(stringStringEntry.getKey(),integer) + (":\t") + stringStringEntry.getValue());

		}
		lines.add("=============================================================");

		if (logSingleLine) {
			log.info("\n" + lines.stream().collect(Collectors.joining("\n\t")));
		}else {
			for (int i = 0; i < lines.size(); i++) {
				if(i == 0){
					log.info(lines.get(i));
				}else {
					log.info("\t" + lines.get(i));
				}

			}
		}
	}


	public static String alignLeft(String str, Integer maxLength) {
		String column1Format = "%-"+ maxLength +"s";
		return String.format(column1Format, str);
	}


	@Autowired
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Autowired
	public void setRunningInfo(RunningInfo runningInfo) {
		this.runningInfo = runningInfo;
	}
}
