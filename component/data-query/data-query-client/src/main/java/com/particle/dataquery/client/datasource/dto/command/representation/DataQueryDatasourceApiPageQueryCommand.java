package com.particle.dataquery.client.datasource.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 数据查询数据源接口 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Data
@Schema
public class DataQueryDatasourceApiPageQueryCommand extends AbstractBasePageQueryCommand {

    @Like(left = true,right = true)
    @Schema(description = "编码,模糊匹配")
    private String code;

    @Like(left = true,right = true)
    @Schema(description = "名称,模糊匹配")
    private String name;

    @Schema(description = "分类")
    private Long categoryDictId;


    @Schema(description = "数据查询供应商id")
    private Long dataQueryProviderId;

    @Schema(description = "数据查询数据源id")
    private Long dataQueryDatasourceId;

    @Schema(description = "输出包装类型")
    private Long responseTypeDictId;


    @Schema(description = "入参类型，字典id")
    private Long inParamTypeDictId;

    @Schema(description = "出参类型，字典id")
    private Long outParamTypeDictId;

    @Like(left = true,right = true)
    @Schema(description = "基础配置json，模糊匹配")
    private String configJson;

    @Schema(description = "等同标签")
    private String sameTag;

	@Schema(description = "是否支持翻译数据")
	private Boolean isSupportTrans;

	@Schema(description = "是否已发布，已发布不能修改和删除")
	private Boolean isPublished;

	@Schema(description = "是否为主版本，非主版本视为开发版本")
	private Boolean isMaster;

	@Schema(description = "主版本id")
	private Long masterId;

	@Schema(description = "是否测试通过，测试通过才能发布")
	private Boolean isTestPassed;

    @Schema(description = "是否使用缓存")
    private Boolean isUseCache;

}
