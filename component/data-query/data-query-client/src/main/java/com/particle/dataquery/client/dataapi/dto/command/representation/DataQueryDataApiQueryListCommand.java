package com.particle.dataquery.client.dataapi.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 数据查询数据接口 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Data
@ApiModel
public class DataQueryDataApiQueryListCommand extends AbstractBaseQueryCommand {

    @Like
    @ApiModelProperty(value = "接口地址,左前缀匹配")
    private String url;

    @Like
    @ApiModelProperty(value = "接口名称,左前缀匹配")
    private String name;

    @ApiModelProperty(value = "数据查询数据源接口id")
    private Long dataQueryDatasourceApiId;

    @ApiModelProperty(value = "适配类型")
    private Long adaptTypeDictId;

    @ApiModelProperty(value = "入参类型")
    private Long inParamTypeDictId;

    @ApiModelProperty(value = "出参类型")
    private Long outParamTypeDictId;

    @ApiModelProperty(value = "输出类型")
    private Long responseTypeDictId;

}
