import{l as g}from"./openplatformDocDirNameAdminApi.b28fe086.js";import{g as t}from"./index.d93ffe12.js";import{l as A}from"./openplatformDocApiDocTemplateAdminApi.e621ed7e.js";let u="/admin/web/openplatform_doc_dir";const b=e=>t.post(u+"/create",e),T=e=>t.delete(u+"/delete",{data:e}),h=e=>t.put(u+"/update",e),M=e=>t.get(u+"/detail-for-update",{params:e}),P=e=>t.get(u+"/list",{params:e}),y=e=>t.get(u+"/page",{params:e});let l="/admin/web/openplatform_doc_api";const x=e=>t.post(l+"/create",e),S=e=>t.delete(l+"/delete",{data:e}),w=e=>t.put(l+"/update",e),O=e=>t.get(l+"/detail-for-update",{params:e}),C=e=>t.get(l+"/list",{params:e}),U=e=>t.get(l+"/page",{params:e}),R=e=>t.post("/api/re/openplatform_doc_html",e);let c="/admin/web/openplatform_doc_api_doc";const H=e=>t.post(c+"/create",e),q=e=>t.delete(c+"/delete",{data:e}),N=e=>t.put(c+"/update",e),j=e=>t.get(c+"/detail-for-update",{params:e}),$=e=>t.get(c+"/list",{params:e}),k=e=>t.get(c+"/page",{params:e});let p="/admin/web/openplatform_doc_api_doc_param_field";const z=e=>t.post(p+"/create",e),G=e=>t.post(p+"/parse-and-create",e),J=e=>t.delete(p+"/delete",{data:e}),K=e=>t.put(p+"/update",e),L=e=>t.get(p+"/detail-for-update",{params:e}),E=e=>t.get(p+"/list",{params:e}),Q=e=>t.get(p+"/page",{params:e});let s="/admin/web/openplatform_doc_api_doc_template_param_field";const V=e=>t.post(s+"/create",e),W=e=>t.post(s+"/parse-and-create",e),X=e=>t.delete(s+"/delete",{data:e}),Y=e=>t.put(s+"/update",e),Z=e=>t.get(s+"/detail-for-update",{params:e}),_=e=>t.get(s+"/list",{params:e}),ee=e=>t.get(s+"/page",{params:e}),te=({required:e=!1,tips:a=""})=>({field:{name:"openplatformDocDirNameId"},element:{comp:"PtSelect",formItemProps:{label:"\u76EE\u5F55\u540D\u79F0",required:e,tips:a||void 0},compProps:{dataMethod:g}}}),ae=({fieldName:e="parentId",required:a=!1,label:d="\u7236\u7EA7",valueChange:n=()=>{},tips:m=""})=>({field:{name:e,valueChange:n},element:{comp:"PtCascader",formItemProps:{label:d,required:a,tips:m||void 0},compProps:{dataMethod:P,dataMethodResultHandleConvertToTree:!0}}}),oe=({required:e=!1,tips:a=""})=>({field:{name:"openplatformDocApiId"},element:{comp:"PtSelect",formItemProps:{label:"\u7ED1\u5B9A\u63A5\u53E3",required:e,tips:a||void 0},compProps:{dataMethod:C}}}),re=({required:e=!1,tips:a=""})=>({field:{name:"openplatformDocApiDocId"},element:{comp:"PtSelect",formItemProps:{label:"\u6587\u6863\u5185\u5BB9",required:e,tips:a||void 0},compProps:{dataMethod:$,props:{label:"requestUrl"}}}}),pe=({fieldName:e="parentId",required:a=!1,label:d="\u7236\u7EA7",valueChange:n=()=>{},tips:m=""})=>({field:{name:e,valueChange:n},element:{comp:"PtCascader",formItemProps:{label:d,required:a,tips:m||void 0,labelTips:"\u9009\u62E9\u6587\u6863\u5185\u5BB9\u548C\u5206\u7C7B\u540E\u53EF\u7528"},compProps:({form:o,formData:D})=>{let r=!1,i;(!o.openplatformDocApiDocId||!o.categoryDictId)&&(r=!0,i="\u9009\u62E9\u6587\u6863\u5185\u5BB9\u548C\u5206\u7C7B\u540E\u53EF\u7528");let f=()=>[];return r||(f=()=>E({openplatformDocApiDocId:o.openplatformDocApiDocId,categoryDictId:o.categoryDictId})),{disabled:r,disabledReason:i,dataMethodParam:{openplatformDocApiDocId:o.openplatformDocApiDocId,categoryDictId:o.categoryDictId},dataMethod:f,dataMethodResultHandleConvertToTree:!0}}}}),se=({required:e=!1,tips:a=""})=>({field:{name:"openplatformDocApiDocTemplateId"},element:{comp:"PtSelect",formItemProps:{label:"\u6587\u6863\u5185\u5BB9\u6A21\u677F",required:e,tips:a||void 0},compProps:{dataMethod:A}}}),ue=({fieldName:e="parentId",required:a=!1,label:d="\u7236\u7EA7",valueChange:n=()=>{},tips:m=""})=>({field:{name:e,valueChange:n},element:{comp:"PtCascader",formItemProps:{label:d,required:a,tips:m||void 0,labelTips:"\u9009\u62E9\u6587\u6863\u5185\u5BB9\u6A21\u677F\u548C\u5206\u7C7B\u540E\u53EF\u7528"},compProps:({form:o,formData:D})=>{let r=!1,i;(!o.openplatformDocApiDocTemplateId||!o.categoryDictId)&&(r=!0,i="\u9009\u62E9\u6587\u6863\u5185\u5BB9\u6A21\u677F\u548C\u5206\u7C7B\u540E\u53EF\u7528");let f=()=>[];return r||(f=()=>_({openplatformDocApiDocTemplateId:o.openplatformDocApiDocTemplateId,categoryDictId:o.categoryDictId})),{disabled:r,disabledReason:i,dataMethodParam:{openplatformDocApiDocTemplateId:o.openplatformDocApiDocTemplateId,categoryDictId:o.categoryDictId},dataMethod:f,dataMethodResultHandleConvertToTree:!0}}}});export{K as A,L as B,ee as C,X as D,ue as E,V as F,W as G,Y as H,Z as I,ae as a,h as b,b as c,M as d,U as e,S as f,R as g,x as h,w as i,O as j,k,q as l,oe as m,se as n,H as o,y as p,N as q,T as r,j as s,Q as t,te as u,J as v,re as w,pe as x,z as y,G as z};