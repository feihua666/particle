package com.particle.dataconstraint.domain.gateway;

import com.particle.dataconstraint.domain.DataObject;
import com.particle.dataconstraint.domain.DataObjectId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 数据对象 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
public interface DataObjectGateway extends IBaseGateway<DataObjectId,DataObject> {
}
