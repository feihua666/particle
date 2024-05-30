package com.particle.dream.infrastructure.ssq.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 双色球开奖 服务类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
public interface ISsqCodeOpenedService extends IBaseService<SsqCodeOpenedDO> {

    /**
     * 获取最大开奖年份
     * @return
     */
    default SsqCodeOpenedDO getMaxOpenedPhaseYear() {

        Page<SsqCodeOpenedDO> page = page(
                new Page<>(1, 1),
                Wrappers.<SsqCodeOpenedDO>lambdaQuery().orderByDesc(SsqCodeOpenedDO::getOpenedPhaseYear)
        );
        List<SsqCodeOpenedDO> records = page.getRecords();
        return records.stream().findFirst().orElse(null);
    }

    /**
     * 根据年份查询
     * @param openedPhaseYear
     * @return
     */
    default List<SsqCodeOpenedDO> listByOpenedPhaseYear(Integer openedPhaseYear) {
        return list(Wrappers.<SsqCodeOpenedDO>lambdaQuery().eq(SsqCodeOpenedDO::getOpenedPhaseYear,openedPhaseYear));
    }
}
