import{g as p}from"./roleDataScopeRelAdminApi.3cedf83c.js";import{r as i,a as d}from"./roleCompItem.211b52ea.js";import{d as r,a as f,r as o,b,o as B,h as R}from"./index.bd091b8a.js";import"./roleAdminApi.d0d294a0.js";const _=r({name:"RoleDataScopeRelManageDeleteByRoleIdPage"}),v=r({..._,props:{...i},setup(s){const a=s,t=f({form:{},formData:{}}),u=o([d({props:a,required:!0})]),m=o({buttonText:"\u786E\u8BA4",permission:"admin:web:roleDataScopeRel:deleteByRoleId"}),n=e=>p({id:e.roleId}),l=()=>"\u5220\u9664\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(e,h)=>{const c=b("PtForm");return B(),R(c,{form:t.form,formData:t.formData,labelWidth:"80",method:n,methodSuccess:l,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:u.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};