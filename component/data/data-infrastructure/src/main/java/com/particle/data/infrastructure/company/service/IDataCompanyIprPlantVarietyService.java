package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权植物新品种 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
public interface IDataCompanyIprPlantVarietyService extends IBaseService<DataCompanyIprPlantVarietyDO> {

    /**
     * 根据申请号查询
     * @param applyNo
     * @return
     */
    default DataCompanyIprPlantVarietyDO getByApplyNo(String applyNo) {
        Assert.notNull(applyNo,"applyNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprPlantVarietyDO>lambdaQuery().eq(DataCompanyIprPlantVarietyDO::getApplyNo, applyNo));
    }



    /**
     * 根据申请号查询多个
     * @param applyNos
     * @return
     */
    default List<DataCompanyIprPlantVarietyDO> getByApplyNos(List<String> applyNos) {
        Assert.notEmpty(applyNos,"applyNos 不能为空");
        return list(Wrappers.<DataCompanyIprPlantVarietyDO>lambdaQuery().in(DataCompanyIprPlantVarietyDO::getApplyNo, applyNos));
    }



    /**
     * 根据公告号查询
     * @param publicNo
     * @return
     */
    default DataCompanyIprPlantVarietyDO getByPublicNo(String publicNo) {
        Assert.notNull(publicNo,"publicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprPlantVarietyDO>lambdaQuery().eq(DataCompanyIprPlantVarietyDO::getPublicNo, publicNo));
    }



    /**
     * 根据公告号查询多个
     * @param publicNos
     * @return
     */
    default List<DataCompanyIprPlantVarietyDO> getByPublicNos(List<String> publicNos) {
        Assert.notEmpty(publicNos,"publicNos 不能为空");
        return list(Wrappers.<DataCompanyIprPlantVarietyDO>lambdaQuery().in(DataCompanyIprPlantVarietyDO::getPublicNo, publicNos));
    }





    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param publicNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyIprPlantVarietyDO> listPageByApplicantNameCompanyId(Long companyId, String publicNo, String applyNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprPlantVarietyDO>lambdaQuery()
                .eq(DataCompanyIprPlantVarietyDO::getApplicantNameCompanyId, companyId)
                .eq(publicNo != null,DataCompanyIprPlantVarietyDO::getPublicNo, publicNo)
                .eq(applyNo != null,DataCompanyIprPlantVarietyDO::getApplyNo, applyNo)
        );
    }

    /**
     * 根据 公司id 和 publicNo 查询
     * @param executedPersonCompanyId
     * @param publicNo
     * @return
     */
    default public DataCompanyIprPlantVarietyDO getByApplicantNameCompanyIdAndPublicNo(Long executedPersonCompanyId,String publicNo) {
        Assert.notNull(executedPersonCompanyId,"executedPersonCompanyId 不能为空");
        Assert.notEmpty(publicNo,"publicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprPlantVarietyDO>lambdaQuery()
                .eq(DataCompanyIprPlantVarietyDO::getApplicantNameCompanyId, executedPersonCompanyId)
                .eq(DataCompanyIprPlantVarietyDO::getPublicNo, publicNo)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPlantVarietyDO>lambdaUpdate().eq(DataCompanyIprPlantVarietyDO::getId, id)
                .set(DataCompanyIprPlantVarietyDO::getLatestHandleAt, LocalDateTime.now()));
    }




}
