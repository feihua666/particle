package com.particle.scheduler.infrastructure.job.quartzjob;

import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.script.GroovyTool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.script.Bindings;

/**
 * 一个基本脚本的任务
 * Created by yangwei
 * Created at 2024-09-09 15:33:14
 */
@Slf4j
public class ScriptInvokerQuartzJob extends AbstractQuartzJob {

    public enum ScriptInvokerQuartzJobDataMapKeys {
        scriptType, scriptContent
    }
    public enum ScriptType {
        groovy
    }

    @SneakyThrows
    @Override
    public String doExecute(JobExecutionContext context) throws JobExecutionException {
        log.info("任务执行 mergedJobDataMap={}",context.getMergedJobDataMap().toString());
        String scriptType = (String) context.getMergedJobDataMap().get(ScriptInvokerQuartzJobDataMapKeys.scriptType.name());
        String scriptContent = (String) context.getMergedJobDataMap().get(ScriptInvokerQuartzJobDataMapKeys.scriptContent.name());

        if (ScriptType.groovy.name().equals(scriptType)) {
            Bindings bindings = GroovyTool.createBindings();
            Object result = GroovyTool.compileAndEval(scriptContent, bindings, true);
            try {
                result = JsonTool.toJsonStr(result);
                log.info("任务执行结果 result={}",result);
            } catch (Exception e) {
                log.info("任务执行结果 result={}",result);
            }
        }else {
            throw new RuntimeException("不支持的脚本类型");
        }
        return null;
    }
}
