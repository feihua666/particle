import{i as t}from"./index-BGwvwH0Y.js";const l=[{field:{name:"schedulerName"},element:{comp:"el-input",formItemProps:{label:"任务计划名称"},compProps:{clearable:!0}}},{field:{name:"schedulerInstanceId"},element:{comp:"el-input",formItemProps:{label:"任务计划实例id"},compProps:{clearable:!0}}},{field:{name:"name"},element:{comp:"el-input",formItemProps:{label:"任务名称"},compProps:{clearable:!0}}},{field:{name:"group"},element:{comp:"el-input",formItemProps:{label:"任务组"},compProps:{clearable:!0}}}],o=[{field:{name:"schedulerName"},element:{comp:"el-input",formItemProps:{label:"任务计划名称",required:!0},compProps:{clearable:!0}}},{field:{name:"schedulerInstanceId"},element:{comp:"el-input",formItemProps:{label:"任务计划实例id",required:!0},compProps:{clearable:!0}}},{field:{name:"name"},element:{comp:"el-input",formItemProps:{label:"任务名称",required:!0},compProps:{clearable:!0}}},{field:{name:"group"},element:{comp:"el-input",formItemProps:{label:"任务组",required:!0},compProps:{clearable:!0}}},{field:{name:"cronExpression"},element:{comp:"el-input",formItemProps:{label:"cron表达式",required:!0,tips:"用于指定执行时间，例如：0/5 * * * * ?"},compProps:{clearable:!0}}},{field:{name:"jobClassType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"任务实现类型",required:!0},compProps:{dictParam:{groupCode:"schedule_job_impl_type"},props:{value:"value"}}}},{field:{name:"jobClassName"},element:{comp:"el-input",formItemProps:{label:"任务类名称",required:({form:e})=>e.jobClassType=="custom_class",tips:"自定义实现类时，此处填写实现类全路径，建议实现 com.particle.scheduler.infrastructure.job.quartzjob.AbstractQuartzJob类作为基类开发，例如：com.example.job.MyJob"},compProps:{clearable:!0}}},{field:{name:"httpUrl"},element:{comp:"el-input",formItemProps:{label:"请求地址",required:({form:e})=>e.jobClassType=="http_job",tips:"http请求时，此处填写请求地址，例如：http://example.com"},compProps:{clearable:!0}}},{field:{name:"httpMethod",value:"get"},element:{comp:"el-input",formItemProps:{label:"请求方法",required:({form:e})=>e.jobClassType=="http_job",tips:"http请求时，此处填写请求方法，get post put delete等"},compProps:{clearable:!0}}},{field:{name:"httpHeaders"},element:{comp:"el-input",formItemProps:{label:"请求头信息",required:({form:e})=>e.jobClassType=="http_job",tips:"http请求时，此处填写请求头信息，请严格按json填写，对象格式"},compProps:{type:"textarea",clearable:!0}}},{field:{name:"httpParams"},element:{comp:"el-input",formItemProps:{label:"请求参数",required:({form:e})=>e.jobClassType=="http_job",tips:"http请求时，此处填写请求参数，请严格按json填写，对象格式"},compProps:{type:"textarea",clearable:!0}}},{field:{name:"beanName"},element:{comp:"el-input",formItemProps:{label:"bean方法名",required:({form:e})=>e.jobClassType=="spring_bean",tips:"容器bean时，此处填写bean方法名"},compProps:{clearable:!0}}},{field:{name:"beanMethodParams"},element:{comp:"el-input",formItemProps:{label:"bean方法参数",required:({form:e})=>e.jobClassType=="spring_bean",tips:"容器bean时，此处填写bean方法参数，请严格按json填写，数组格式，每一个下标位置对应一个参数"},compProps:{type:"textarea",clearable:!0}}},{field:{name:"scriptType"},element:{comp:"el-input",formItemProps:{label:"脚本类型",required:({form:e})=>e.jobClassType=="script_job",tips:"脚本任务时，此处填写脚本类型，目前仅支持groovy"},compProps:{clearable:!0}}},{field:{name:"scriptContent"},element:{comp:"el-input",formItemProps:{label:"脚本内容",required:({form:e})=>e.jobClassType=="script_job",tips:"脚本任务时，此处填写脚本内容，目前仅支持groovy脚本"},compProps:{type:"textarea",clearable:!0}}},{field:{name:"dataMap"},element:{comp:"el-input",formItemProps:{label:"额外数据",tips:"建议使用ext开头作为key，为避免变量冲突，请严格按json填写，数组格式"},compProps:{type:"textarea",clearable:!0}}},{field:{name:"description"},element:{comp:"el-input",formItemProps:{label:"备注",required:!0},compProps:{clearable:!0}}}],a=o;let r="/admin/web/schedule/job";const m=e=>t.post(r+"/addJob",e),s=e=>t.post(r+"/copyJob",e),n=e=>t.put(r+"/updateJob",e),u=e=>t.delete(r+"/deleteJob",e),c=e=>t.post(r+"/pauseJob",e),i=e=>t.post(r+"/resumeJob",e),b=e=>t.post(r+"/executeOnce",e),d=e=>t.get(r+"/getJobDetailExt",{params:e}),P=e=>t.get(r+"/getJobDetailList",{params:e});export{c as a,o as b,s as c,u as d,b as e,m as f,P as g,n as h,d as i,l as p,i as r,a as u};