package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocResponseCodeDO;

import java.util.List;

/**
 * <p>
 * 开放接口文档响应码 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
public interface IOpenplatformDocApiDocResponseCodeService extends IBaseService<OpenplatformDocApiDocResponseCodeDO> {


    /**
     * 根据开放接口文档id查询
     * @param openplatformDocApiDocId
     * @return
     */
    default List<OpenplatformDocApiDocResponseCodeDO> getByOpenplatformDocApiDocId(Long openplatformDocApiDocId){
        return list(Wrappers.<OpenplatformDocApiDocResponseCodeDO>lambdaQuery().eq(OpenplatformDocApiDocResponseCodeDO::getOpenplatformDocApiDocId, openplatformDocApiDocId));
    }












}
