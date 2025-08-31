package com.particle.user.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class UserVO extends AbstractBaseIdVO {

    @Schema(description = "姓名，真实姓名")
    private String name;

    @Schema(description = "昵称，模糊查询")
    private String nickname;


    @Schema(description = "性别，字典id")
    private Long genderDictId;

    @Schema(description = "性别，字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "genderDictId",mapValueField = "name")
    private String genderDictName;

    @Schema(description = "头像，建议图片相对路径")
    private String avatar;

    @Schema(description = "用户编号，可以做为员工编号")
    private String serialNo;

    @Schema(description = "公司id，冗余字段，由dept_id对应公司派生")
    private Long compId;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_USER_ID,byFieldName = "id",mapValueField = "id")
    @Schema(description = "部门id")
    private Long deptId;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_USER_ID,byFieldName = "id",mapValueField = "name")
    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "是否虚拟用户，虚拟用户代表不是一个真正存在的用户")
    private Boolean isVirtual;

    @Schema(description = "锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @Schema(description = "锁定原因")
    private String lockReason;

    @Schema(description = "用户分类字典，标识是哪一类用户，比如后台用户等")
    private Long categoryDictId;

    @Schema(description = "用户分类，字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "categoryDictId",mapValueField = "name")
    private String categoryDictName;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "用户来源，字典id")
    private Long sourceFromDictId;

    @Schema(description = "用户来源，字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "sourceFromDictId",mapValueField = "name")
    private String sourceFromDictName;

    @Schema(description = "是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @Schema(description = "过期原因")
    private String expiredReason;

    @Schema(description = "到期时间，为空永不到期")
    private LocalDateTime expireAt;

	@Schema(description = "生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@Schema(description = "生效日期，触发方式，一般为首次登录触发")
	private Long effectiveAtTriggerDictId;

	@Schema(description = "有效天数,0或空为不限制")
	private Integer effectiveDays;

	@Schema(description = "备注")
	private String remark;

    @TransBy(type = TransConstants.TRANS_USER_EXTRA_INFO_BY_ID,byFieldName = "id")
    @Schema(description = "用户扩展信息")
    private UserExtraInfoVO userExtraInfoVO;
}
