package com.particle.data.infrastructure.company.service;

import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业裁判文书内容 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
public interface IDataCompanyJudgmentDocumentContentService extends IBaseService<DataCompanyJudgmentDocumentContentDO> {

    /**
     * 根据裁判文书表id查询
     * @param companyJudgmentDocumentId
     * @return
     */
    default DataCompanyJudgmentDocumentContentDO getByCompanyJudgmentDocumentId(Long companyJudgmentDocumentId) {
        Assert.notNull(companyJudgmentDocumentId,"companyJudgmentDocumentId 不能为空");
        return getOne(Wrappers.<DataCompanyJudgmentDocumentContentDO>lambdaQuery().eq(DataCompanyJudgmentDocumentContentDO::getCompanyJudgmentDocumentId, companyJudgmentDocumentId));
    }



    /**
     * 根据裁判文书表id查询多个
     * @param companyJudgmentDocumentIds
     * @return
     */
    default List<DataCompanyJudgmentDocumentContentDO> listByCompanyJudgmentDocumentIds(List<Long> companyJudgmentDocumentIds) {
        Assert.notEmpty(companyJudgmentDocumentIds,"companyJudgmentDocumentIds 不能为空");
        return list(Wrappers.<DataCompanyJudgmentDocumentContentDO>lambdaQuery().in(DataCompanyJudgmentDocumentContentDO::getCompanyJudgmentDocumentId, companyJudgmentDocumentIds));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyJudgmentDocumentContentDO>lambdaUpdate().eq(DataCompanyJudgmentDocumentContentDO::getId, id)
                .set(DataCompanyJudgmentDocumentContentDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
