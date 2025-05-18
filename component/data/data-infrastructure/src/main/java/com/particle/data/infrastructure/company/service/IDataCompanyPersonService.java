package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业个人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
public interface IDataCompanyPersonService extends IBaseService<DataCompanyPersonDO> {

    /**
     * 根据 idNo 查询
     * @param idNo
     * @return
     */
    default DataCompanyPersonDO getByIdNo(String idNo) {
        Assert.notNull(idNo,"idNo 不能为空");
        return getOne(Wrappers.<DataCompanyPersonDO>lambdaQuery().eq(DataCompanyPersonDO::getIdNo, idNo));
    }

    /**
     * 根据 idNoMd5 查询
     * @param idNoMd5
     * @return
     */
    default DataCompanyPersonDO getByIdNoMd5(String idNoMd5) {
        Assert.notNull(idNoMd5,"idNoMd5 不能为空");
        return getOne(Wrappers.<DataCompanyPersonDO>lambdaQuery().eq(DataCompanyPersonDO::getIdNoMd5, idNoMd5));
    }

    /**
     * 根据 idNoSha256 查询
     * @param idNoSha256
     * @return
     */
    default DataCompanyPersonDO getByIdNoSha256(String idNoSha256) {
        Assert.notNull(idNoSha256,"idNoSha256 不能为空");
        return getOne(Wrappers.<DataCompanyPersonDO>lambdaQuery().eq(DataCompanyPersonDO::getIdNoSha256, idNoSha256));
    }

    /**
     * 根据 idNoSm3 查询
     * @param idNoSm3
     * @return
     */
    default DataCompanyPersonDO getByIdNoSm3(String idNoSm3) {
        Assert.notNull(idNoSm3,"idNoSm3 不能为空");
        return getOne(Wrappers.<DataCompanyPersonDO>lambdaQuery().eq(DataCompanyPersonDO::getIdNoSm3, idNoSm3));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyPersonDO>lambdaUpdate().eq(DataCompanyPersonDO::getId, id)
                .set(DataCompanyPersonDO::getLatestHandleAt, LocalDateTime.now()));
    }
}
