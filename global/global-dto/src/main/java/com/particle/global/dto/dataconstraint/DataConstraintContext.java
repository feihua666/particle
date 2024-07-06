package com.particle.global.dto.dataconstraint;

import com.particle.global.dto.basic.DTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据范围约束配置
 * 注：该类本应该放在global-data-permission-boot-starter 模块中，但是由于该模块是通用的，所以放在这里，避免循环依赖
 * 需要与 {@link com.particle.global.data.permission.DataConstraintContext} 保持一致
 * </p>
 *
 * @author yangwei
 * @since 2024/7/4 14:11
 */
@Accessors(chain = true)
@Data
public class DataConstraintContext extends DTO {

    /**
     * dataObjectCode 数据对象编码
     */
    private String dataObject;

    /**
     * 数据对象名称
     */
    private String name;

    /**
     * 描述备注
     */
    private String remark;

    /**
     * 是否忽略
     */
    private Boolean isIgnore = false;

    /**
     * 执行的动作 {@link Action}
     */
    private String action;

    /**
     * 匹配动作
     * @param action
     * @return
     */
    public Boolean match(Action action) {
        return action.name().equals(action);
    }

    public static DataConstraintContext create(String dataObject, String action) {
        return new DataConstraintContext().setDataObject(dataObject).setAction(action);
    }

    /**
     * 动作
     */
    public static enum Action {
        /**
         * 删除
         */
        delete,
        /**
         * 更新
         */
        update,
        /**
         * 查询
         */
        query,
        /**
         * 其他
         */
        other,
        ;
    }
}
