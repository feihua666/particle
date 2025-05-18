package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业股东 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
public interface IDataCompanyShareholderService extends IBaseService<DataCompanyShareholderDO> {

    /**
     * 根据公司id和股东名称查询
     * @param companyId
     * @param dataMd5
     * @return
     */
    default public DataCompanyShareholderDO getByCompanyIdAndDataMd5(Long companyId, String dataMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyShareholderDO>lambdaQuery().eq(DataCompanyShareholderDO::getCompanyId, companyId).eq(DataCompanyShareholderDO::getDataMd5, dataMd5));
    }
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyShareholderDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyShareholderDO>lambdaQuery().eq(DataCompanyShareholderDO::getCompanyId, companyId));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyShareholderDO>lambdaUpdate().eq(DataCompanyShareholderDO::getId, id)
                .set(DataCompanyShareholderDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
