package com.particle.tracking.infrastructure.service;

import com.particle.tracking.infrastructure.dos.TrackingPageDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 埋点页面 服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
public interface ITrackingPageService extends IBaseService<TrackingPageDO> {

    /**
     * 根据页面编码查询
     * @param code
     * @return
     */
    default TrackingPageDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<TrackingPageDO>lambdaQuery().eq(TrackingPageDO::getCode, code));
    }



    /**
     * 根据页面编码查询多个
     * @param codes
     * @return
     */
    default List<TrackingPageDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<TrackingPageDO>lambdaQuery().in(TrackingPageDO::getCode, codes));
    }
            




























}
