package com.particle.user.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class UserPageQueryCommand extends AbstractBasePageQueryCommand {

    @Like(left = true,right = true)
    @Schema(description = "姓名，左前缀匹配")
    private String name;

    @Like(left = true,right = true)
    @Schema(description = "昵称，左前缀匹配")
    private String nickname;

    @Schema(description = "性别，字典id")
    private Long genderDictId;

    @Schema(description = "用户编号，可以做为员工编号")
    private String serialNo;

    @Schema(description = "公司id，冗余字段，由dept_id对应公司派生")
    private Long compId;

    @Schema(description = "部门id")
    private Long deptId;

    @Schema(description = "是否虚拟用户，虚拟用户代表不是一个真正存在的用户")
    private Boolean isVirtual;

    @Schema(description = "锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @Schema(description = "用户分类字典，标识是哪一类用户，比如后台用户等")
    private Long categoryDictId;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "用户来源，字典id")
    private Long sourceFromDictId;

    @Schema(description = "是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @Schema(description = "是否包含角色信息响应")
    private Boolean isIncludeRoleInfo;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "角色名称，模糊匹配")
    private String roleName;

    @Schema(description = "角色类型字典id")
    private Long roleTypeDictId;

    @Schema(description = "用户标识id")
    private Long identifierId;

    @Schema(description = "登录标识，模糊匹配")
    private String userIdentifier;
}
