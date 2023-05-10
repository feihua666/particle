package com.particle.tracking.infrastructure.service;

import com.particle.tracking.infrastructure.dos.TrackingPageRecordDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 页面埋点记录 服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
public interface ITrackingPageRecordService extends IBaseService<TrackingPageRecordDO> {

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default List<TrackingPageRecordDO> getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return list(Wrappers.<TrackingPageRecordDO>lambdaQuery().eq(TrackingPageRecordDO::getUserId, userId));
    }



    /**
     * 根据用户id查询多个
     * @param userIds
     * @return
     */
    default List<TrackingPageRecordDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<TrackingPageRecordDO>lambdaQuery().in(TrackingPageRecordDO::getUserId, userIds));
    }
            









    /**
     * 根据埋点页面编码查询
     * @param trackingPageCode
     * @return
     */
    default List<TrackingPageRecordDO> getByTrackingPageCode(String trackingPageCode) {
        Assert.notNull(trackingPageCode,"trackingPageCode 不能为空");
        return list(Wrappers.<TrackingPageRecordDO>lambdaQuery().eq(TrackingPageRecordDO::getTrackingPageCode, trackingPageCode));
    }



    /**
     * 根据埋点页面编码查询多个
     * @param trackingPageCodes
     * @return
     */
    default List<TrackingPageRecordDO> getByTrackingPageCodes(List<String> trackingPageCodes) {
        Assert.notEmpty(trackingPageCodes,"trackingPageCodes 不能为空");
        return list(Wrappers.<TrackingPageRecordDO>lambdaQuery().in(TrackingPageRecordDO::getTrackingPageCode, trackingPageCodes));
    }
            

    /**
     * 根据埋点前驱页面编码查询
     * @param preTrackingPageCode
     * @return
     */
    default List<TrackingPageRecordDO> getByPreTrackingPageCode(String preTrackingPageCode) {
        Assert.notNull(preTrackingPageCode,"preTrackingPageCode 不能为空");
        return list(Wrappers.<TrackingPageRecordDO>lambdaQuery().eq(TrackingPageRecordDO::getPreTrackingPageCode, preTrackingPageCode));
    }



    /**
     * 根据埋点前驱页面编码查询多个
     * @param preTrackingPageCodes
     * @return
     */
    default List<TrackingPageRecordDO> getByPreTrackingPageCodes(List<String> preTrackingPageCodes) {
        Assert.notEmpty(preTrackingPageCodes,"preTrackingPageCodes 不能为空");
        return list(Wrappers.<TrackingPageRecordDO>lambdaQuery().in(TrackingPageRecordDO::getPreTrackingPageCode, preTrackingPageCodes));
    }
            


























}
