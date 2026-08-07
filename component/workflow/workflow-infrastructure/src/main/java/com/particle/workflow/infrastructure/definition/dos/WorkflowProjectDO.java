package com.particle.workflow.infrastructure.definition.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 工作流项目表
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Accessors(chain = true)
@Data
@TableName("component_workflow_project")
public class WorkflowProjectDO extends BaseDO {

    /**
    * 项目名称
    */
    private String name;

    /**
    * 配置参数json
    */
    private String configJson;

    /**
    * 封面图地址
    */
    private String coverImageUrl;

    /**
    * 归属用户id
    */
    private Long userId;

    /**
    * 是否公开，1=是，所有人可见，0=否，用户自己可见
    */
    private Boolean isPublic;

    /**
    * 描述
    */
    private String remark;


}
