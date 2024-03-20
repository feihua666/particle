package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口文档接口与目录关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Data
@Entity
public class OpenplatformDocApiDirRel extends AggreateRoot {

    private OpenplatformDocApiDirRelId id;

    /**
    * 开放接口文档接口id
    */
    private Long openplatformDocApiId;

    /**
    * 开放接口文档目录id
    */
    private Long openplatformDocDirId;



    /**
     * 创建开放接口文档接口与目录关系领域模型对象
     * @return 开放接口文档接口与目录关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocApiDirRel create(){
        return DomainFactory.create(OpenplatformDocApiDirRel.class);
    }

    public static OpenplatformDocApiDirRel create(Long openplatformDocApiId,Long openplatformDocDirId){
        OpenplatformDocApiDirRel rel = create();
        rel.openplatformDocApiId = openplatformDocApiId;
        rel.openplatformDocDirId = openplatformDocDirId;
        return rel;
    }
}
