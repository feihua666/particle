package com.particle.dataquery.domain.provider;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 数据查询供应商 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Data
@Entity
public class DataQueryProvider extends AggreateRoot {

    private DataQueryProviderId id;

    /**
    * 供应商名称
    */
    private String name;

    /**
    * 是否禁用
    */
    private Boolean isDisabled;
    /**
     * 禁用原因
     */
    private String disabledReason;
    /**
    * 联系人姓名
    */
    private String contactUserName;

    /**
    * 联系人邮箱
    */
    private String contactUserEmail;

    /**
    * 联系人电话
    */
    private String contactUserPhone;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建数据查询供应商领域模型对象
     * @return 数据查询供应商领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataQueryProvider create(){
        return DomainFactory.create(DataQueryProvider.class);
    }
}
