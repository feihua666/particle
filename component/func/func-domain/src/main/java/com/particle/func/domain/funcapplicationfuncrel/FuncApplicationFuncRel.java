package com.particle.func.domain.funcapplicationfuncrel;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 功能应用功能关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Data
@Entity
public class FuncApplicationFuncRel extends AggreateRoot {

    private FuncApplicationFuncRelId id;

    /**
    * 功能应用id
    */
    private Long funcApplicationId;

    /**
    * 功能id
    */
    private Long funcId;



    /**
     * 创建功能应用功能关系领域模型对象
     * @return 功能应用功能关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static FuncApplicationFuncRel create(){
        return DomainFactory.create(FuncApplicationFuncRel.class);
    }
}
