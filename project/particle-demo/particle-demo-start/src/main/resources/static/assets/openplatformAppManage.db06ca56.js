import{t as o}from"./index.858f5c96.js";import{l as s}from"./oauth2RegisteredClientAdminApi.4f9e6ee5.js";import{u as l}from"./openplatformOpenapiFeeCompItem.b64a1f8d.js";const m=({props:t={},required:u=!1,fieldName:e="clientId",label:r="oauth2\u5BA2\u6237\u7AEF",valueChange:a=()=>{},tips:p=""})=>({field:{name:e,valueChange:a},element:{comp:"PtSelect",formItemProps:{label:r,required:u,tips:p},compProps:{dataMethod:s,props:{value:"clientId",label:"clientName"}}}}),c=[{field:{name:"name"},element:{comp:"el-input",formItemProps:{label:"\u540D\u79F0"},compProps:{clearable:!0,placeholder:"\u5DE6\u524D\u7F00\u5339\u914D"}}},m({fieldName:"appId",label:"appId"}),o({props:{},fieldName:"ownerUserId",propUserIdFieldName:"ownerUserId",propUserNicknameFieldName:"ownerUserNickname",label:"\u5F52\u5C5E\u7528\u6237"}),{field:{name:"ownerCustomerId"},element:{comp:"el-input",formItemProps:{label:"\u5F52\u5C5E\u5BA2\u6237"},compProps:{disabled:!0,disabledReason:"\u6682\u4E0D\u652F\u6301\uFF0C\u9884\u7559"}}},l({})],i=({props:t,appAlgorithmSecretConfigsRef:u})=>[{field:{name:"name"},element:{comp:"el-input",formItemProps:{label:"\u540D\u79F0",required:!0,tips:"\u5EFA\u8BAE\u586B\u5199\u4E09\u65B9\u7533\u8BF7\u516C\u53F8/\u59D3\u540D"},compProps:{clearable:!0}}},m({fieldName:"appId",label:"appId",tips:"\u9700\u8981\u524D\u5F80 oauth2\u5BA2\u6237\u7AEF\u7BA1\u7406 \u521B\u5EFA",required:!0}),o({props:t,fieldName:"ownerUserId",propUserIdFieldName:"ownerUserId",propUserNicknameFieldName:"ownerUserNickname",label:"\u5F52\u5C5E\u7528\u6237",tips:"\u8FD9\u6709\u5229\u4E8E\u7528\u6237\u81EA\u52A8\u64CD\u4F5C\u65F6\u7ED1\u5B9A\u5173\u7CFB"}),{field:{name:"ownerCustomerId"},element:{comp:"el-input",formItemProps:{label:"\u5F52\u5C5E\u5BA2\u6237"},compProps:{disabled:!0,disabledReason:"\u6682\u4E0D\u652F\u6301\uFF0C\u9884\u7559"}}},{field:{name:"requestAlgorithmSecretJson"},element:{comp:"PtButton",formItemProps:{label:"\u8BF7\u6C42\u914D\u7F6E",required:!0,tips:"\u8BF7\u6C42\u7B97\u6CD5\u4E0E\u5BC6\u94A5\u7B49\u76F8\u5173\u914D\u7F6E"},compProps:({form:e,formData:r})=>({text:!0,type:e.requestAlgorithmSecretJson?"primary":"default",buttonText:"\u70B9\u51FB\u914D\u7F6E",method:()=>{u.value&&(u.value.reactiveData.openapiRequestAlgorithmSecretConfigJson.dialogVisible=!0)}})}},{field:{name:"responseAlgorithmSecretJson"},element:{comp:"PtButton",formItemProps:{label:"\u54CD\u5E94\u914D\u7F6E",tips:"\u54CD\u5E94\u7B97\u6CD5\u4E0E\u5BC6\u94A5\u7B49\u76F8\u5173\u914D\u7F6E"},compProps:({form:e,formData:r})=>({text:!0,type:e.responseAlgorithmSecretJson?"primary":"default",buttonText:"\u70B9\u51FB\u914D\u7F6E",method:()=>{u.value&&(u.value.reactiveData.openapiResponseAlgorithmSecretConfigJson.dialogVisible=!0)}})}},{field:{name:"scopes"},element:{comp:"el-input",formItemProps:{label:"\u8BBF\u95EE\u8303\u56F4\u914D\u7F6E",tips:"\u989D\u5916\u914D\u7F6E\u7684\u8BBF\u95EE\u6743\u9650,\u591A\u4E2A\u4EE5\u9017\u53F7\u5206\u9694"},compProps:{placeholder:"\u5982\uFF1Aadmin:user:create",clearable:!0}}},l({tips:"\u9ED8\u8BA4\u7684\u8BA1\u8D39\u914D\u7F6E\uFF0C\u53EF\u4EE5\u5728\u5206\u914D\u63A5\u53E3\u65F6\u5355\u72EC\u6307\u5B9A\u8BA1\u8D39\u89C4\u5219\uFF0C\u4E0D\u914D\u7F6E\uFF0C\u4E0D\u8FDB\u884C\u8BA1\u8D39"}),{field:{name:"isDisabled",value:!1},element:{comp:"el-switch",formItemProps:{label:"\u662F\u5426\u7981\u7528",required:!0},compProps:{}}},{field:{name:"disabledReason"},element:{comp:"el-input",formItemProps:{label:"\u7981\u7528\u539F\u56E0",required:({form:e})=>e.isDisabled==!0},compProps:{clearable:!0}}},{field:{name:"isCheckSignature",value:!0},element:{comp:"el-switch",formItemProps:{label:"\u662F\u5426\u68C0\u67E5\u7B7E\u540D",required:!0},compProps:{}}}],E=i;export{E as a,c as p,i as u};