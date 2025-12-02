package com.particle.data.domain.dynamicdata;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 动态数据指标分类上传记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Data
@Entity
public class DynamicDataIndicatorCategoryUploadRecord extends AggreateRoot {

    private DynamicDataIndicatorCategoryUploadRecordId id;

    /**
    * 动态数据指标分类id
    */
    private Long dynamicDataIndicatorCategoryId;

    /**
    * 上传文件名称
    */
    private String uploadFileName;

    /**
    * 上传文件地址
    */
    private String uploadFileUrl;

    /**
    * 上传指标数量
    */
    private Integer uploadIndicatorNum;

    /**
    * 上传数据数量
    */
    private Integer uploadIndicatorDataNum;

	/**
	 * 是否发布，1=是，0=否
	 */
	private Boolean isPublic;

    public void publish(){
    	this.isPublic = true;
    }
    public void unPublish(){
        this.isPublic = false;
    }


    /**
     * 创建动态数据指标分类上传记录领域模型对象
     * @return 动态数据指标分类上传记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DynamicDataIndicatorCategoryUploadRecord create(){
        return DomainFactory.create(DynamicDataIndicatorCategoryUploadRecord.class);
    }
}
