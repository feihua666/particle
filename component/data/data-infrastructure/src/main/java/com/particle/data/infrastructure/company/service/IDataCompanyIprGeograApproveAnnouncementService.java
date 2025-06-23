package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权地理标识核准公告 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
public interface IDataCompanyIprGeograApproveAnnouncementService extends IBaseService<DataCompanyIprGeograApproveAnnouncementDO> {


    /**
     * 根据企业知识产权地址标识表id查询多个
     * @param companyIprGeograIds
     * @return
     */
    default List<DataCompanyIprGeograApproveAnnouncementDO> listByCompanyIprGeograIds(List<Long> companyIprGeograIds) {
        Assert.notEmpty(companyIprGeograIds,"companyIprGeograIds 不能为空");
        return list(Wrappers.<DataCompanyIprGeograApproveAnnouncementDO>lambdaQuery().in(DataCompanyIprGeograApproveAnnouncementDO::getCompanyIprGeograId, companyIprGeograIds));
    }
    /**
     * 根据地址标识ID分页查询列表
     * @param companyIprGeograId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprGeograApproveAnnouncementDO> listPageByCompanyIprGeograId(Long companyIprGeograId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprGeograId,"companyIprGeograId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprGeograApproveAnnouncementDO>lambdaQuery()
                .eq(DataCompanyIprGeograApproveAnnouncementDO::getCompanyIprGeograId, companyIprGeograId)
        );
    }
    /**
     * 根据 地址标识id 和 approvePublicNo 查询
     * @param companyIprGeograId
     * @param approvePublicNo
     * @return
     */
    default public DataCompanyIprGeograApproveAnnouncementDO getByCompanyIprGeograIdAndApprovePublicNo(Long companyIprGeograId, String approvePublicNo) {
        Assert.notNull(companyIprGeograId,"companyIprGeograId 不能为空");
        Assert.notEmpty(approvePublicNo,"approvePublicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprGeograApproveAnnouncementDO>lambdaQuery()
                .eq(DataCompanyIprGeograApproveAnnouncementDO::getCompanyIprGeograId, companyIprGeograId)
                .eq(DataCompanyIprGeograApproveAnnouncementDO::getApprovePublicNo, approvePublicNo)
        );
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprGeograApproveAnnouncementDO>lambdaUpdate().eq(DataCompanyIprGeograApproveAnnouncementDO::getId, id)
                .set(DataCompanyIprGeograApproveAnnouncementDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
