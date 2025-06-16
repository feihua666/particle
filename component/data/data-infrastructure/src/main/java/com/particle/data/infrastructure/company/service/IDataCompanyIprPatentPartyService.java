package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权专利当事人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
public interface IDataCompanyIprPatentPartyService extends IBaseService<DataCompanyIprPatentPartyDO> {

    /**
     * 根据专利ID查询列表
     * @param companyIprPatentIds
     * @return
     */
    default public List<DataCompanyIprPatentPartyDO> listByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
        Assert.notEmpty(companyIprPatentIds,"companyIprPatentIds 不能为空");
        return list(Wrappers.<DataCompanyIprPatentPartyDO>lambdaQuery()
                .in(DataCompanyIprPatentPartyDO::getCompanyIprPatentId, companyIprPatentIds)
        );
    }

    /**
     * 根据专利ID分页查询列表
     * @param companyIprPatentId
     * @param companyId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprPatentPartyDO> listPageByCompanyIprPatentId(Long companyIprPatentId, Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprPatentPartyDO>lambdaQuery()
                .eq(DataCompanyIprPatentPartyDO::getCompanyIprPatentId, companyIprPatentId)
                .eq(companyId != null,DataCompanyIprPatentPartyDO::getPartyCompanyId, companyId)
        );
    }

    /**
     * 根据 专利id 和 dataMd5 查询
     * @param companyIprPatentId
     * @param dataMd5
     * @return
     */
    default public DataCompanyIprPatentPartyDO getByCompanyIprPatentIdAndDataMd5(Long companyIprPatentId,String dataMd5) {
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentPartyDO>lambdaQuery()
                .eq(DataCompanyIprPatentPartyDO::getCompanyIprPatentId, companyIprPatentId)
                .eq(DataCompanyIprPatentPartyDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPatentPartyDO>lambdaUpdate().eq(DataCompanyIprPatentPartyDO::getId, id)
                .set(DataCompanyIprPatentPartyDO::getLatestHandleAt, LocalDateTime.now()));
    }
}
