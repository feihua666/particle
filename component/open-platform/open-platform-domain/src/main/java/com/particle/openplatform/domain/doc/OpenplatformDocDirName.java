package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口目录名称 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Data
@Entity
public class OpenplatformDocDirName extends AggreateRoot {

	/**
	 * 编码，唯一
	 */
	private String code;

    private OpenplatformDocDirNameId id;

    /**
    * 名称
    */
    private String name;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建开放接口目录名称领域模型对象
     * @return 开放接口目录名称领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocDirName create(){
        return DomainFactory.create(OpenplatformDocDirName.class);
    }
}