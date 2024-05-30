package com.particle.dream.domain.ssq.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.dream.domain.ssq.value.SsqCodeCrawlingResult;

import java.util.List;

/**
 * <p>
 * 双色球号码爬虫网关
 * 双色球从2003年开始，但在官网上只有2013年以后才有开奖号码，所以2003年到2013年以前的号码从其它网站获取
 * </p>
 *
 * @author yangwei
 * @since 2024/5/20 11:33
 */
public interface SsqCodeCrawlingGateway extends IGateway {

    /**
     * 根据年份爬取一年的已开奖双色球号码
     * @param openedPhaseYear
     */
    public List<SsqCodeCrawlingResult> crawlingByYear(Integer openedPhaseYear);

    /**
     * 根据开奖期数爬取已开奖双色球号码
     * @param startOpenedPhaseYear
     * @param startOpenedPhaseNum
     * @param endOpenedPhaseYear
     * @param endOpenedPhaseNum
     */
    public List<SsqCodeCrawlingResult> crawlingByPhase(Integer startOpenedPhaseYear,
                                                       Integer startOpenedPhaseNum,
                                                       Integer endOpenedPhaseYear,
                                                       Integer endOpenedPhaseNum);
}
