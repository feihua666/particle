package com.particle.audit.domain.auditrecord;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 审核记录数据快照 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Data
@Entity
public class AuditRecordSnapshotData extends AggreateRoot {

    private AuditRecordSnapshotDataId id;

    /**
    * 审核记录id
    */
    private Long auditRecordId;

    /**
    * 数据id，标识是哪个数据快照
    */
    private Long dataId;

	/**
	 * 快照数据id，标识是哪个快照数据的id
	 */
	private Long dataSnapshotDataId;

    /**
    * 快照内容json
    */
    private String snapshotJson;



    /**
     * 创建审核记录数据快照领域模型对象
     * @return 审核记录数据快照领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AuditRecordSnapshotData create(){
        return DomainFactory.create(AuditRecordSnapshotData.class);
    }
}
