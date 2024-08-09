package com.particle.oplog.domain.error;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 操作异常日志内容 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Data
@Entity
public class OpLogErrorContent extends AggreateRoot {

    private OpLogErrorContentId id;

    /**
    * 异常日志id
    */
    private Long opLogErrorId;

    /**
    * 异常内容
    */
    private String content;



    /**
     * 创建操作异常日志内容领域模型对象
     * @return 操作异常日志内容领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpLogErrorContent create(){
        return DomainFactory.create(OpLogErrorContent.class);
    }
}
