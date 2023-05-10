package com.particle.tracking.client.dto.command.representation;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 埋点页面 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@OrderBy("seq")
@Data
@ApiModel
public class TrackingPagePageQueryCommand extends AbstractBaseTreePageQueryCommand {

    @Like
    @ApiModelProperty(value = "页面编码,左前缀匹配")
    private String code;

    @Like
    @ApiModelProperty(value = "页面名称,左前缀匹配")
    private String name;

    @ApiModelProperty(value = "页面版本")
    private String pageVersion;

    @Like
    @ApiModelProperty(value = "分组标识,左前缀匹配")
    private String groupFlag;

}
