package com.particle.data.infrastructure.company.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.data.infrastructure.company.dto.DataCompanyIprTrademarkListPageByCompanyIdParam;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业知识产权商标 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Mapper
public interface DataCompanyIprTrademarkMapper extends IBaseMapper<DataCompanyIprTrademarkDO> {
    /**
     * 分页查询商标信息
     * @param page
     * @param param
     * @return
     */
    public Page<DataCompanyIprTrademarkDO> listPage(Page page, @Param("param") DataCompanyIprTrademarkListPageByCompanyIdParam param);

}
