import{d as u,au as p,a as l,r,av as f,I,b as h,o as F,h as b}from"./index.661e0f85.js";import{q as A,t as T}from"./tenantFuncAdminApi.1008d303.js";const P=u({name:"TenantFuncTenantAssignFuncPage"}),v=u({...P,props:{...p,funcApplicationId:{type:String}},setup(s){const e=s,o=l({form:{},formData:{}}),c=r([f({props:e,required:!0}),{field:{name:"checkedFuncIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"\u529F\u80FD",required:!0},compProps:({form:n})=>({dataInitMethod:({param:a})=>{let t=a.tenantId;return t?A({id:t,funcApplicationId:e.funcApplicationId}):{data:[]}},dataInitMethodParam:{tenantId:n.tenantId},dataMethod:()=>I({funcApplicationId:e.funcApplicationId}),dataMethodResultHandleConvertToTree:!0,showCheckbox:!0})}}]),m=r({buttonText:"\u786E\u8BA4",permission:"admin:web:tenantFunc:tenantAssignFunc"}),i=()=>T,d=()=>"\u5206\u914D\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(n,a)=>{const t=h("PtForm");return F(),b(t,{form:o.form,formData:o.formData,labelWidth:"80",method:i(),methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:n.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:c.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};