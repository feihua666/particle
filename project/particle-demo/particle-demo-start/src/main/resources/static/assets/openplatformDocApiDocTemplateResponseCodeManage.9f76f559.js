import{g as u}from"./index.858f5c96.js";import{n as r}from"./openplatformDocCompItem.88ea8882.js";let t="/admin/web/openplatform_doc_api_doc_template_response_code";const l=e=>u.post(t+"/create",e),m=e=>u.delete(t+"/delete",{data:e}),s=e=>u.put(t+"/update",e),c=e=>u.get(t+"/detail-for-update",{params:e}),n=e=>u.get(t+"/page",{params:e}),i=[{field:{name:"code"},element:{comp:"el-input",formItemProps:{label:"\u7F16\u7801"},compProps:{clearable:!0,placeholder:"\u5DE6\u524D\u7F00\u5339\u914D"}}},{field:{name:"codeStatus"},element:{comp:"el-input",formItemProps:{label:"\u4E1A\u52A1\u72B6\u6001\u7801\u7801"},compProps:{clearable:!0,placeholder:"\u5DE6\u524D\u7F00\u5339\u914D"}}},{field:{name:"explanation"},element:{comp:"el-input",formItemProps:{label:"\u5B57\u6BB5\u8BF4\u660E"},compProps:{clearable:!0,placeholder:"\u5DE6\u524D\u7F00\u5339\u914D"}}},r({})],o=[{field:{name:"code"},element:{comp:"el-input",formItemProps:{label:"\u7F16\u7801",required:!0,tips:"\u54CD\u5E94\u4EE3\u7801\uFF0C\u5982\uFF1Auser_not_found"},compProps:{clearable:!0}}},{field:{name:"codeStatus"},element:{comp:"el-input",formItemProps:{label:"\u4E1A\u52A1\u72B6\u6001\u7801",required:!0,tips:"\u54CD\u5E94\u4E1A\u52A1\u72B6\u6001\u7801\uFF0C\u5982\uFF1A40300000001"},compProps:{clearable:!0}}},{field:{name:"httpCode"},element:{comp:"el-input",formItemProps:{label:"http\u54CD\u5E94\u7801",tips:"http\u72B6\u6001\u7801\uFF0C\u5982\uFF1A200\u3001500\uFF0C\u53EA\u80FD\u4E3A\u6570\u5B57"},compProps:{clearable:!0}}},{field:{name:"explanation"},element:{comp:"el-input",formItemProps:{label:"\u5B57\u6BB5\u8BF4\u660E",required:!0},compProps:{clearable:!0}}},{field:{name:"isCharge"},element:{comp:"el-switch",formItemProps:{label:"\u662F\u5426\u8BA1\u8D39",required:!0},compProps:{activeText:"\u662F",inactiveText:"\u5426"}}},r({required:!0,tips:"\u7ED1\u5B9A\u7684\u6587\u6863\u5185\u5BB9\u6A21\u677F"}),{field:{name:"isGlobal"},element:{comp:"el-switch",formItemProps:{label:"\u662F\u5426\u5168\u5C40\u901A\u7528\u7801",required:!0},compProps:{activeText:"\u662F",inactiveText:"\u5426"}}},{field:{name:"seq",value:10},element:{comp:"el-input-number",formItemProps:{label:"\u6392\u5E8F"},compProps:{clearable:!0}}},{field:{name:"remark"},element:{comp:"el-input",formItemProps:{label:"\u63CF\u8FF0",tips:"\u5B57\u7B26\u8BF4\u660E\u7684\u989D\u5916\u63CF\u8FF0"},compProps:{clearable:!0}}}],d=o;export{n as a,o as b,l as c,s as d,c as e,i as p,m as r,d as u};