package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.data.infrastructure.company.dto.DataCompanyCaseFilingListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业立案信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
public interface IDataCompanyCaseFilingService extends IBaseService<DataCompanyCaseFilingDO> {

    /**
     * 分页查询立案信息
     * @param param
     * @param pageQueryForm
     * @return
     */
    public Page<DataCompanyCaseFilingDO> listPage(DataCompanyCaseFilingListPageByCompanyIdParam param, PageQueryCommand pageQueryForm);
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param caseNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyCaseFilingDO> listPageByCompanyId(Long companyId, String caseNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        return listPage(DataCompanyCaseFilingListPageByCompanyIdParam.create(companyId, caseNo), pageQueryForm);
    }

    /**
     * 根据 dataMd5 查询
     * @param dataMd5
     * @return
     */
    default public DataCompanyCaseFilingDO getByDataMd5(String dataMd5) {
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyCaseFilingDO>lambdaQuery()
                .eq(DataCompanyCaseFilingDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyCaseFilingDO>lambdaUpdate().eq(DataCompanyCaseFilingDO::getId, id)
                .set(DataCompanyCaseFilingDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
