package com.particle.test.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 测试 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Data
@Schema
public class TestCreateCommand extends AbstractBaseCommand {


    @Schema(description = "昵称，姓名,模糊查询")
    private String nickname;

    @Schema(description = "性别，字典id")
    private Long genderDictId;

    @Schema(description = "头像，建议图片相对路径")
    private String avatar;

    @Schema(description = "锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @Schema(description = "锁定原因")
    private String lockReason;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "用户来源，字典id")
    private Long sourceFromDictId;


}
