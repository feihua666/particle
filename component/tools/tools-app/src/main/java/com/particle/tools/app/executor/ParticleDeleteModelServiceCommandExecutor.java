package com.particle.tools.app.executor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.file.FileTool;
import com.particle.tools.client.dto.command.DeleteModelServiceCommand;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>
 * 删除模型服务指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-08 10:09:04
 */
@Component
@Validated
public class ParticleDeleteModelServiceCommandExecutor extends AbstractBaseExecutor {


	/**
	 * 主要用于对已生成的代码，删除模型服务，主要用于生成错误，或者删除多余的服务
	 * @param deleteModelServiceCommand
	 * @return
	 */
	public Response deleteModelService(@Valid DeleteModelServiceCommand deleteModelServiceCommand) {
		// 后端处理
		handleBackendForDeleteModelService(deleteModelServiceCommand);
		// 前端处理
		handleFrontendForDeleteModelService(deleteModelServiceCommand);

		return Response.buildSuccess();
	}

	/**
	 * 后端，删除模型服务，过滤后端文件
	 * 删除后，可能有一些未删除干净，如：数据库表创建语句，一般在 classpath:db/ 下
	 * @param deleteModelServiceCommand
	 */
	private void handleBackendForDeleteModelService(DeleteModelServiceCommand deleteModelServiceCommand) {
		if (StrUtil.isEmpty(deleteModelServiceCommand.getComponentBackendAbsolutePath())) {
			return;
		}
		List<File> fileList = getFile(file -> {
			// XxxxxCreateCommand.java
			String name = FileUtil.getName(file);
			// XxxxxCreateCommand
			name = name.substring(0,name.lastIndexOf("."));
			boolean equalsAny = StrUtil.equalsAny(name,
					deleteModelServiceCommand.getDomainName(),
					deleteModelServiceCommand.getDomainName() + "Id",

					deleteModelServiceCommand.getDomainName() + "AdminWebController",
					deleteModelServiceCommand.getDomainName() + "FrontWebController",
					deleteModelServiceCommand.getDomainName() + "AdminMobileController",
					deleteModelServiceCommand.getDomainName() + "FrontMobileController",
					deleteModelServiceCommand.getDomainName() + "AdminWapController",
					deleteModelServiceCommand.getDomainName() + "FrontWapController",
					deleteModelServiceCommand.getDomainName() + "RpcController",

					deleteModelServiceCommand.getDomainName() + "CreateCommand",
					deleteModelServiceCommand.getDomainName() + "UpdateCommand",
					deleteModelServiceCommand.getDomainName() + "PageQueryCommand",
					deleteModelServiceCommand.getDomainName() + "QueryListCommand",
					deleteModelServiceCommand.getDomainName() + "VO",

					"I" + deleteModelServiceCommand.getDomainName() + "RepresentationApplicationService",
					"I" + deleteModelServiceCommand.getDomainName() + "ApplicationService",
					deleteModelServiceCommand.getDomainName() + "RepresentationApplicationServiceImpl",
					deleteModelServiceCommand.getDomainName() + "ApplicationServiceImpl",

					deleteModelServiceCommand.getDomainName() + "CreateCommandExecutor",
					deleteModelServiceCommand.getDomainName() + "DeleteCommandExecutor",
					deleteModelServiceCommand.getDomainName() + "UpdateCommandExecutor",
					deleteModelServiceCommand.getDomainName() + "CommandExecutor",
					deleteModelServiceCommand.getDomainName() + "QueryCommandExecutor",

					deleteModelServiceCommand.getDomainName() + "Gateway",
					deleteModelServiceCommand.getDomainName() + "GatewayImpl",
					deleteModelServiceCommand.getDomainName() + "AppStructMapping",

					"I" + deleteModelServiceCommand.getDomainName() + "Service",
					deleteModelServiceCommand.getDomainName() + "ServiceImpl",
					deleteModelServiceCommand.getDomainName() + "Mapper",
					deleteModelServiceCommand.getDomainName() + "InfrastructureStructMapping",
					deleteModelServiceCommand.getDomainName() + "DO",

					deleteModelServiceCommand.getDomainName() + "RpcFeignClient"
			);

			return equalsAny;
		}, deleteModelServiceCommand.getComponentBackendAbsolutePath());

		for (File file : fileList) {
			FileUtil.del( file);
		}
	}

	/**
	 * 前端暂不支持
	 * @param deleteModelServiceCommand
	 */
	private void handleFrontendForDeleteModelService(DeleteModelServiceCommand deleteModelServiceCommand) {
		if (StrUtil.isEmpty(deleteModelServiceCommand.getComponentFrontendAbsolutePath())) {
			return;
		}
		List<File> fileList = getFile(file -> {
			// XxxxxCreateCommand.java
			String name = FileUtil.getName(file);
			// XxxxxCreateCommand
			name = name.substring(0,name.lastIndexOf("."));
			boolean equalsAny = StrUtil.equalsAny(name,
					StrUtil.lowerFirst(deleteModelServiceCommand.getDomainName()) + "AdminApi",
					StrUtil.lowerFirst(deleteModelServiceCommand.getDomainName()) + "Manage",
					StrUtil.lowerFirst(deleteModelServiceCommand.getDomainName()) + "AdminRoutes",
					deleteModelServiceCommand.getDomainName() + "ManageAddPage",
					deleteModelServiceCommand.getDomainName() + "ManagePage",
					deleteModelServiceCommand.getDomainName() + "ManageUpdatePage"
			);

			return equalsAny;
		}, deleteModelServiceCommand.getComponentFrontendAbsolutePath());

		for (File file : fileList) {
			FileUtil.del(file);
		}
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
