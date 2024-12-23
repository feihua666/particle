package com.particle.user.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

/**
 * <p>
 * 用户 翻译结果
 * </p>
 *
 * @author yw
 * @since 2022-12-29
 */
@Accessors(chain = true)
@Data
@Schema
public class UserTransVO extends AbstractBaseIdVO {

    @Schema(description = "用户姓名")
    private String name;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "优先取姓名，如果姓名不存在取昵称")
    private String abName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;


    public void changeAbNameIfNecessary() {
        abName = Optional.ofNullable(name).orElse(nickname);
    }

    public void changeName(String name) {
        this.name = name;
        changeAbNameIfNecessary();
    }
}
