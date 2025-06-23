package com.particle.data.infrastructure.company.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyDeliveryAnnouncementListPageByCompanyIdParam;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业送达公告 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Mapper
public interface DataCompanyDeliveryAnnouncementMapper extends IBaseMapper<DataCompanyDeliveryAnnouncementDO> {
    /**
     * 分页查询送达公告
     * @param page
     * @param param
     * @return
     */
    public Page<DataCompanyDeliveryAnnouncementDO> listPage(Page page, @Param("param") DataCompanyDeliveryAnnouncementListPageByCompanyIdParam param);

}
