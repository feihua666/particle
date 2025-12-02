package com.particle.data.domain.dynamictable;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 动态数据表格上传记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Data
@Entity
public class DynamicTableUploadRecord extends AggreateRoot {

    private DynamicTableUploadRecordId id;

    /**
    * 动态数据表格id
    */
    private Long dynamicTableId;

    /**
    * 上传文件名称
    */
    private String uploadFileName;

    /**
    * 上传文件地址
    */
    private String uploadFileUrl;

    /**
    * 上传字段数量
    */
    private Integer uploadTableFieldNum;

    /**
    * 上传数据数量
    */
    private Integer uploadTableDataNum;

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
     * 创建动态数据表格上传记录领域模型对象
     * @return 动态数据表格上传记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DynamicTableUploadRecord create(){
        return DomainFactory.create(DynamicTableUploadRecord.class);
    }
}
