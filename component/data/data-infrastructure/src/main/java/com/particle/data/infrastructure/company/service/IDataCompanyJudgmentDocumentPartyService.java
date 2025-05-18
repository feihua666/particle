package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业裁判文书当事人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
public interface IDataCompanyJudgmentDocumentPartyService extends IBaseService<DataCompanyJudgmentDocumentPartyDO> {

    /**
     * 根据裁判文书表id查询
     * @param companyJudgmentDocumentId
     * @return
     */
    default List<DataCompanyJudgmentDocumentPartyDO> getByCompanyJudgmentDocumentId(Long companyJudgmentDocumentId) {
        Assert.notNull(companyJudgmentDocumentId,"companyJudgmentDocumentId 不能为空");
        return list(Wrappers.<DataCompanyJudgmentDocumentPartyDO>lambdaQuery().eq(DataCompanyJudgmentDocumentPartyDO::getCompanyJudgmentDocumentId, companyJudgmentDocumentId));
    }



    /**
     * 根据裁判文书表id查询多个
     * @param companyJudgmentDocumentIds
     * @return
     */
    default List<DataCompanyJudgmentDocumentPartyDO> getByCompanyJudgmentDocumentIds(List<Long> companyJudgmentDocumentIds) {
        Assert.notEmpty(companyJudgmentDocumentIds,"companyJudgmentDocumentIds 不能为空");
        return list(Wrappers.<DataCompanyJudgmentDocumentPartyDO>lambdaQuery().in(DataCompanyJudgmentDocumentPartyDO::getCompanyJudgmentDocumentId, companyJudgmentDocumentIds));
    }




    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyJudgmentDocumentPartyDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyJudgmentDocumentPartyDO>lambdaQuery()
                .eq(DataCompanyJudgmentDocumentPartyDO::getPartyCompanyId, companyId)
        );
    }

    /**
     * 根据 裁判文书id 和 dataMd5 查询
     * @param companyJudgmentDocumentId
     * @param dataMd5
     * @return
     */
    default public DataCompanyJudgmentDocumentPartyDO getByCompanyJudgmentDocumentIdAndDataMd5(Long companyJudgmentDocumentId,String dataMd5) {
        Assert.notNull(companyJudgmentDocumentId,"companyJudgmentDocumentId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyJudgmentDocumentPartyDO>lambdaQuery()
                .eq(DataCompanyJudgmentDocumentPartyDO::getCompanyJudgmentDocumentId, companyJudgmentDocumentId)
                .eq(DataCompanyJudgmentDocumentPartyDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyJudgmentDocumentPartyDO>lambdaUpdate().eq(DataCompanyJudgmentDocumentPartyDO::getId, id)
                .set(DataCompanyJudgmentDocumentPartyDO::getLatestHandleAt, LocalDateTime.now()));
    }



}
