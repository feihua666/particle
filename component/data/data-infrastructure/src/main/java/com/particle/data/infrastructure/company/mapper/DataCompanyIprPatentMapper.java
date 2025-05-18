package com.particle.data.infrastructure.company.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import com.particle.data.infrastructure.company.dto.DataCompanyIprPatentListPageByCompanyIdParam;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业知识产权专利 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Mapper
public interface DataCompanyIprPatentMapper extends IBaseMapper<DataCompanyIprPatentDO> {
    /**
     * 分页查询专利信息
     * @param page
     * @param param
     * @return
     */
    public Page<DataCompanyIprPatentDO> listPage(Page page, @Param("param") DataCompanyIprPatentListPageByCompanyIdParam param);

}
