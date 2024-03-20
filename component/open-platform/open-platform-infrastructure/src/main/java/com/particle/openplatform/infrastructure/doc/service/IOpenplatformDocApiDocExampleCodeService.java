package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocExampleCodeDO;

import java.util.List;

/**
 * <p>
 * 开放接口文档示例代码 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
public interface IOpenplatformDocApiDocExampleCodeService extends IBaseService<OpenplatformDocApiDocExampleCodeDO> {

    /**
     * 根据文档内容id查询
     * @param openplatformDocApiDocId
     * @return
     */
    default List<OpenplatformDocApiDocExampleCodeDO> getByOpenplatformDocApiDocId(Long openplatformDocApiDocId) {
        return list(Wrappers.<OpenplatformDocApiDocExampleCodeDO>lambdaQuery().eq(OpenplatformDocApiDocExampleCodeDO::getOpenplatformDocApiDocId, openplatformDocApiDocId));
    }










}
