package com.particle.func.domain.gateway;

import com.particle.func.domain.Func;

/**
 * @program: particle
 * @description: 菜单防腐层
 * @author: 许宝华
 * @create: 2022-07-02 20:02
 */

public interface FuncGateway {



    /**
     * 保存菜单领域对象
     * @param func 菜单对象
     * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
     *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
     */
    boolean save(Func func);
}
