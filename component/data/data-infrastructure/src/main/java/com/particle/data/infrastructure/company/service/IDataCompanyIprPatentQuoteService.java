package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权专利引证信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
public interface IDataCompanyIprPatentQuoteService extends IBaseService<DataCompanyIprPatentQuoteDO> {

    /**
     * 根据企业知识产权专利表id查询多个
     * @param companyIprPatentIds
     * @return
     */
    default List<DataCompanyIprPatentQuoteDO> listByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
        Assert.notEmpty(companyIprPatentIds,"companyIprPatentIds 不能为空");
        return list(Wrappers.<DataCompanyIprPatentQuoteDO>lambdaQuery().in(DataCompanyIprPatentQuoteDO::getCompanyIprPatentId, companyIprPatentIds));
    }
    /**
     * 根据专利ID分页查询列表
     * @param companyIprPatentId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprPatentQuoteDO> listPageByCompanyIprPatentId(Long companyIprPatentId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprPatentQuoteDO>lambdaQuery()
                .eq(DataCompanyIprPatentQuoteDO::getCompanyIprPatentId, companyIprPatentId)
        );
    }

    /**
     * 根据 专利id 和 原始申请号 查询
     * @param companyIprPatentId
     * @param applyNo
     * @return
     */
    default public DataCompanyIprPatentQuoteDO getByCompanyIprPatentIdAndApplyNo(Long companyIprPatentId,String applyNo) {
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Assert.notEmpty(applyNo,"applyNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentQuoteDO>lambdaQuery()
                .eq(DataCompanyIprPatentQuoteDO::getCompanyIprPatentId, companyIprPatentId)
                .eq(DataCompanyIprPatentQuoteDO::getApplyNo, applyNo)
        );
    }
    /**
     * 根据 专利id 和 原始公布号 查询
     * @param companyIprPatentId
     * @param publicNo
     * @return
     */
    default public DataCompanyIprPatentQuoteDO getByCompanyIprPatentIdAndPublicNo(Long companyIprPatentId,String publicNo) {
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Assert.notEmpty(publicNo,"publicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentQuoteDO>lambdaQuery()
                .eq(DataCompanyIprPatentQuoteDO::getCompanyIprPatentId, companyIprPatentId)
                .eq(DataCompanyIprPatentQuoteDO::getPublicNo, publicNo)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPatentQuoteDO>lambdaUpdate().eq(DataCompanyIprPatentQuoteDO::getId, id)
                .set(DataCompanyIprPatentQuoteDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
