import{h as t}from"./index.49d89bd8.js";import{u as a}from"./openplatformAppCompItem.6545e816.js";import{u as o}from"./oauth2authorizationRegisteredClientCompItem.5ebf5298.js";import{u as m}from"./openplatformOpenapiCompItem.3f3d378b.js";import{u as p}from"./crmCompItem.a6ee91d7.js";let u="/admin/web/openplatform_openapi_record_app_openapi_month_summary";const c=e=>t.delete(u+"/delete",{data:e}),E=e=>t.get(u+"/page",{params:e}),F=e=>t.post(u+"/lastMonthStatistic",e),f=e=>t.post(u+"/thisMonthStatistic",e),d=[a({}),o({fieldName:"appId",label:"appId"}),m({fieldName:"openplatformOpenapiId",label:"\u5F00\u653E\u63A5\u53E3",disableGroup:!0}),{field:{name:"year"},element:{comp:"PtDatePicker",formItemProps:{label:"\u5E74"},compProps:{type:"year",valueFormat:"YYYY"}}},{field:{name:"month"},element:{comp:"PtDatePicker",formItemProps:{label:"\u6708"},compProps:{type:"month",format:"MM",valueFormat:"MM"}}},p({fieldName:"customerId",label:"\u5BA2\u6237"})],C=[{field:{name:"isIncludeDaySummary"},element:{comp:"el-switch",formItemProps:{label:"\u662F\u5426\u7EDF\u8BA1\u65E5\u6C47\u603B",tips:"\u5982\u679C\u4E0D\u7EDF\u8BA1\u65E5\u6C47\u603B\uFF0C\u8BF7\u786E\u4FDD\u7EDF\u8BA1\u6708\u4EFD\u7684\u65E5\u6C47\u603B\u5DF2\u5B58\u5728\uFF0C\u5426\u5219\u7EDF\u8BA1\u4E0D\u5230\u6570\u636E"},compProps:{activeText:"\u7EDF\u8BA1\u65E5\u6C47\u603B",inactiveText:"\u4E0D\u7EDF\u8BA1\u65E5\u6C47\u603B"}}}];export{E as a,F as l,C as m,d as p,c as r,f as t};