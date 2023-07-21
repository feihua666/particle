package com.particle.tools.client.dto.command;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yangwei
 * Created at 2023-05-11 12:41:05
 */
@Setter
@Getter
@Schema(description = "添加字段表单对象")
public class AddFieldCommand extends AbstractBaseCommand {

    @Schema(description = "添加到该字段之后")
    private String afterFieldName;

    /**
     * 例如：/Users/yw/fh/git-source/particle/component/tenant
     * 注意后面带组件名称
     */
    @NotEmpty(message = "后端组件绝对路径 不能为空")
    @Schema(description = "前端组件绝对路径")
    private String componentBackendAbsolutePath;

    /**
     * 后端所有的需要添加字段的都是以领域模型为前缀的
     * 以该名称为前缀匹配
     * 注意首字母要大家，应该是类名称，不带后缀
     */
    @NotEmpty(message = "领域模型名称 不能为空")
    @Schema(description = "领域模型名称")
    private String domainName;

    @Valid
    @Schema(description = "要添加的字段")
    private List<AddFieldItemCommand> items;


    public void addFieldItem(String fieldName, String fieldComment, String fieldType) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(AddFieldItemCommand.create(fieldName,fieldComment,fieldType));
    }

    @Setter
    @Getter
    @Schema(description = "添加字段项表单对象")
    public static class AddFieldItemCommand {

        @NotEmpty(message = "字段名称 不能为空")
        @Schema(description = "字段名称")
        private String fieldName;

        @NotEmpty(message = "字段名称 不能为空")
        @Schema(description = "字段名称")
        private String fieldComment;

        @NotEmpty(message = "字段类型 不能为空")
        @Schema(description = "字段类型，java字段类型，如：String")
        private String fieldType;

        public static AddFieldItemCommand create(String fieldName, String fieldComment, String fieldType) {
            AddFieldItemCommand addFieldItemCommand = new AddFieldItemCommand();
            addFieldItemCommand.setFieldName(fieldName);
            addFieldItemCommand.setFieldComment(fieldComment);
            addFieldItemCommand.setFieldType(fieldType);
            return addFieldItemCommand;
        }
    }


    public List<String> properties(boolean swagger){
        return items.stream()
                .map(item -> {

                    String comment = StrUtil.format("\t@Schema(description = \"{}\")", item.getFieldComment());
                    if (!swagger) {
                        comment = StrUtil.format("\t/**\n" +
                                "\t * {}\n" +
                                "\t */", item.getFieldComment());
                    }
                    String field = StrUtil.format("\tprivate {} {};", item.getFieldType(), item.getFieldName());
                    return "\n" + comment + "\n" + field;
                }).collect(Collectors.toList());
    }
}
