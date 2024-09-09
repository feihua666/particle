package com.particle.scheduler.domain.value;

import com.particle.common.domain.ValueObjRoot;
import lombok.Getter;
import lombok.Setter;

/**
 * NameAndGroupQuery表单对象
 * Created by yangwei
 * Created at 2021/2/2 17:25
 */
@Setter
@Getter
public class NameAndGroupQueryParam extends ValueObjRoot {

    /**
     * 名称
     */
    private String name;

    /**
     * 组
     */
    private String group;
}
