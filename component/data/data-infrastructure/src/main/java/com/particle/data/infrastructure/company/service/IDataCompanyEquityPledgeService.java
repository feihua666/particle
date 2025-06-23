package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业股权出质 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
public interface IDataCompanyEquityPledgeService extends IBaseService<DataCompanyEquityPledgeDO> {


    /**
     * 根据 regNo 查询
     * @param punishContentMd5
     * @return
     */
    default public DataCompanyEquityPledgeDO getByCompanyIdAndRegNo(Long companyId, String punishContentMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notEmpty(punishContentMd5,"punishContentMd5 不能为空");
        return getOne(Wrappers.<DataCompanyEquityPledgeDO>lambdaQuery().eq(DataCompanyEquityPledgeDO::getCompanyId, companyId).eq(DataCompanyEquityPledgeDO::getRegNo, punishContentMd5));
    }
    /**
     * 根据企业表ID查询列表
     * @param companyId
     * @return
     */
    default public List<DataCompanyEquityPledgeDO> listByCompanyId(Long companyId){
        Assert.notNull(companyId,"companyId 不能为空");
        return list(Wrappers.<DataCompanyEquityPledgeDO>lambdaQuery().eq(DataCompanyEquityPledgeDO::getCompanyId, companyId));
    }

    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyEquityPledgeDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery,Wrappers.<DataCompanyEquityPledgeDO>lambdaQuery().eq(DataCompanyEquityPledgeDO::getCompanyId, companyId));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyEquityPledgeDO>lambdaUpdate().eq(DataCompanyEquityPledgeDO::getId, id)
                .set(DataCompanyEquityPledgeDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
