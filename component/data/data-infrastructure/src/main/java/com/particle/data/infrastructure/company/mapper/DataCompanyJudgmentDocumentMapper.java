package com.particle.data.infrastructure.company.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import com.particle.data.infrastructure.company.dto.DataCompanyJudgmentDocumentListPageByCompanyIdParam;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业裁判文书 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Mapper
public interface DataCompanyJudgmentDocumentMapper extends IBaseMapper<DataCompanyJudgmentDocumentDO> {
    /**
     * 分页查询裁判文书
     * @param page
     * @param param
     * @return
     */
    public Page<DataCompanyJudgmentDocumentDO> listPage(Page page, @Param("param") DataCompanyJudgmentDocumentListPageByCompanyIdParam param);

}
