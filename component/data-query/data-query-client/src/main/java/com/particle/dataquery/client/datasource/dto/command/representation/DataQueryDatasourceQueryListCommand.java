package com.particle.dataquery.client.datasource.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 数据查询数据源 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Data
@ApiModel
public class DataQueryDatasourceQueryListCommand extends AbstractBaseQueryCommand {

    @ApiModelProperty(value = "编码")
    private String code;

    @Like
    @ApiModelProperty(value = "名称,左前缀匹配")
    private String name;

    @ApiModelProperty(value = "类型")
    private Long typeDictId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "数据查询供应商")
    private Long dataQueryProviderId;

}
