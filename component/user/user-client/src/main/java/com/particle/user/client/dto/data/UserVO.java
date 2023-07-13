package com.particle.user.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class UserVO extends AbstractBaseIdVO {

    @ApiModelProperty("姓名，真实姓名")
    private String name;
    
    @ApiModelProperty("昵称，模糊查询")
    private String nickname;


    @ApiModelProperty("性别，字典id")
    private Long genderDictId;

    @ApiModelProperty("性别，字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "genderDictId",mapValueField = "name")
    private String genderDictName;

    @ApiModelProperty("头像，建议图片相对路径")
    private String avatar;

    @ApiModelProperty("用户编号，可以做为员工编号")
    private String serialNo;

    @ApiModelProperty("公司id，冗余字段，由dept_id对应公司派生")
    private Long compId;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_USER_ID,byFieldName = "id",mapValueField = "id")
    @ApiModelProperty("部门id")
    private Long deptId;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_USER_ID,byFieldName = "id",mapValueField = "name")
    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("是否虚拟用户，虚拟用户代表不是一个真正存在的用户")
    private Boolean isVirtual;

    @ApiModelProperty("锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @ApiModelProperty("锁定原因")
    private String lockReason;

    @ApiModelProperty("用户分类字典，标识是哪一类用户，比如后台用户等")
    private Long categoryDictId;

    @ApiModelProperty("用户分类，字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "categoryDictId",mapValueField = "name")
    private String categoryDictName;

    @ApiModelProperty("分组标识")
    private String groupFlag;

    @ApiModelProperty("用户来源，字典id")
    private Long sourceFromDictId;

    @ApiModelProperty("用户来源，字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "sourceFromDictId",mapValueField = "name")
    private String sourceFromDictName;

    @ApiModelProperty("是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @ApiModelProperty("过期原因")
    private String expiredReason;

    @ApiModelProperty("到期时间，为空永不到期")
    private LocalDateTime expireAt;


}
