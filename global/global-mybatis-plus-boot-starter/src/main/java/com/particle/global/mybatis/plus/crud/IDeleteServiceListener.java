package com.particle.global.mybatis.plus.crud;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.util.List;

/**
 * <p>
 * service删除方法监听
 * </p>
 *
 * @author yangwei
 * @since 2020-11-22 11:00
 */
public interface IDeleteServiceListener<Po> {

    /**
     * 删除前调用
     * @see IBaseServiceImpl#deleteById(java.lang.Long)
     * @param id 主键id
     * @param po 要删除的数据
     */
    default void preDeleteById(Long id,Po po) {

    }

    /**
     * 删除后调用
     * @see IBaseServiceImpl#deleteById(java.lang.Long)
     * @param id 主键id
     * @param po 要删除的数据
     */
    default void postDeleteById(Long id,Po po) {

    }

    /**
     * 删除前调用
     * @see IBaseServiceImpl#deleteByColumn(java.lang.Object, com.baomidou.mybatisplus.core.toolkit.support.SFunction)
     * @param columnId 删除的筛选值
     * @param column 删除的列名
     * @param pos 删除的数据
     */
    default void preDeleteByColumn(Object columnId, SFunction<Po, ?> column, List<Po> pos) {

    }

    /**
     * 删除后调用
     * @see IBaseServiceImpl#deleteByColumn(java.lang.Object, com.baomidou.mybatisplus.core.toolkit.support.SFunction)
     * @param columnId 删除的筛选值
     * @param column 删除的列名
     * @param pos 删除的数据
     */
    default void postDeleteByColumn(Object columnId , SFunction<Po, ?> column, List<Po> pos) {

    }
}
