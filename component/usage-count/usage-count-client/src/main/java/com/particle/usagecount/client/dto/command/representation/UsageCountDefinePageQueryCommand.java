package com.particle.usagecount.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 使用次数定义 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@OrderBy("seq")
@Data
@Schema
public class UsageCountDefinePageQueryCommand extends AbstractBaseTreePageQueryCommand {



    @Schema(description = "编码")
    private String code;


    @Like
    @Schema(description = "名称,左前缀匹配")
    private String name;

    @Like
    @Schema(description = "匹配的url地址")
    private String urlPattern;


    @Schema(description = "是否为分组")
    private Boolean isGroup;


}
