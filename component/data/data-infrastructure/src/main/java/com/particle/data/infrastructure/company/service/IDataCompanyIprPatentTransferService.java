package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权专利转让信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
public interface IDataCompanyIprPatentTransferService extends IBaseService<DataCompanyIprPatentTransferDO> {


    /**
     * 根据企业知识产权专利表id查询多个
     * @param companyIprPatentIds
     * @return
     */
    default List<DataCompanyIprPatentTransferDO> listByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
        Assert.notEmpty(companyIprPatentIds,"companyIprPatentIds 不能为空");
        return list(Wrappers.<DataCompanyIprPatentTransferDO>lambdaQuery().in(DataCompanyIprPatentTransferDO::getCompanyIprPatentId, companyIprPatentIds));
    }
    /**
     * 根据专利ID分页查询列表
     * @param companyIprPatentId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprPatentTransferDO> listPageByCompanyIprPatentId(Long companyIprPatentId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprPatentTransferDO>lambdaQuery()
                .eq(DataCompanyIprPatentTransferDO::getCompanyIprPatentId, companyIprPatentId)
        );
    }

    /**
     * 根据 专利id 和 dataMd5 查询
     * @param companyIprPatentId
     * @param dataMd5
     * @return
     */
    default public DataCompanyIprPatentTransferDO getByCompanyIprPatentIdAndDataMd5(Long companyIprPatentId, String dataMd5) {
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentTransferDO>lambdaQuery()
                .eq(DataCompanyIprPatentTransferDO::getCompanyIprPatentId, companyIprPatentId)
                .eq(DataCompanyIprPatentTransferDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPatentTransferDO>lambdaUpdate().eq(DataCompanyIprPatentTransferDO::getId, id)
                .set(DataCompanyIprPatentTransferDO::getLatestHandleAt, LocalDateTime.now()));
    }



}
