package com.particle.tools.adapter.web.front;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.tools.client.dto.command.AddFieldCommand;
import com.particle.tools.client.dto.command.BatchGenIdsCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
        handleBackend(addFieldCommand);
        // 前端处理
        handleFrontEnd(addFieldCommand);


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
