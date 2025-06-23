package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业终本案件 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
public interface IDataCompanyEndCaseService extends IBaseService<DataCompanyEndCaseDO> {


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param caseNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyEndCaseDO> listPageByCompanyId(Long companyId, String caseNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyEndCaseDO>lambdaQuery()
                .eq(DataCompanyEndCaseDO::getExecutedPersonCompanyId, companyId)
                .eq(caseNo != null,DataCompanyEndCaseDO::getCaseNo, caseNo)
        );
    }

    /**
     * 根据 公司id 和 dataMd5 查询
     * @param executedPersonCompanyId
     * @param dataMd5
     * @return
     */
    default public DataCompanyEndCaseDO getByExecutedPersonCompanyIdAndDataMd5(Long executedPersonCompanyId,String dataMd5) {
        Assert.notNull(executedPersonCompanyId,"executedPersonCompanyId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyEndCaseDO>lambdaQuery()
                .eq(DataCompanyEndCaseDO::getExecutedPersonCompanyId, executedPersonCompanyId)
                .eq(DataCompanyEndCaseDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyEndCaseDO>lambdaUpdate().eq(DataCompanyEndCaseDO::getId, id)
                .set(DataCompanyEndCaseDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
