import{q as i,f as p}from"./roleFuncRelAdminApi.88129da9.js";import{r as f,a as h}from"./funcCompItem.e46beb51.js";import{l as b}from"./roleAdminApi.e2ac2933.js";import{d as u,a as F,r as s,b as I,o as P,h as B}from"./index.39bfef88.js";import"./funcAdminApi.65d97e31.js";const R=u({name:"RoleFuncRelManageFuncAssignRolePage"}),M=u({...R,props:{...f},setup(a){const n=a,o=F({form:{},formData:{}}),m=s([h({props:n,required:!0}),{field:{name:"checkedRoleIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"\u89D2\u8272",required:!0},compProps:({form:t})=>({dataInitMethod:({param:r})=>{let e=r.funcId;return e?i({id:e}):{data:[]}},dataInitMethodParam:{funcId:t.funcId},dataMethod:b,dataMethodResultHandleConvertToTree:!0,showCheckbox:!0})}}]),c=s({buttonText:"\u786E\u8BA4",permission:"admin:web:roleFuncRel:funcAssignRole"}),l=()=>p,d=()=>"\u5206\u914D\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(t,r)=>{const e=I("PtForm");return P(),B(e,{form:o.form,formData:o.formData,labelWidth:"80",method:l(),methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:c.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:m.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{M as default};