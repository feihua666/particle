package com.particle.usagecount.client.dto.command;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import com.particle.global.validation.form.Form;
import com.particle.global.validation.form.IFormValid;
import com.particle.global.validation.form.ValidContext;
import com.particle.global.validation.form.ValidResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 使用次数定义 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Form
@Data
@Schema
public class UsageCountDefineUpdateCommand extends AbstractBaseUpdateCommand implements IFormValid {



    @Schema(description = "编码")
    private String code;


    @NotEmpty(message = "名称 不能为空")
        @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "匹配的url地址")
    private String urlPattern;


    @NotNull(message = "是否为分组 不能为空")
        @Schema(description = "是否为分组",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isGroup;


    @Schema(description = "备注")
    private String remark;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;



    @Schema(description = "父级")
    private Long parentId;


    @Override
    public boolean valid(ValidResult result, ValidContext context) {
        if (BooleanUtil.isFalse(isGroup)) {
            if (StrUtil.isEmpty(code) && StrUtil.isEmpty(urlPattern)) {
                result.setErrorMsg("编码和url模式至少填写一个");
                result.setReportOn("code");
                return false;
            }
        }
        return true;
    }

}
