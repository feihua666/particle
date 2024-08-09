package com.particle.oplog.infrastructure.error.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 操作异常日志内容表
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Accessors(chain = true)
@Data
@TableName("component_op_log_error_content")
public class OpLogErrorContentDO extends BaseDO {

    /**
    * 异常日志id
    */
    private Long opLogErrorId;

    /**
    * 异常内容
    */
    private String content;


}
