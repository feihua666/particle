package com.particle.global.mybatis.plus.mapstruct;

import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;

/**
 * 查询对象转DO基础类
 * @author yangwei
 * @since 2020/10/27 14:43
 */
public interface IBaseQueryCommandMapStruct<DO> {

    /**
     * 查询表单转 do
     * @param queryCommand
     * @return
     */
    DO queryCommandToDO(QueryCommand queryCommand);

}
