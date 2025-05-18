package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import com.particle.data.infrastructure.company.dto.DataCompanyJudgmentDocumentListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业裁判文书 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
public interface IDataCompanyJudgmentDocumentService extends IBaseService<DataCompanyJudgmentDocumentDO> {

    /**
     * 分页查询裁判文书
     * @param param
     * @param pageQueryForm
     * @return
     */
    public Page<DataCompanyJudgmentDocumentDO> listPage(DataCompanyJudgmentDocumentListPageByCompanyIdParam param, PageQueryCommand pageQueryForm);
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param caseNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyJudgmentDocumentDO> listPageByCompanyId(Long companyId,String caseNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        return listPage(DataCompanyJudgmentDocumentListPageByCompanyIdParam.create(companyId, caseNo), pageQueryForm);
    }

    /**
     * 根据 dataMd5 查询
     * @param dataMd5
     * @return
     */
    default public DataCompanyJudgmentDocumentDO getByDataMd5(String dataMd5) {
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyJudgmentDocumentDO>lambdaQuery()
                .eq(DataCompanyJudgmentDocumentDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyJudgmentDocumentDO>lambdaUpdate().eq(DataCompanyJudgmentDocumentDO::getId, id)
                .set(DataCompanyJudgmentDocumentDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
