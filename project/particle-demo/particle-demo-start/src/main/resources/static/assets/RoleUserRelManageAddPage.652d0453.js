import{c as l}from"./roleUserRelAdminApi.437fe6ba.js";import{d as r,q as i,a as f,r as o,t as d,b,o as B,h}from"./index.30ac3b34.js";import{r as _,a as F}from"./roleCompItem.696a4b9c.js";import"./roleAdminApi.f40f0647.js";const P=r({name:"RoleUserRelManageAddPage"}),v=r({...P,props:{...i,..._},setup(s){const e=s,t=f({form:{},formData:{}}),m=o([d({props:e,required:!0}),F({props:e,required:!0})]),u=o({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:roleUserRel:create"}),a=()=>l,n=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,A)=>{const p=b("PtForm");return B(),h(p,{form:t.form,formData:t.formData,labelWidth:"80",method:a(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",comps:m.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};