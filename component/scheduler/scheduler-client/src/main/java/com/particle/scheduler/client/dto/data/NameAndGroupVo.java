package com.particle.scheduler.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
@Schema(description="NameAndGroup响应数据对象")
public class NameAndGroupVo extends VO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "组")
    private String group;
}
