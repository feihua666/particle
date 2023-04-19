package com.particle.tenant.client.createapply.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 租户创建申请 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Data
@ApiModel
public class TenantCreateApplyPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @ApiModelProperty(value = "租户名称,左前缀匹配")
    private String name;





    @ApiModelProperty(value = "租户类型字典id")
    private Long tenantTypeDictId;


    @ApiModelProperty(value = "申请用户")
    private Long applyUserId;


    @ApiModelProperty(value = "审核状态")
    private Long auditStatusDictId;



    @ApiModelProperty(value = "审核用户id")
    private Long auditUserId;


    @ApiModelProperty(value = "审核通过后创建的租户id")
    private Long appliedTenantId;










}
