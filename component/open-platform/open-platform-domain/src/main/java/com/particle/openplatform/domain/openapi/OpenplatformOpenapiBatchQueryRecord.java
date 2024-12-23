package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 开放接口批量查询记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Data
@Entity
public class OpenplatformOpenapiBatchQueryRecord extends AggreateRoot {

    private OpenplatformOpenapiBatchQueryRecordId id;

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

    /**
    * 开放接口id，这里只存储接口，不存储分组
    */
    private Long openplatformOpenapiId;

    /**
    * 客户id
    */
    private Long customerId;

    /**
    * 执行状态，字典id
    */
    private Long executeStatusDictId;

    /**
    * 成功条数
    */
    private Integer successCount;

    /**
    * 失败条数
    */
    private Integer failCount;

    /**
    * 总条数
    */
    private Integer totalCount;

    /**
    * 用户id,谁操作的
    */
    private Long userId;

    /**
    * 查询时间
    */
    private LocalDateTime queryAt;

    /**
    * 追踪id
    */
    private String traceId;

	/**
	 * 上传文件名
	 */
	private String uploadFileName;

	/**
	 * 导出的文件地址
	 */
	private String exportFileUrl;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建开放接口批量查询记录领域模型对象
     * @return 开放接口批量查询记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformOpenapiBatchQueryRecord create(){
        return DomainFactory.create(OpenplatformOpenapiBatchQueryRecord.class);
    }
}
