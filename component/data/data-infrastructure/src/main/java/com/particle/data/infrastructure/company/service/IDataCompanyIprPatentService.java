package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import com.particle.data.infrastructure.company.dto.DataCompanyIprPatentListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权专利 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
public interface IDataCompanyIprPatentService extends IBaseService<DataCompanyIprPatentDO> {


    /**
     * 根据原始申请号查询
     * @param applyNo
     * @return
     */
    default DataCompanyIprPatentDO getByApplyNo(String applyNo) {
        Assert.notNull(applyNo,"applyNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentDO>lambdaQuery().eq(DataCompanyIprPatentDO::getApplyNo, applyNo));
    }



    /**
     * 根据原始申请号查询多个
     * @param applyNos
     * @return
     */
    default List<DataCompanyIprPatentDO> getByApplyNos(List<String> applyNos) {
        Assert.notEmpty(applyNos,"applyNos 不能为空");
        return list(Wrappers.<DataCompanyIprPatentDO>lambdaQuery().in(DataCompanyIprPatentDO::getApplyNo, applyNos));
    }

    /**
     * 根据公布号查询
     * @param publicNo
     * @return
     */
    default DataCompanyIprPatentDO getByPublicNo(String publicNo) {
        Assert.notNull(publicNo,"publicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentDO>lambdaQuery().eq(DataCompanyIprPatentDO::getPublicNo, publicNo));
    }



    /**
     * 根据公布号查询多个
     * @param publicNos
     * @return
     */
    default List<DataCompanyIprPatentDO> getByPublicNos(List<String> publicNos) {
        Assert.notEmpty(publicNos,"publicNos 不能为空");
        return list(Wrappers.<DataCompanyIprPatentDO>lambdaQuery().in(DataCompanyIprPatentDO::getPublicNo, publicNos));
    }

    /**
     * 分页查询专利信息
     * @param param
     * @param pageQueryForm
     * @return
     */
    public Page<DataCompanyIprPatentDO> listPage(DataCompanyIprPatentListPageByCompanyIdParam param, PageQueryCommand pageQueryForm);
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param applyNo
     * @param applyNo
     * @param publicNo
     * @return
     */
    default public Page<DataCompanyIprPatentDO> listPageByCompanyId(Long companyId, String applyNo,String publicNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        return listPage(DataCompanyIprPatentListPageByCompanyIdParam.create(companyId,
                applyNo, publicNo), pageQueryForm);
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPatentDO>lambdaUpdate().eq(DataCompanyIprPatentDO::getId, id)
                .set(DataCompanyIprPatentDO::getLatestHandleAt, LocalDateTime.now()));
    }
}
