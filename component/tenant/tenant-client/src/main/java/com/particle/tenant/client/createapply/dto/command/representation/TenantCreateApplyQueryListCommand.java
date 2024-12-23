package com.particle.tenant.client.createapply.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 租户创建申请 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Data
@Schema
public class TenantCreateApplyQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "密码，没有指定 applyUserId 时，用户创建用户登录密码")
	private String password;

	@Schema(description = "是否发送邮件通知，1=发送，0=不发送，仅存在邮箱时发送")
	private Boolean isSendEmailNotice;

	@Schema(description = "密码，没有指定 applyUserId 时，用户创建用户登录密码")
	private Boolean isSendMobileNotice;

	@Schema(description = "账号，没有指定 applyUserId 时，用户创建用户登录账号")
	private String account;



    @Like
        @Schema(description = "租户名称,左前缀匹配")
    private String name;





    @Schema(description = "租户类型字典id")
    private Long tenantTypeDictId;


    @Schema(description = "申请用户")
    private Long applyUserId;


    @Schema(description = "审核状态")
    private Long auditStatusDictId;



    @Schema(description = "审核用户id")
    private Long auditUserId;


    @Schema(description = "审核通过后创建的租户id")
    private Long appliedTenantId;










}
