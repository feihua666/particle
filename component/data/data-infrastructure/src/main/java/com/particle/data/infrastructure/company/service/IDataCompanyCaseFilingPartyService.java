package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业立案信息当事人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
public interface IDataCompanyCaseFilingPartyService extends IBaseService<DataCompanyCaseFilingPartyDO> {

    /**
     * 根据立案信息表id查询
     * @param companyCaseFilingId
     * @return
     */
    default List<DataCompanyCaseFilingPartyDO> getByCompanyCaseFilingId(Long companyCaseFilingId) {
        Assert.notNull(companyCaseFilingId,"companyCaseFilingId 不能为空");
        return list(Wrappers.<DataCompanyCaseFilingPartyDO>lambdaQuery().eq(DataCompanyCaseFilingPartyDO::getCompanyCaseFilingId, companyCaseFilingId));
    }



    /**
     * 根据立案信息表id查询多个
     * @param companyCaseFilingIds
     * @return
     */
    default List<DataCompanyCaseFilingPartyDO> listByCompanyCaseFilingIds(List<Long> companyCaseFilingIds) {
        Assert.notEmpty(companyCaseFilingIds,"companyCaseFilingIds 不能为空");
        return list(Wrappers.<DataCompanyCaseFilingPartyDO>lambdaQuery().in(DataCompanyCaseFilingPartyDO::getCompanyCaseFilingId, companyCaseFilingIds));
    }


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyCaseFilingPartyDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyCaseFilingPartyDO>lambdaQuery()
                .eq(DataCompanyCaseFilingPartyDO::getPartyCompanyId, companyId)
        );
    }

    /**
     * 根据 立案信息id 和 dataMd5 查询
     * @param companyCaseFilingId
     * @param dataMd5
     * @return
     */
    default public DataCompanyCaseFilingPartyDO getByCompanyCaseFilingIdAndDataMd5(Long companyCaseFilingId,String dataMd5) {
        Assert.notNull(companyCaseFilingId,"companyCaseFilingId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyCaseFilingPartyDO>lambdaQuery()
                .eq(DataCompanyCaseFilingPartyDO::getCompanyCaseFilingId, companyCaseFilingId)
                .eq(DataCompanyCaseFilingPartyDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyCaseFilingPartyDO>lambdaUpdate().eq(DataCompanyCaseFilingPartyDO::getId, id)
                .set(DataCompanyCaseFilingPartyDO::getLatestHandleAt, LocalDateTime.now()));
    }



}
