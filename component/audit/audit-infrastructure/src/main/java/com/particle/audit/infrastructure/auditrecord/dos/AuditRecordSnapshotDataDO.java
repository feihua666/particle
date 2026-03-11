package com.particle.audit.infrastructure.auditrecord.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 审核记录数据快照表
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Accessors(chain = true)
@Data
@TableName("component_audit_record_snapshot_data")
public class AuditRecordSnapshotDataDO extends BaseDO {

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


}
