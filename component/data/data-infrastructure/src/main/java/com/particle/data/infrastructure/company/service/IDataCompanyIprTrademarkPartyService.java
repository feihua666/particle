package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权商标当事人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
public interface IDataCompanyIprTrademarkPartyService extends IBaseService<DataCompanyIprTrademarkPartyDO> {



    /**
     * 根据商标ID查询列表
     * @param companyIprTrademarkIds
     * @return
     */
    default public List<DataCompanyIprTrademarkPartyDO> listByCompanyIprTrademarkIds(List<Long> companyIprTrademarkIds) {
        Assert.notEmpty(companyIprTrademarkIds,"companyIprTrademarkIds 不能为空");
        return list(Wrappers.<DataCompanyIprTrademarkPartyDO>lambdaQuery()
                .in(DataCompanyIprTrademarkPartyDO::getCompanyIprTrademarkId, companyIprTrademarkIds)
        );
    }

    /**
     * 根据商标ID分页查询列表
     * @param companyIprTrademarkId
     * @param companyId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprTrademarkPartyDO> listPageByCompanyIprTrademarkId(Long companyIprTrademarkId, Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprTrademarkId,"companyIprTrademarkId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprTrademarkPartyDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkPartyDO::getCompanyIprTrademarkId, companyIprTrademarkId)
                .eq(companyId != null,DataCompanyIprTrademarkPartyDO::getPartyCompanyId, companyId)
        );
    }

    /**
     * 根据 商标id 和 dataMd5 查询
     * @param companyIprTrademarkId
     * @param dataMd5
     * @return
     */
    default public DataCompanyIprTrademarkPartyDO getByCompanyIprTrademarkIdAndDataMd5(Long companyIprTrademarkId,String dataMd5) {
        Assert.notNull(companyIprTrademarkId,"companyIprTrademarkId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyIprTrademarkPartyDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkPartyDO::getCompanyIprTrademarkId, companyIprTrademarkId)
                .eq(DataCompanyIprTrademarkPartyDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprTrademarkPartyDO>lambdaUpdate().eq(DataCompanyIprTrademarkPartyDO::getId, id)
                .set(DataCompanyIprTrademarkPartyDO::getLatestHandleAt, LocalDateTime.now()));
    }
}
