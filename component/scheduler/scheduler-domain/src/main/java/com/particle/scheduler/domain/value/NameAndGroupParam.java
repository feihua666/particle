package com.particle.scheduler.domain.value;

import com.particle.common.domain.ValueObjRoot;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * NameAndGroup表单对象
 * Created by yangwei
 * Created at 2021/2/2 17:25
 */
@Setter
@Getter
public class NameAndGroupParam extends ValueObjRoot {

    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;

    /**
     * 组
     */
    @NotEmpty(message = "组不能为空")
    private String group;
}
