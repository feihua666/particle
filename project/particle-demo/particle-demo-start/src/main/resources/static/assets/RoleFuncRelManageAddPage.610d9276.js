import{c as l}from"./roleFuncRelAdminApi.a4b10acc.js";import{d as r,G as i,a as f,r as o,H as d,b,o as F,h as B}from"./index.661e0f85.js";import{r as h,a as _}from"./roleCompItem.83590b31.js";import"./roleAdminApi.e03f302d.js";const P=r({name:"RoleFuncRelManageAddPage"}),v=r({...P,props:{...i,...h},setup(s){const e=s,t=f({form:{},formData:{}}),u=o([d({props:e,required:!0}),_({props:e,required:!0})]),m=o({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:roleFuncRel:create"}),a=()=>l,n=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,A)=>{const p=b("PtForm");return F(),B(p,{form:t.form,formData:t.formData,labelWidth:"80",method:a(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",comps:u.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};