package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权专利质押信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
public interface IDataCompanyIprPatentPledgeService extends IBaseService<DataCompanyIprPatentPledgeDO> {


    /**
     * 根据企业知识产权专利表id查询多个
     * @param companyIprPatentIds
     * @return
     */
    default List<DataCompanyIprPatentPledgeDO> listByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
        Assert.notEmpty(companyIprPatentIds,"companyIprPatentIds 不能为空");
        return list(Wrappers.<DataCompanyIprPatentPledgeDO>lambdaQuery().in(DataCompanyIprPatentPledgeDO::getCompanyIprPatentId, companyIprPatentIds));
    }
    /**
     * 根据专利ID分页查询列表
     * @param companyIprPatentId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprPatentPledgeDO> listPageByCompanyIprPatentId(Long companyIprPatentId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprPatentPledgeDO>lambdaQuery()
                .eq(DataCompanyIprPatentPledgeDO::getCompanyIprPatentId, companyIprPatentId)
        );
    }

    /**
     * 根据 专利id 和 pledgeNo 查询
     * @param companyIprPatentId
     * @param pledgeNo
     * @return
     */
    default public DataCompanyIprPatentPledgeDO getByCompanyIprPatentIdAndPledgeNo(Long companyIprPatentId,String pledgeNo) {
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Assert.notEmpty(pledgeNo,"pledgeNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentPledgeDO>lambdaQuery()
                .eq(DataCompanyIprPatentPledgeDO::getCompanyIprPatentId, companyIprPatentId)
                .eq(DataCompanyIprPatentPledgeDO::getPledgeNo, pledgeNo)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPatentPledgeDO>lambdaUpdate().eq(DataCompanyIprPatentPledgeDO::getId, id)
                .set(DataCompanyIprPatentPledgeDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
