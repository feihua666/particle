import{g as t}from"./index.661e0f85.js";import{l as d}from"./deptTreeNameAdminApi.55f0d9b6.js";let r="/admin/web/dept";const c=e=>t.post(r+"/create",e),i=e=>t.delete(r+"/delete",{data:e}),f=e=>t.put(r+"/update",e),g=e=>t.get(r+"/detail-for-update",{params:e}),n=e=>t.get(r+"/list",{params:e}),P=e=>t.get(r+"/page",{params:e});let a="/admin/web/dept_tree";const C=e=>t.post(a+"/create",e),I=e=>t.delete(a+"/delete",{data:e}),T=e=>t.put(a+"/update",e),$=e=>t.get(a+"/detail-for-update",{params:e}),u=e=>t.get(a+"/list",{params:e}),b=e=>t.get(a+"/page",{params:e}),h=({fieldName:e="parentId",required:s=!1,label:o="\u7236\u7EA7"})=>({field:{name:e},element:{comp:"PtCascader",formItemProps:{label:o,required:s},compProps:{clearable:!0,dataMethod:()=>n({}),dataMethodResultHandleConvertToTree:!0}}}),M=({fieldName:e="parentId",required:s=!1,label:o="\u7236\u7EA7",propsAttr:p={}})=>({field:{name:e},element:{comp:"PtCascader",formItemProps:{label:o,required:s},compProps:{clearable:!0,dataMethod:()=>u({}),dataMethodResultHandleConvertToTree:!0,props:p}}}),v=({})=>({field:{name:"deptTreeNameId"},element:{comp:"PtSelect",formItemProps:{label:"\u90E8\u95E8\u6811\u540D\u79F0"},compProps:{dataMethod:d}}});export{f as a,b,c,g as d,I as e,v as f,M as g,C as h,T as i,$ as j,P as p,i as r,h as u};