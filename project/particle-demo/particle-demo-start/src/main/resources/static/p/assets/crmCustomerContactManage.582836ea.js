import{h as t}from"./index.49d89bd8.js";import{u as o}from"./crmCompItem.a6ee91d7.js";let r="/admin/web/crm_customer_contact";const c=e=>t.post(r+"/create",e),p=e=>t.delete(r+"/delete",{data:e}),l=e=>t.put(r+"/update",e),s=e=>t.get(r+"/detail-for-update",{params:e}),n=e=>t.get(r+"/page",{params:e}),d=[o({}),{field:{name:"contactTypeDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u8054\u7CFB\u65B9\u5F0F\u7C7B\u578B"},compProps:{dictParam:{groupCode:"contact_type"}}}},{field:{name:"contact"},element:{comp:"el-input",formItemProps:{label:"\u8054\u7CFB\u65B9\u5F0F"},compProps:{clearable:!0,placeholder:"\u5DE6\u524D\u7F00\u5339\u914D"}}}],a=[o({required:!0}),{field:{name:"contactTypeDictId"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u8054\u7CFB\u65B9\u5F0F\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"contact_type"}}}},{field:{name:"contact"},element:{comp:"el-input",formItemProps:{label:"\u8054\u7CFB\u65B9\u5F0F",required:!0},compProps:{clearable:!0}}},{field:{name:"remark"},element:{comp:"el-input",formItemProps:{label:"\u5907\u6CE8"},compProps:{clearable:!0}}}],i=a;export{n as a,a as b,c,l as d,s as e,d as p,p as r,i as u};