package com.particle.dataquery.client.dataapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 数据查询数据接口 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Data
@Schema
public class DataQueryDataApiPageQueryCommand extends AbstractBasePageQueryCommand {

    @Like(left = true,right = true)
    @Schema(description = "接口地址,模糊匹配")
    private String url;

    @Like(left = true,right = true)
    @Schema(description = "接口名称,模糊匹配")
    private String name;

    @Schema(description = "数据查询数据源接口id，仅一对一直连关联的接口")
    private Long dataQueryDatasourceApiId;

    @Schema(description = "适配类型")
    private Long adaptTypeDictId;

    @Like(left = true,right = true)
    @Schema(description = "接口复杂适配逻辑配置json，模糊匹配")
    private String adaptConfigJson;

    @Schema(description = "入参类型")
    private Long inParamTypeDictId;

    @Schema(description = "出参类型")
    private Long outParamTypeDictId;

    @Schema(description = "输出包装类型")
    private Long responseTypeDictId;

    @Schema(description = "出参翻译配置json")
    private String outParamTransConfigJson;

    @Schema(description = "出参扩展配置json")
    private String outParamExtConfigJson;

    @Schema(description = "入参扩展配置json")
    private String inParamExtConfigJson;

    @Schema(description = "是否使用远程服务")
    private Boolean isUseRemote;

    @Schema(description = "是否已发布，已发布不能修改和删除")
    private Boolean isPublished;

    @Schema(description = "是否为主版本，非主版本视为开发版本")
    private Boolean isMaster;

    @Schema(description = "主版本id")
    private Long masterId;

    @Schema(description = "是否测试通过，测试通过才能发布")
    private Boolean isTestPassed;


}
