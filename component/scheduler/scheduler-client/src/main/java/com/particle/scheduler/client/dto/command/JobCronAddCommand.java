package com.particle.scheduler.client.dto.command;

import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by yangwei
 * Created at 2021/2/2 13:25
 */
@PropValid
@Setter
@Getter
@Schema(description="添加任务表单对象")
public class JobCronAddCommand extends NameAndGroupCommand {

    /**
     * 需要和 {@link com.particle.scheduler.domain.value.JobCronAddParam} 定义的一致
     */
    public final static String JOB_CLASS_TYPE_CUSTOM = "custom_class";
    public final static String JOB_CLASS_TYPE_HTTP = "http_job";
    public final static String JOB_CLASS_TYPE_BEAN = "spring_bean";
    public final static String JOB_CLASS_TYPE_SCRIPT = "script_job";


    @NotEmpty(message = "任务计划名称不能为空")
    @Schema(description = "任务计划名称")
    private String schedulerName;

    @NotEmpty(message = "任务计划实例id不能为空")
    @Schema(description = "任务计划实例id")
    private String schedulerInstanceId;

    @NotEmpty(message = "cron表达式不能为空")
    @Schema(description = "cron表达式",required = true)
    private String cronExpression;

    @NotEmpty(message = "任务类型不能为空")
    @Schema(description = "任务类型",required = true)
    private String jobClassType;

    @PropValid.DependCondition(message = "任务类全名不能为空",dependProp = "jobClassType",ifEqual = JOB_CLASS_TYPE_CUSTOM)
    @Schema(description = "任务类全名")
    private String jobClassName;

    @PropValid.DependCondition(message = "请求地址不能为空",dependProp = "jobClassType",ifEqual = JOB_CLASS_TYPE_HTTP)
    @Schema(description = "请求地址")
    private String httpUrl;

    @PropValid.DependCondition(message = "请求方法不能为空",dependProp = "jobClassType",ifEqual = JOB_CLASS_TYPE_HTTP)
    @Schema(description = "请求方法")
    private String httpMethod;

    @Schema(description = "请求头信息")
    private Map<String,String> httpHeaders;

    @Schema(description = "请求参数")
    private Map<String,Object> httpParams;

    @PropValid.DependCondition(message = "bean名称不能为空",dependProp = "jobClassType",ifEqual = JOB_CLASS_TYPE_BEAN)
    @Schema(description = "bean名称")
    private String beanName;

    @PropValid.DependCondition(message = "bean方法名不能为空",dependProp = "jobClassType",ifEqual = JOB_CLASS_TYPE_BEAN)
    @Schema(description = "bean方法名")
    private String beanMethodName;

    @Schema(description = "bean方法参数")
    private List<Object> beanMethodParams;

    @PropValid.DependCondition(message = "脚本类型不能为空",dependProp = "jobClassType",ifEqual = JOB_CLASS_TYPE_SCRIPT)
    @Schema(description = "脚本类型")
    private String scriptType;

    @PropValid.DependCondition(message = "脚本内容不能为空",dependProp = "jobClassType",ifEqual = JOB_CLASS_TYPE_SCRIPT)
    @Schema(description = "脚本内容")
    private String scriptContent;

    @NotEmpty(message = "描述信息不能为空")
    @Schema(description = "描述信息",required = true)
    private String description;

    @Schema(description = "额外数据")
    private Map<String, Object> dataMap;
}
