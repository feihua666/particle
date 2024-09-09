package com.particle.scheduler.domain.value;

import com.particle.global.dto.basic.VO;
import lombok.Getter;
import lombok.Setter;

/**
 * NameAndGroup响应数据对象
 * Created by yangwei
 * Created at 2021/2/2 13:45
 */
@Setter
@Getter
public class NameAndGroupDTO extends VO {

    /**
     * 名称
     */
    private String name;
    /**
     * 组
     */
    private String group;
}
