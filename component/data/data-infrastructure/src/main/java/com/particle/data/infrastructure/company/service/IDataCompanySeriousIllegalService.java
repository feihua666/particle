package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业严重违法 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
public interface IDataCompanySeriousIllegalService extends IBaseService<DataCompanySeriousIllegalDO> {

    /**
     * 根据 公司id 和 dataMd5 查询
     * @param companyId
     * @param dataMd5
     * @return
     */
    default public DataCompanySeriousIllegalDO getByCompanyIdAndDataMd5(Long companyId, String dataMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanySeriousIllegalDO>lambdaQuery().eq(DataCompanySeriousIllegalDO::getCompanyId, companyId).eq(DataCompanySeriousIllegalDO::getDataMd5, dataMd5));
    }
    /**
     * 根据企业表ID查询列表
     * @param companyId
     * @return
     */
    default public List<DataCompanySeriousIllegalDO> listByCompanyId(Long companyId){
        Assert.notNull(companyId,"companyId 不能为空");
        return list(Wrappers.<DataCompanySeriousIllegalDO>lambdaQuery().eq(DataCompanySeriousIllegalDO::getCompanyId, companyId));
    }

    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanySeriousIllegalDO> listPageByCompanyId(Long companyId,PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery,Wrappers.<DataCompanySeriousIllegalDO>lambdaQuery()
                .eq(DataCompanySeriousIllegalDO::getCompanyId, companyId));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanySeriousIllegalDO>lambdaUpdate().eq(DataCompanySeriousIllegalDO::getId, id)
                .set(DataCompanySeriousIllegalDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
