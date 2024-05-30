package com.particle.dream.infrastructure.ssq.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 双色球号码 服务类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
public interface ISsqCodeService extends IBaseService<SsqCodeDO> {

    /**
     * 根据序号查询
     * @param seqNo
     * @return
     */
    default SsqCodeDO getBySeqNo(Integer seqNo) {
        Assert.notNull(seqNo,"seqNo 不能为空");
        return getOne(Wrappers.<SsqCodeDO>lambdaQuery().eq(SsqCodeDO::getSeqNo, seqNo));
    }

    /**
     * 获取最大序号
     * @return
     */
    default SsqCodeDO getMaxSeqNo() {

        Page<SsqCodeDO> page = page(
                new Page<>(1, 1),
                Wrappers.<SsqCodeDO>lambdaQuery().orderByDesc(SsqCodeDO::getSeqNo)
        );
        List<SsqCodeDO> records = page.getRecords();
        return records.stream().findFirst().orElse(null);
    }
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
    default SsqCodeDO getByBall(Integer red1,Integer red2,Integer red3,Integer red4,Integer red5,Integer red6,Integer blue) {
        return getOne(Wrappers.<SsqCodeDO>lambdaQuery()
                .eq(SsqCodeDO::getRed1, red1)
                .eq(SsqCodeDO::getRed2, red2)
                .eq(SsqCodeDO::getRed3, red3)
                .eq(SsqCodeDO::getRed4, red4)
                .eq(SsqCodeDO::getRed5, red5)
                .eq(SsqCodeDO::getRed6, red6)
                .eq(SsqCodeDO::getBlue, blue));

    }



    /**
     * 根据序号查询多个
     * @param seqNos
     * @return
     */
    default List<SsqCodeDO> getBySeqNos(List<Integer> seqNos) {
        Assert.notEmpty(seqNos,"seqNos 不能为空");
        return list(Wrappers.<SsqCodeDO>lambdaQuery().in(SsqCodeDO::getSeqNo, seqNos));
    }

    /**
     * 更新是否开奖状态
     * @param id
     * @param idOpened
     * @return
     */
    default boolean updateSetIsOpened(Long id, Boolean idOpened) {
        SsqCodeDO ssqCodeDO = new SsqCodeDO();
        ssqCodeDO.setId(id);

        ssqCodeDO.setIsOpened(idOpened);
        return updateById(ssqCodeDO);
    }






























}
