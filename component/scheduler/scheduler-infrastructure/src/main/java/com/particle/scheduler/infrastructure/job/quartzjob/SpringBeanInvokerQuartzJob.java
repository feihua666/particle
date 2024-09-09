package com.particle.scheduler.infrastructure.job.quartzjob;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 一个spring容器bean的调用任务
 * Created by yangwei
 * Created at 2021/2/4 10:59
 */
@Slf4j
public class SpringBeanInvokerQuartzJob extends AbstractQuartzJob {

    public enum SpringBeanInvokerQuartzJobDataMapKeys {
        beanName, beanMethodName, beanMethodParams
    }

    @Override
    public String doExecute(JobExecutionContext context) throws JobExecutionException {
        log.info("任务执行 mergedJobDataMap={}",context.getMergedJobDataMap().toString());
        String beanName = (String) context.getMergedJobDataMap().get(SpringBeanInvokerQuartzJobDataMapKeys.beanName.name());
        String beanMethodName = (String) context.getMergedJobDataMap().get(SpringBeanInvokerQuartzJobDataMapKeys.beanMethodName.name());
        List<Map> beanMethodParams = (List<Map>) context.getMergedJobDataMap().get(SpringBeanInvokerQuartzJobDataMapKeys.beanMethodParams.name());
        Object bean = SpringContextHolder.getBean(beanName);
        if (bean == null) {
            throw new RuntimeException(StrUtil.format("没有找到对应的bean. beanName={}",beanName));
        }


        // 参数
        List<Object> params = new ArrayList<>();
        Method methodByName = ReflectUtil.getMethodByName(bean.getClass(), beanMethodName);
        Class<?>[] parameterTypes = methodByName.getParameterTypes();
        if (parameterTypes != null && parameterTypes.length != 0) {
            if (CollectionUtil.isEmpty(beanMethodParams)) {
                throw new RuntimeException(StrUtil.format("方法需要参数. beanName={},beanMethodName={}",beanName,beanMethodName));
            }
            if (parameterTypes.length != beanMethodParams.size()) {
                throw new RuntimeException(StrUtil.format("方法参数与给定的参数个数不符. beanName={},beanMethodName={},方法参数个数={},传入参数个数={}",beanName,beanMethodName,parameterTypes.length,beanMethodParams.size()));
            }
            for (int i = 0; i < parameterTypes.length; i++) {
                try {
                    params.add(Convert.convert(parameterTypes[i],beanMethodParams.get(i)));
                } catch (Exception e) {
                    // 如果转换异常，直接使用传入的
                    params.add(beanMethodParams.get(i));

                }
            }
        }
        Object invoke = ReflectUtil.invoke(bean, beanMethodName, params.toArray());
        String result = "";
        if (invoke != null) {
            try {
                result = JsonTool.toJsonStr(invoke);
                log.info("任务执行结果 result={}",result);
            } catch (Exception e) {
                log.info("任务执行结果 result={}",invoke);
            }
        }
        return result;
    }
}
