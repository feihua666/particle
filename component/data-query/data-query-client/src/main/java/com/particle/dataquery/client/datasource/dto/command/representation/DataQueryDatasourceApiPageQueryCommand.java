package com.particle.dataquery.client.datasource.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class DataQueryDatasourceApiPageQueryCommand extends AbstractBasePageQueryCommand {

    @Like
    @ApiModelProperty(value = "编码,左前缀匹配")
    private String code;

    @Like
    @ApiModelProperty(value = "名称,左前缀匹配")
    private String name;

    @ApiModelProperty(value = "分类")
    private Long categoryDictId;


    @ApiModelProperty(value = "数据查询供应商id")
    private Long dataQueryProviderId;

    @ApiModelProperty(value = "数据查询数据源id")
    private Long dataQueryDatasourceId;

    @ApiModelProperty(value = "类型")
    private Long responseTypeDictId;

    @ApiModelProperty(value = "等同标签")
    private String sameTag;

}
