package com.particle.area.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 区域 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Data
@ApiModel
public class AreaPageQueryCommand extends AbstractBaseTreePageQueryCommand {


    @Like
    @ApiModelProperty("编码，左前缀匹配")
    private String code;

    @Like
    @ApiModelProperty("区域名称,左前缀匹配")
    private String name;

    @Like
    @ApiModelProperty("区域名称,左前缀匹配")
    private String nameSimple;

    @ApiModelProperty("第一个字的首字母")
    private String spellFirst;

    @Like
    @ApiModelProperty("每个字的首字母,左前缀匹配")
    private String spellSimple;

    @Like
    @ApiModelProperty("全拼,左前缀匹配")
    private String spell;

    @ApiModelProperty("类型，字典id")
    private Long typeDictId;

}
