import{t as u}from"./index.9463e69a.js";const o=[{field:{name:"name"},element:{comp:"el-input",formItemProps:{label:"\u79DF\u6237\u540D\u79F0"},compProps:{clearable:!0,placeholder:"\u5DE6\u524D\u7F00\u5339\u914D"}}},{field:{name:"tenantTypeDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u79DF\u6237\u7C7B\u578B"},compProps:{dictParam:{groupCode:"tenant_type"}}}},u({props:{},fieldName:"applyUserId",label:"\u7533\u8BF7\u7528\u6237"}),{field:{name:"auditStatusDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u5BA1\u6838\u72B6\u6001"},compProps:{dictParam:{groupCode:"tenant_create_apply_audit_status"}}}},u({props:{},fieldName:"auditUserId",label:"\u5BA1\u6838\u7528\u6237"})],r=({props:e,funcApplicationDialogVisible:t})=>[{field:{name:"name"},element:{comp:"el-input",formItemProps:{label:"\u79DF\u6237\u540D\u79F0",required:!0},compProps:{clearable:!0}}},{field:{name:"tenantTypeDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u79DF\u6237\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"tenant_type"}}}},u({props:e,required:!1,fieldName:"applyUserId",propUserIdFieldName:"applyUserId",propUserNicknameFieldName:"applyUserNickname",label:"\u7533\u8BF7\u7528\u6237",tips:"\u586B\u5199\u540E\u624B\u52A8\u8F93\u5165\u7684\u8D26\u53F7\u3001\u90AE\u7BB1\u3001\u624B\u673A\u53F7\u4E0D\u518D\u751F\u6548\uFF0C\u4E0D\u518D\u5339\u914D\u7528\u6237"}),{field:{name:"isFormal",value:!1},element:{comp:"el-switch",formItemProps:{label:"\u662F\u5426\u6B63\u5F0F",required:!0},compProps:{activeText:"\u6B63\u5F0F",inactiveText:"\u8BD5\u7528"}}},{field:{name:"userLimitCount",value:0},element:{comp:"el-input-number",formItemProps:{label:"\u7528\u6237\u6570\u9650\u5236",required:!0,tips:"0 \u4E3A\u4E0D\u9650\u5236"},compProps:{}}},{field:{name:"effectiveDays",value:0},element:{comp:"el-input-number",formItemProps:{label:"\u7533\u8BF7\u5929\u6570",required:!0,tips:"0 \u4E3A\u4E0D\u9650\u5236\uFF0C\u5C06\u6839\u636E\u622A\u6B62\u65E5\u671F\u81EA\u52A8\u8BA1\u7B97"},compProps:{}}},{field:{name:"effectiveAt"},element:{comp:"PtDatePicker",formItemProps:{label:"\u751F\u6548\u65F6\u95F4",tips:"\u4E0D\u586B\u5199\u7ACB\u5373\u751F\u6548,\u5C06\u4F7F\u7528\u5F53\u524D\u65F6\u95F4"},compProps:{clearable:!0,type:"datetime"}}},{field:{name:"expireAt"},element:{comp:"PtDatePicker",formItemProps:{label:"\u5931\u6548\u65F6\u95F4",tips:"\u4E0D\u586B\u5199\u6C38\u4E0D\u5931\u6548\uFF0C\u5C06\u6839\u636E\u7533\u8BF7\u5929\u6570\u81EA\u52A8\u8BA1\u7B97"},compProps:{clearable:!0,type:"datetime"}}},{field:{name:"extJsonObj"},element:{comp:"PtButton",formItemProps:{label:"\u7533\u8BF7\u5E94\u7528",tips:"\u9009\u62E9\u7533\u8BF7\u7684\u5E94\u7528\u53CA\u529F\u80FD",required:!0},compProps:({form:l,formData:m})=>({text:!0,type:l.extJsonObj?"primary":"default",buttonText:"\u70B9\u51FB\u5E94\u7528\u8BBE\u7F6E",method:()=>{t&&(t.value=!0)}})}},{field:{name:"userName"},element:{comp:"el-input",formItemProps:{label:"\u59D3\u540D",tips:"\u5982\u679C\u7528\u6237\u4E0D\u5B58\u5728\u5C06\u4F5C\u4E3A\u7528\u6237\u7684\u6635\u79F0,\u521B\u5EFA\u7528\u6237"},compProps:{placeholder:"\u59D3\u540D",clearable:!0}}},{field:{name:"account"},element:{comp:"el-input",formItemProps:{label:"\u8D26\u53F7",tips:"\u5982\u679C\u7528\u6237\u4E0D\u5B58\u5728\u5C06\u4F5C\u4E3A\u7528\u6237\u767B\u5F55\u8D26\u53F7\u5339\u914D\u7528\u6237"},compProps:{placeholder:"\u8D26\u53F7",clearable:!0}}},{field:{name:"email"},element:{comp:"el-input",formItemProps:{label:"\u90AE\u7BB1",tips:"\u5982\u679C\u7528\u6237\u4E0D\u5B58\u5728\u5C06\u4F5C\u4E3A\u7528\u6237\u767B\u5F55\u8D26\u53F7\u5339\u914D\u7528\u6237",validate:{email:!0}},compProps:{placeholder:"\u90AE\u7BB1",clearable:!0}}},{field:{name:"mobile"},element:{comp:"el-input",formItemProps:{label:"\u624B\u673A\u53F7",tips:"\u5982\u679C\u7528\u6237\u4E0D\u5B58\u5728\u5C06\u4F5C\u4E3A\u7528\u6237\u767B\u5F55\u8D26\u53F7\u5339\u914D\u7528\u6237",validate:{mobile:!0}},compProps:{placeholder:"\u624B\u673A\u53F7",clearable:!0}}},{field:{name:"password"},element:{comp:"el-input",formItemProps:{label:"\u5BC6\u7801",tips:"\u5982\u679C\u7528\u6237\u4E0D\u5B58\u5728\u5C06\u4F5C\u4E3A\u521D\u59CB\u5BC6\u7801\uFF0C\u4E0D\u586B\u5199\u5C06\u81EA\u52A8\u751F\u6210"},compProps:{placeholder:"\u5BC6\u7801",clearable:!0}}},{field:{name:"isSendEmailNotice",value:!0},element:{comp:"el-switch",formItemProps:{label:"\u53D1\u9001\u90AE\u4EF6\u901A\u77E5",tips:"\u4EC5\u90AE\u7BB1\u5B58\u5728\u65F6\u751F\u6548"},compProps:{activeText:"\u53D1\u9001",inactiveText:"\u4E0D\u53D1\u9001"}}},{field:{name:"remark"},element:{comp:"el-input",formItemProps:{label:"\u63CF\u8FF0"},compProps:{clearable:!0}}}],p=r,i=({props:e})=>[{field:{name:"auditStatusDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u5BA1\u6838\u72B6\u6001",required:!0},compProps:{dictParam:{groupCode:"tenant_create_apply_audit_status",tags:"audit"}}}},{field:{name:"auditStatusComment"},element:{comp:"el-input",formItemProps:{label:"\u5BA1\u6838\u610F\u89C1",required:!0,displayBlock:!0},compProps:{type:"textarea",clearable:!0,rows:10,placeholder:"\u586B\u5199\u60A8\u7684\u5BA1\u6838\u610F\u89C1"}}}],s=r;export{r as a,p as b,i as c,o as p,s as u};