package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权商标转让人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
public interface IDataCompanyIprTrademarkTransferPersonService extends IBaseService<DataCompanyIprTrademarkTransferPersonDO> {


    /**
     * 根据企业知识产权商标转让表id查询多个
     * @param companyIprTrademarkTransferIds
     * @return
     */
    default List<DataCompanyIprTrademarkTransferPersonDO> listByCompanyIprTrademarkTransferIds(List<Long> companyIprTrademarkTransferIds) {
        Assert.notEmpty(companyIprTrademarkTransferIds,"companyIprTrademarkTransferIds 不能为空");
        return list(Wrappers.<DataCompanyIprTrademarkTransferPersonDO>lambdaQuery().in(DataCompanyIprTrademarkTransferPersonDO::getCompanyIprTrademarkTransferId, companyIprTrademarkTransferIds));
    }
    /**
     * 根据商标转让ID分页查询列表
     * @param companyIprTrademarkTransferId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprTrademarkTransferPersonDO> listPageByCompanyIprTrademarkTransferId(Long companyIprTrademarkTransferId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprTrademarkTransferId,"companyIprTrademarkTransferId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprTrademarkTransferPersonDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkTransferPersonDO::getCompanyIprTrademarkTransferId, companyIprTrademarkTransferId)
        );
    }
    /**
     * 根据 商标转让id 和 dataMd5 查询
     * @param companyIprTrademarkTransferId
     * @param dataMd5
     * @return
     */
    default public DataCompanyIprTrademarkTransferPersonDO getByCompanyIprTrademarkTransferIdAndDataMd5(Long companyIprTrademarkTransferId, String dataMd5) {
        Assert.notNull(companyIprTrademarkTransferId,"companyIprTrademarkTransferId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyIprTrademarkTransferPersonDO>lambdaQuery()
                .eq(DataCompanyIprTrademarkTransferPersonDO::getCompanyIprTrademarkTransferId, companyIprTrademarkTransferId)
                .eq(DataCompanyIprTrademarkTransferPersonDO::getDataMd5, dataMd5)
        );
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprTrademarkTransferPersonDO>lambdaUpdate().eq(DataCompanyIprTrademarkTransferPersonDO::getId, id)
                .set(DataCompanyIprTrademarkTransferPersonDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
