package com.particle.area.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 区域 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Data
@ApiModel
public class AreaUpdateCommand extends AbstractBaseUpdateCommand {


    @ApiModelProperty("编码，唯一,模糊查询")
    private String code;

    @ApiModelProperty("区域名称,模糊查询")
    private String name;

    @ApiModelProperty("区域名称,模糊查询")
    private String nameSimple;

    @ApiModelProperty("第一个字的首字母")
    private String spellFirst;

    @ApiModelProperty("每个字的首字母")
    private String spellSimple;

    @ApiModelProperty("全拼")
    private String spell;

    @ApiModelProperty("类型，字典id")
    private Long typeDictId;

    @ApiModelProperty("排序,默认按该字段升序排序")
    private Integer seq;


}
