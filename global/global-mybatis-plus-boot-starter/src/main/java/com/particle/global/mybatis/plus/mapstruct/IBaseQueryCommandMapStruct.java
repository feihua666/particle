package com.particle.global.mybatis.plus.mapstruct;

import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;

/**
 * 查询对象转DO基础类
 * Created by yangwei
 * Created at 2020/10/27 14:43
 */
public interface IBaseQueryCommandMapStruct<DO,Query extends QueryCommand,PageQuery extends PageQueryCommand> {

    /**
     * 查询表单转 do
     * @param queryForm
     * @return
     */
    DO queryFormToPo(Query queryForm);

    /**
     * 分布查询静音转 do
     * @param pageQueryForm
     * @return
     */
    DO pageQueryFormToPo(PageQuery pageQueryForm);

}
