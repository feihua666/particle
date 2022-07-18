package com.particle.global.mybatis.plus.crud;

/**
 * <p>
 * service添加方法监听
 * </p>
 *
 * @author yangwei
 * @since 2020-11-22 11:00
 */
public interface IAddServiceListener<DO> {
    /**
     * 添加前调用
     * @see IBaseServiceImpl#add(com.particle.global.mybatis.plus.dto.BaseDO)
     * @param po
     */
    default void preAdd(DO po) {

    }

    /**
     * 添加后调用
     * @see IBaseServiceImpl#add(com.particle.global.mybatis.plus.dto.BaseDO)
     * @param po
     */
    default void postAdd(DO po) {

    }
}
