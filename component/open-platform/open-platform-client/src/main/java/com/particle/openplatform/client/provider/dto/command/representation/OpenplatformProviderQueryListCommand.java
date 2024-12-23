package com.particle.openplatform.client.provider.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.In;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 开放平台开放接口供应商 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Data
@Schema
public class OpenplatformProviderQueryListCommand extends AbstractBaseQueryCommand {

    @Schema(description = "编码")
    private String code;

    @Like
    @Schema(description = "供应商名称,左前缀匹配")
    private String name;

    @Schema(description = "数据查询供应商id")
    private Long dataQueryProviderId;

    @Schema(description = "如果有值将根据开放接口id过滤")
    private Long openplatformOpenapiId;

    /**
     * 根据id查询，后端逻辑处理
     */
    @In("id")
    @Schema(description = "主键",hidden = true)
    private List<Long> ids;
}
