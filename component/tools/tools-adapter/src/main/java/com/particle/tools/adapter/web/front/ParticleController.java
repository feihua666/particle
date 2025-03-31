package com.particle.tools.adapter.web.front;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.tools.client.dto.command.AddFieldCommand;
import com.particle.tools.client.dto.command.BatchGenIdsCommand;
import com.particle.tools.client.dto.command.DeleteModelServiceCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by yangwei
 * Created at 2023-05-11 12:38:09
 */
@Tag(name = "Particle项目相关接口")
@RestController
@RequestMapping("front/web/particle")
public class ParticleController extends AbstractBaseWebAdapter {

    /**
     * 主要用于对已生成的代码，添加字段，包括常用的po、command、vo等，注意不包括sql
     * @param addFieldCommand
     * @return
     */
    @Operation(summary = "添加字段")
    @PostMapping("/addField")
    @ResponseStatus(HttpStatus.OK)
    public Response addField(@RequestBody @Validated AddFieldCommand addFieldCommand) {

        // 后端处理
        handleBackendForAddField(addFieldCommand);
        // 前端处理
        handleFrontendForAddField(addFieldCommand);


        return Response.buildSuccess();
    }
    /**
     * 主要用于对已生成的代码，删除模型服务，主要用于生成错误，或者删除多余的服务
     * @param deleteModelServiceCommand
     * @return
     */
    @Operation(summary = "删除模型服务")
    @PostMapping("/deleteModelService")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteModelService(@RequestBody @Validated DeleteModelServiceCommand deleteModelServiceCommand) {

        // 后端处理
        handleBackendForDeleteModelService(deleteModelServiceCommand);
        // 前端处理
        handleFrontendForDeleteModelService(deleteModelServiceCommand);


        return Response.buildSuccess();
    }
    @Operation(summary = "批量生成id")
    @PostMapping("/batchGenIds")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse batchGenIds(@RequestBody @Validated BatchGenIdsCommand batchGenIdsCommand) {
        List<Long> list = new ArrayList<>(batchGenIdsCommand.getNum());
        for (Integer i = 0; i < batchGenIdsCommand.getNum(); i++) {
            list.add(SnowflakeIdTool.nextId());
        }
        return MultiResponse.of(list);
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
    private List<File> getFile( Predicate<File> predicate,String parentPath) {
        List<File> result = new ArrayList<>();
        File[] ls = FileUtil.ls(parentPath);
        for (File file : ls) {
            if (file.isDirectory()) {
                result.addAll(getFile(predicate, file.getAbsolutePath()));
            }else {
                if (predicate.test(file)) {
                    result.add(file);
                }
            }
        }
        return result;
    }

}
