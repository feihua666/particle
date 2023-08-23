package com.particle.openplatform.domain.provider;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口供应商 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Data
@Entity
public class OpenplatformProvider extends AggreateRoot {

    private OpenplatformProviderId id;

    /**
    * 编码，唯一
    */
    private String code;

    /**
    * 供应商名称
    */
    private String name;

    /**
    * 数据查询供应商id，兼容一下数据查询数据进行统一供应商
    */
    private Long dataQueryProviderId;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建开放平台开放接口供应商领域模型对象
     * @return 开放平台开放接口供应商领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformProvider create(){
        return DomainFactory.create(OpenplatformProvider.class);
    }
}
