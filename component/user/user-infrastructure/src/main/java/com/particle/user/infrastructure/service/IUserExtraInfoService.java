package com.particle.user.infrastructure.service;

import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 用户扩展信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
public interface IUserExtraInfoService extends IBaseService<UserExtraInfoDO> {

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default UserExtraInfoDO getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return getOne(Wrappers.<UserExtraInfoDO>lambdaQuery().eq(UserExtraInfoDO::getUserId, userId));
    }



    /**
     * 根据用户id查询多个
     * @param userIds
     * @return
     */
    default List<UserExtraInfoDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<UserExtraInfoDO>lambdaQuery().in(UserExtraInfoDO::getUserId, userIds));
    }
    /**
     * 根据用户id查询多个
     * @param userIds
     * @return
     */
    default List<UserExtraInfoDO> getByUserIds(Set<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<UserExtraInfoDO>lambdaQuery().in(UserExtraInfoDO::getUserId, userIds));
    }













}
