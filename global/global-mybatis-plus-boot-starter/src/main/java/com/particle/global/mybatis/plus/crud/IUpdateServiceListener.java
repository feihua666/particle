package com.particle.global.mybatis.plus.crud;

/**
 * <p>
 * service更新方法监听
 * </p>
 *
 * @author yangwei
 * @since 2020-11-22 11:00
 */
public interface IUpdateServiceListener<DO> {
    /**
     * 在更新前调用
     * @see IBaseServiceImpl#update(com.particle.global.mybatis.plus.dto.BaseDO)
     * @param po
     */
    default void preUpdate(DO po) {

    }

    /**
     * 在更新后调用
     * @see IBaseServiceImpl#update(com.particle.global.mybatis.plus.dto.BaseDO)
     * @param po
     */
    default void postUpdate(DO po) {

    }
}
