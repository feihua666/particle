package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权商标许可人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
public interface IDataCompanyIprTrademarkLicensePersonService extends IBaseService<DataCompanyIprTrademarkLicensePersonDO> {



    /**
     * 根据企业知识产权商标许可表id查询多个
     * @param companyIprTrademarkLicenseIds
     * @return
     */
    default List<DataCompanyIprTrademarkLicensePersonDO> listByCompanyIprTrademarkLicenseIds(List<Long> companyIprTrademarkLicenseIds) {
        Assert.notEmpty(companyIprTrademarkLicenseIds,"companyIprTrademarkLicenseIds 不能为空");
        return list(Wrappers.<DataCompanyIprTrademarkLicensePersonDO>lambdaQuery().in(DataCompanyIprTrademarkLicensePersonDO::getCompanyIprTrademarkLicenseId, companyIprTrademarkLicenseIds));
    }
    /**
     * 根据商标许可ID分页查询列表
     * @param companyIprTrademarkLicenseId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprTrademarkLicensePersonDO> listPageByCompanyIprTrademarkLicenseId(Long companyIprTrademarkLicenseId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprTrademarkLicenseId,"companyIprTrademarkLicenseId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprTrademarkLicensePersonDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkLicensePersonDO::getCompanyIprTrademarkLicenseId, companyIprTrademarkLicenseId)
        );
    }
    /**
     * 根据 商标许可id 和 dataMd5 查询
     * @param companyIprTrademarkLicenseId
     * @param dataMd5
     * @return
     */
    default public DataCompanyIprTrademarkLicensePersonDO getByCompanyIprTrademarkLicenseIdAndDataMd5(Long companyIprTrademarkLicenseId, String dataMd5) {
        Assert.notNull(companyIprTrademarkLicenseId,"companyIprTrademarkLicenseId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyIprTrademarkLicensePersonDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkLicensePersonDO::getCompanyIprTrademarkLicenseId, companyIprTrademarkLicenseId)
                .eq(DataCompanyIprTrademarkLicensePersonDO::getDataMd5, dataMd5)
        );
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprTrademarkLicensePersonDO>lambdaUpdate().eq(DataCompanyIprTrademarkLicensePersonDO::getId, id)
                .set(DataCompanyIprTrademarkLicensePersonDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
