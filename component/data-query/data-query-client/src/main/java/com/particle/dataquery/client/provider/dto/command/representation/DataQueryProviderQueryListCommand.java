package com.particle.dataquery.client.provider.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 数据查询供应商 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Data
@ApiModel
public class DataQueryProviderQueryListCommand extends AbstractBaseQueryCommand {



    @Like
        @ApiModelProperty(value = "供应商名称,左前缀匹配")
    private String name;


    @ApiModelProperty(value = "是否禁用")
    private Boolean isDisabled;


    @ApiModelProperty(value = "联系人姓名")
    private String contactUserName;


    @ApiModelProperty(value = "联系人邮箱")
    private String contactUserEmail;


    @ApiModelProperty(value = "联系人电话")
    private String contactUserPhone;


    @ApiModelProperty(value = "描述")
    private String remark;









}
