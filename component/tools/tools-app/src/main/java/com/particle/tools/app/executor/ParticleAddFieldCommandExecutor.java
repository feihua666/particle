package com.particle.tools.app.executor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.file.FileTool;
import com.particle.tools.client.dto.command.AddFieldCommand;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>
 * 添加字段指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-08 10:02:14
 */
@Component
@Validated
public class ParticleAddFieldCommandExecutor extends AbstractBaseExecutor {

	/**
	 * 主要用于对已生成的代码，添加字段，包括常用的po、command、vo等，注意不包括sql
	 * @param addFieldCommand
	 * @return
	 */
	public Response addField(@Valid AddFieldCommand addFieldCommand) {
		// 后端处理
		handleBackendForAddField(addFieldCommand);
		// 前端处理
		handleFrontendForAddField(addFieldCommand);

		return Response.buildSuccess();
	}
	/**
	 * 后端，添加字段，过滤后端文件
	 * @param addFieldCommand
	 */
	private void handleBackendForAddField(AddFieldCommand addFieldCommand) {
		// 添加字段主要是 DO、Domain、Command、VO等
		if (StrUtil.isEmpty(addFieldCommand.getComponentBackendAbsolutePath())) {
			return;
		}
		List<File> fileList = getFile(file -> {
			// XxxxxCreateCommand.java
			String name = FileUtil.getName(file);
			// XxxxxCreateCommand
			name = name.substring(0,name.lastIndexOf("."));
			boolean equalsAny = StrUtil.equalsAny(name,
					addFieldCommand.getDomainName(),
					addFieldCommand.getDomainName() + "CreateCommand",
					addFieldCommand.getDomainName() + "UpdateCommand",
					addFieldCommand.getDomainName() + "PageQueryCommand",
					addFieldCommand.getDomainName() + "QueryListCommand",
					addFieldCommand.getDomainName() + "DO",
					addFieldCommand.getDomainName() + "VO"
			);

			return equalsAny;
		}, addFieldCommand.getComponentBackendAbsolutePath());

		for (File file : fileList) {
			handleBackendFileForAddField(file, addFieldCommand);
		}
	}


	/**
	 * 后端，添加字段，处理后端文件
	 * @param file
	 * @param addFieldCommand
	 */
	private void handleBackendFileForAddField(File file, AddFieldCommand addFieldCommand) {
		List<String> list = FileUtil.readUtf8Lines(file);
		boolean hasSchema = list.stream().anyMatch(line -> StrUtil.contains(line, "Schema"));
		boolean hasAfterFieldName = list.stream().anyMatch(line -> StrUtil.startWith(line.trim(),"private") && StrUtil.contains(line,addFieldCommand.getAfterFieldName()));
		List<String> listResult = new ArrayList<>(list.size());
		List<String> properties = addFieldCommand.properties(hasSchema);
		boolean hasAdd = false;
		for (String line : list) {
			listResult.add(line);
			if (hasAdd) {
				continue;
			}
			if (hasAfterFieldName) {
				if (StrUtil.startWith(line.trim(),"private") && StrUtil.contains(line,addFieldCommand.getAfterFieldName())) {

					listResult.addAll(properties);
					hasAdd = true;
				}
			}else {
				if (StrUtil.startWith(line.trim(),"public class")) {
					listResult.addAll(properties);
					hasAdd = true;
				}
			}

		}
		FileUtil.writeUtf8Lines(listResult, file);
	}

	/**
	 * 前端暂不支持
	 * @param addFieldCommand
	 */
	private void handleFrontendForAddField(AddFieldCommand addFieldCommand) {

	}

	/**
	 * 在父目录下，递归获取所有需要的文件
	 * @param predicate
	 * @param parentPath
	 * @return
	 */
	private List<File> getFile(Predicate<File> predicate, String parentPath) {
		return FileTool.getFiles(predicate, parentPath, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
}
