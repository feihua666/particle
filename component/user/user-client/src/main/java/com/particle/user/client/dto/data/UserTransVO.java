package com.particle.user.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
@ApiModel
public class UserTransVO extends AbstractBaseIdVO {

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;
}
