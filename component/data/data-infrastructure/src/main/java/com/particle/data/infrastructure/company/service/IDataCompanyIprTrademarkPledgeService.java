package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权商标质押信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
public interface IDataCompanyIprTrademarkPledgeService extends IBaseService<DataCompanyIprTrademarkPledgeDO> {


    /**
     * 根据企业知识产权商标表id查询多个
     * @param companyIprTrademarkIds
     * @return
     */
    default List<DataCompanyIprTrademarkPledgeDO> listByCompanyIprTrademarkIds(List<Long> companyIprTrademarkIds) {
        Assert.notEmpty(companyIprTrademarkIds,"companyIprTrademarkIds 不能为空");
        return list(Wrappers.<DataCompanyIprTrademarkPledgeDO>lambdaQuery().in(DataCompanyIprTrademarkPledgeDO::getCompanyIprTrademarkId, companyIprTrademarkIds));
    }
    /**
     * 根据商标ID分页查询列表
     * @param companyIprTrademarkId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprTrademarkPledgeDO> listPageByCompanyIprTrademarkId(Long companyIprTrademarkId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprTrademarkId,"companyIprTrademarkId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprTrademarkPledgeDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkPledgeDO::getCompanyIprTrademarkId, companyIprTrademarkId)
        );
    }
    /**
     * 根据 商标id 和 dataMd5 查询
     * @param companyIprTrademarkId
     * @param dataMd5
     * @return
     */
    default public DataCompanyIprTrademarkPledgeDO getByCompanyIprTrademarkIdAndDataMd5(Long companyIprTrademarkId, String dataMd5) {
        Assert.notNull(companyIprTrademarkId,"companyIprTrademarkId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyIprTrademarkPledgeDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkPledgeDO::getCompanyIprTrademarkId, companyIprTrademarkId)
                .eq(DataCompanyIprTrademarkPledgeDO::getDataMd5, dataMd5)
        );
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprTrademarkPledgeDO>lambdaUpdate().eq(DataCompanyIprTrademarkPledgeDO::getId, id)
                .set(DataCompanyIprTrademarkPledgeDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
