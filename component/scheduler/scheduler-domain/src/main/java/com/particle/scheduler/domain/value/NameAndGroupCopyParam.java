package com.particle.scheduler.domain.value;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * NameAndGroup表单对象
 * Created by yangwei
 * Created at 2021/2/2 17:25
 */
@Setter
@Getter
public class NameAndGroupCopyParam extends NameAndGroupParam {

    /**
     * 复制名称
     */
    @NotEmpty(message = "复制名称不能为空")
    private String copyName;

    /**
     * 复制组
     */
    @NotEmpty(message = "复制组不能为空")
    private String copyGroup;
}
