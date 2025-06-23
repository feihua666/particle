package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权集成电路 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
public interface IDataCompanyIprIntegratedCircuitService extends IBaseService<DataCompanyIprIntegratedCircuitDO> {

    /**
     * 根据公告号查询
     * @param publicNo
     * @return
     */
    default DataCompanyIprIntegratedCircuitDO getByPublicNo(String publicNo) {
        Assert.notNull(publicNo,"publicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprIntegratedCircuitDO>lambdaQuery().eq(DataCompanyIprIntegratedCircuitDO::getPublicNo, publicNo));
    }



    /**
     * 根据公告号查询多个
     * @param publicNos
     * @return
     */
    default List<DataCompanyIprIntegratedCircuitDO> getByPublicNos(List<String> publicNos) {
        Assert.notEmpty(publicNos,"publicNos 不能为空");
        return list(Wrappers.<DataCompanyIprIntegratedCircuitDO>lambdaQuery().in(DataCompanyIprIntegratedCircuitDO::getPublicNo, publicNos));
    }


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param publicNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyIprIntegratedCircuitDO> listPageByRightHolderCompanyId(Long companyId, String publicNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprIntegratedCircuitDO>lambdaQuery()
                .eq(DataCompanyIprIntegratedCircuitDO::getRightHolderCompanyId, companyId)
                .eq(publicNo != null,DataCompanyIprIntegratedCircuitDO::getPublicNo, publicNo)
        );
    }

    /**
     * 根据 公司id 和 publicNo 查询
     * @param executedPersonCompanyId
     * @param publicNo
     * @return
     */
    default public DataCompanyIprIntegratedCircuitDO getByRightHolderCompanyIdAndPublicNo(Long executedPersonCompanyId,String publicNo) {
        Assert.notNull(executedPersonCompanyId,"executedPersonCompanyId 不能为空");
        Assert.notEmpty(publicNo,"publicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprIntegratedCircuitDO>lambdaQuery()
                .eq(DataCompanyIprIntegratedCircuitDO::getRightHolderCompanyId, executedPersonCompanyId)
                .eq(DataCompanyIprIntegratedCircuitDO::getPublicNo, publicNo)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprIntegratedCircuitDO>lambdaUpdate().eq(DataCompanyIprIntegratedCircuitDO::getId, id)
                .set(DataCompanyIprIntegratedCircuitDO::getLatestHandleAt, LocalDateTime.now()));
    }




}
