package com.particle.tools.adapter.web.front;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.Response;
import com.particle.tools.client.dto.command.AddFieldCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by yangwei
 * Created at 2023-05-11 12:38:09
 */
@Api(tags = "Cron相关接口")
@RestController
@RequestMapping("front/web/particle")
public class ParticleController extends AbstractBaseWebAdapter {

    @ApiOperation("添加字段")
    @PostMapping("/addField")
    @ResponseStatus(HttpStatus.OK)
    public Response addField(@Validated AddFieldCommand addFieldCommand) {

        // 后端处理
        handleBackend(addFieldCommand);
        // 前端处理
        handleFrontEnd(addFieldCommand);


        return Response.buildSuccess();
    }

    /**
     * 过滤后端文件
     * @param addFieldCommand
     */
    private void handleBackend(AddFieldCommand addFieldCommand) {
        // 添加字段主要是 DO、Domain、Command、VO等
        if (StrUtil.isEmpty(addFieldCommand.getComponentBackendAbsolutePath())) {
            return;
        }
        List<File> fileList = getFile(file -> {
            String name = FileUtil.getName(file);
            name = name.substring(0,name.lastIndexOf("."));
            boolean equals = StrUtil.equals(name, addFieldCommand.getDomainName());
            boolean startWidth = StrUtil.startWith(name, addFieldCommand.getDomainName());
            boolean endWithAny = StrUtil.equalsAny(name,
                    addFieldCommand.getDomainName() + "CreateCommand",
                   addFieldCommand.getDomainName() + "UpdateCommand",
                   addFieldCommand.getDomainName() + "PageQueryCommand",
                   addFieldCommand.getDomainName() + "QueryListCommand",
                   addFieldCommand.getDomainName() + "DO",
                   addFieldCommand.getDomainName() + "VO"
            );

            return equals || (startWidth && endWithAny);
        }, addFieldCommand.getComponentBackendAbsolutePath());

        for (File file : fileList) {
            handleBackendFile(file, addFieldCommand);
        }
    }

    /**
     * 处理后端文件
     * @param file
     * @param addFieldCommand
     */
    private void handleBackendFile(File file,AddFieldCommand addFieldCommand) {
        List<String> list = FileUtil.readUtf8Lines(file);
        boolean hasApiModelProperty = list.stream().anyMatch(line -> StrUtil.contains(line, "ApiModelProperty"));
        boolean hasAfterFieldName = list.stream().anyMatch(line -> StrUtil.startWith(line.trim(),"private") && StrUtil.contains(line,addFieldCommand.getAfterFieldName()));
        List<String> listResult = new ArrayList<>(list.size());
        List<String> properties = addFieldCommand.properties(hasApiModelProperty);
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
    private void handleFrontEnd(AddFieldCommand addFieldCommand) {

    }

    /**
     * 获取需要的文件
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