import{e as p}from"./roleFuncRelAdminApi.53321286.js";import{r as i,a as d}from"./roleCompItem.9d427723.js";import{d as r,a as f,r as o,b,o as B,h as F}from"./index.9463e69a.js";import"./roleAdminApi.2e039afa.js";const _=r({name:"RoleFuncRelManageDeleteByRoleIdPage"}),C=r({..._,props:{...i},setup(s){const u=s,t=f({form:{},formData:{}}),m=o([d({props:u,required:!0})]),a=o({buttonText:"\u786E\u8BA4",permission:"admin:web:roleFuncRel:deleteByFuncId"}),n=e=>p({id:e.roleId}),l=()=>"\u5220\u9664\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(e,h)=>{const c=b("PtForm");return B(),F(c,{form:t.form,formData:t.formData,labelWidth:"80",method:n,methodSuccess:l,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:m.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])}}});export{C as default};