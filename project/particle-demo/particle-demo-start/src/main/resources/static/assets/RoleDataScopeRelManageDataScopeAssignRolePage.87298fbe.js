import{b as l,e as i}from"./roleDataScopeRelAdminApi.6cb0e218.js";import{r as f,u as S,a as b}from"./dataconstraintCompItem.19812417.js";import{l as h}from"./roleAdminApi.d46815c8.js";import{d as u,a as D,r as s,b as I,o as P,h as B}from"./index.858f5c96.js";import"./dataObjectAdminApi.59ae2d87.js";import"./dataScopeAdminApi.9fbe2fd6.js";const C=u({name:"RoleDataScopeRelManageDataScopeAssignRolePage"}),g=u({...C,props:{...f,dataObjectId:{type:String}},setup(m){const o=m,a=D({form:{},formData:{}}),n=s([S({props:o,required:!0}),b({props:o,required:!1}),{field:{name:"checkedRoleIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"\u89D2\u8272",required:!0},compProps:({form:t})=>({dataInitMethod:({param:r})=>{let e=r.dataScopeId;return e?l({id:e}):{data:[]}},dataInitMethodParam:{dataScopeId:t.dataScopeId},dataMethod:h,dataMethodResultHandleConvertToTree:!0,showCheckbox:!0})}}]),c=s({buttonText:"\u786E\u8BA4",permission:"admin:web:roleDataScopeRel:dataScopeAssignRole"}),p=()=>i,d=()=>"\u5206\u914D\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(t,r)=>{const e=I("PtForm");return P(),B(e,{form:a.form,formData:a.formData,labelWidth:"80",method:p(),methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:c.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:n.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{g as default};