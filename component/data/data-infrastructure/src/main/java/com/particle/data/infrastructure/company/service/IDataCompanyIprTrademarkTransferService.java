package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权商标转让信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
public interface IDataCompanyIprTrademarkTransferService extends IBaseService<DataCompanyIprTrademarkTransferDO> {

    /**
     * 根据企业知识产权商标表id查询多个
     * @param companyIprTrademarkIds
     * @return
     */
    default List<DataCompanyIprTrademarkTransferDO> listByCompanyIprTrademarkIds(List<Long> companyIprTrademarkIds) {
        Assert.notEmpty(companyIprTrademarkIds,"companyIprTrademarkIds 不能为空");
        return list(Wrappers.<DataCompanyIprTrademarkTransferDO>lambdaQuery().in(DataCompanyIprTrademarkTransferDO::getCompanyIprTrademarkId, companyIprTrademarkIds));
    }
    /**
     * 根据商标ID分页查询列表
     * @param companyIprTrademarkId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprTrademarkTransferDO> listPageByCompanyIprTrademarkId(Long companyIprTrademarkId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprTrademarkId,"companyIprTrademarkId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprTrademarkTransferDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkTransferDO::getCompanyIprTrademarkId, companyIprTrademarkId)
        );
    }
    /**
     * 根据 商标id 和 dataMd5 查询
     * @param companyIprTrademarkId
     * @param dataMd5
     * @return
     */
    default public DataCompanyIprTrademarkTransferDO getByCompanyIprTrademarkIdAndDataMd5(Long companyIprTrademarkId, String dataMd5) {
        Assert.notNull(companyIprTrademarkId,"companyIprTrademarkId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyIprTrademarkTransferDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkTransferDO::getCompanyIprTrademarkId, companyIprTrademarkId)
                .eq(DataCompanyIprTrademarkTransferDO::getDataMd5, dataMd5)
        );
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprTrademarkTransferDO>lambdaUpdate().eq(DataCompanyIprTrademarkTransferDO::getId, id)
                .set(DataCompanyIprTrademarkTransferDO::getLatestHandleAt, LocalDateTime.now()));
    }



}
