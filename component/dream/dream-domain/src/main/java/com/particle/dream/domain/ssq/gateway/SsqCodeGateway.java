package com.particle.dream.domain.ssq.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.dream.domain.ssq.SsqCode;
import com.particle.dream.domain.ssq.SsqCodeId;

import java.util.List;

/**
 * <p>
 * 双色球号码 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
public interface SsqCodeGateway extends IBaseGateway<SsqCodeId,SsqCode> {

    /**
     * 根据球号查询
     * @param red1
     * @param red2
     * @param red3
     * @param red4
     * @param red5
     * @param red6
     * @param blue
     * @return
     */
    SsqCode getByBall(Integer red1,Integer red2,Integer red3,Integer red4,Integer red5,Integer red6,Integer blue);

    /**
     * 批量添加，在生成全部号码时保存太慢了，这里加一个指保存
     * @param aggreateRoots
     * @return
     */
    boolean addBatch(List<SsqCode> aggreateRoots);

    /**
     * 批量更新
     * @param aggreateRoots
     * @return
     */
    boolean updateBatch(List<SsqCode> aggreateRoots);
}
