import{d as r,az as l,a as d,r as a,aA as f,b as h,o as A,h as F}from"./index.d93ffe12.js";import{l as b}from"./funcApplicationAdminApi.adeb1c26.js";import{q as T,t as I}from"./tenantFuncApplicationAdminApi.740f4a52.js";const P=r({name:"TenantFuncApplicationTenantAssignFuncApplicationPage"}),C=r({...P,props:{...l},setup(s){const u=s,o=d({form:{},formData:{}}),m=a([f({props:u,required:!0}),{field:{name:"checkedFuncApplicationIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"\u529F\u80FD\u5E94\u7528",required:!0},compProps:({form:e})=>({dataInitMethod:({param:n})=>{let t=n.tenantId;return t?T({id:t}):{data:[]}},dataInitMethodParam:{tenantId:e.tenantId},dataMethod:b,dataMethodResultHandleConvertToTree:!0,showCheckbox:!0})}}]),c=a({buttonText:"\u786E\u8BA4",permission:"admin:web:tenantFuncApplication:tenantAssignFuncApplication"}),i=()=>I,p=()=>"\u5206\u914D\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(e,n)=>{const t=h("PtForm");return A(),F(t,{form:o.form,formData:o.formData,labelWidth:"80",method:i(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:c.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:m.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{C as default};