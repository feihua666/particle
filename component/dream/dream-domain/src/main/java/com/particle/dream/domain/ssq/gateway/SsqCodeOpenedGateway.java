package com.particle.dream.domain.ssq.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.dream.domain.ssq.SsqCodeOpened;
import com.particle.dream.domain.ssq.SsqCodeOpenedId;

import java.util.List;

/**
 * <p>
 * 双色球开奖 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
public interface SsqCodeOpenedGateway extends IBaseGateway<SsqCodeOpenedId,SsqCodeOpened> {


    /**
     * 批量添加，在生成全部号码时保存太慢了，这里加一个指保存
     * @param aggreateRoots
     * @return
     */
    boolean addBatch(List<SsqCodeOpened> aggreateRoots);

    /**
     * 批量更新
     * @param aggreateRoots
     * @return
     */
    boolean updateBatch(List<SsqCodeOpened> aggreateRoots);
}
