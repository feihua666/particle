import{a as o}from"./openplatformAppCompItem-DXCwBHcH.js";import{b as t}from"./openplatformOpenapiCompItem-PgDqVVhz.js";import{u as l}from"./crmCompItem-tSmPV_B9.js";import{i as a}from"./index-BGwvwH0Y.js";const c=[o({label:"应用",valueChange:({form:e,newValue:r})=>{r||(e.openplatformOpenapiId=null)}}),t({fieldName:"openplatformOpenapiId",label:"开放接口",disableGroup:!0}),l({fieldName:"customerId",label:"客户"}),{field:{name:"executeStatusDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"执行状态"},compProps:{dictParam:{groupCode:"open_platform_batch_query_execute_status"}}}},{field:{name:"traceId"},element:{comp:"el-input",formItemProps:{label:"追踪id"},compProps:{clearable:!0}}}],i=[{prop:"openplatformAppName",label:"应用名称"},{prop:"openplatformOpenapiName",label:"接口名称"},{prop:"customerName",label:"客户名称"},{prop:"executeStatusDictName",label:"执行状态"},{prop:"successCount",label:"成功条数"},{prop:"failCount",label:"失败条数"},{prop:"totalCount",label:"总条数"},{prop:"userNickname",label:"用户昵称"},{prop:"queryAt",label:"查询时间"},{prop:"traceId",label:"追踪id"},{prop:"uploadFileName",label:"上传文件名"},{prop:"remark",label:"描述"}];let p="/admin/web/openplatform_openapi_batch_query_record";const d=e=>a.delete(p+"/delete",{data:e}),b=e=>a.get(p+"/detail",{params:e}),f=e=>a.get(p+"/page",{params:e});export{f as a,b as d,c as p,d as r,i as t};