import{g as t}from"./index.bd091b8a.js";import{l as c}from"./crmCustomerTagAdminApi.9e96f732.js";let s="/admin/web/crm_company";const P=e=>t.post(s+"/create",e),$=e=>t.delete(s+"/delete",{data:e}),I=e=>t.put(s+"/update",e),b=e=>t.get(s+"/detail-for-update",{params:e}),l=e=>t.get(s+"/list",{params:e}),h=e=>t.get(s+"/page",{params:e});let o="/admin/web/crm_dept";const v=e=>t.post(o+"/create",e),x=e=>t.delete(o+"/delete",{data:e}),M=e=>t.put(o+"/update",e),S=e=>t.get(o+"/detail-for-update",{params:e}),d=e=>t.get(o+"/list",{params:e}),T=e=>t.get(o+"/page",{params:e});let n="/admin/web/crm_customer";const _=e=>t.post(n+"/create",e),w=e=>t.delete(n+"/delete",{data:e}),A=e=>t.put(n+"/update",e),B=e=>t.get(n+"/detail-for-update",{params:e}),i=e=>t.get(n+"/list",{params:e}),D=e=>t.get(n+"/page",{params:e});let m="/admin/web/crm_customer_relation_define";const F=e=>t.post(m+"/create",e),R=e=>t.delete(m+"/delete",{data:e}),y=e=>t.put(m+"/update",e),E=e=>t.get(m+"/detail-for-update",{params:e}),f=e=>t.get(m+"/list",{params:e}),U=e=>t.get(m+"/page",{params:e}),H=({fieldName:e="crmCustomerTagId",required:r=!1,label:a="\u6807\u7B7E"})=>({field:{name:e},element:{comp:"PtSelect",formItemProps:{label:a,required:r},compProps:{clearable:!0,dataMethod:()=>c({})}}}),j=({fieldName:e="crmCustomerRelationDefineId",required:r=!1,label:a="\u5BA2\u6237\u5173\u7CFB\u5B9A\u4E49",isBidirectional:u=null,tips:p=null})=>({field:{name:e},element:{comp:"PtSelect",formItemProps:{label:a,required:r,tips:p},compProps:{clearable:!0,dataMethod:()=>f({isBidirectional:u})}}}),k=({fieldName:e="crmCustomerId",required:r=!1,label:a="\u5BA2\u6237"})=>({field:{name:e},element:{comp:"PtSelect",formItemProps:{label:a,required:r},compProps:{clearable:!0,dataMethod:()=>i({})}}}),q=({fieldName:e="parentId",required:r=!1,label:a="\u7236\u7EA7"})=>({field:{name:e},element:{comp:"PtCascader",formItemProps:{label:a,required:r},compProps:{clearable:!0,dataMethod:()=>l({}),dataMethodResultHandleConvertToTree:!0}}}),z=({fieldName:e="parentId",required:r=!1,label:a="\u7236\u7EA7"})=>({field:{name:e},element:{comp:"PtCascader",formItemProps:{label:a,required:r},compProps:{clearable:!0,dataMethod:()=>d({}),dataMethodResultHandleConvertToTree:!0}}});export{I as a,T as b,P as c,b as d,x as e,z as f,v as g,M as h,S as i,D as j,w as k,_ as l,A as m,B as n,k as o,h as p,U as q,$ as r,R as s,j as t,q as u,F as v,y as w,E as x,H as y};