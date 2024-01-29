package com.particle.global.tool.obj;

import java.io.Serializable;

/**
 * <p>
 * 定义一个null对象用来充当null
 * </p>
 *
 * @author yangwei
 * @since 2024/1/25 13:06
 */
public class NullObj implements Serializable {

    /**
     * 默认实例
     */
    public static final NullObj NULL = new NullObj();

    @Override
    public boolean equals(Object object) {
        return object == null || (object == this);
    }

    @Override
    public String toString() {
        return null;
    }
}
