import{d as r,t as p,a as i,r as o,x as d,b as f,o as _,c as b,e as B,f as F,F as R,_ as A}from"./index.3ad8a272.js";import{c as P}from"./roleUserRelAdminApi.dd77d8dd.js";import{r as h,a as g}from"./roleCompItem.4cbc3d12.js";import"./roleAdminApi.e95de39f.js";const D=r({name:"RoleUserRelManageAddPage"}),v=r({...D,props:{...p,...h},setup(s){const e=s,t=i({form:{},formData:{}}),a=o([d({props:e,required:!0}),g({props:e,required:!0})]),u=o({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:roleUserRel:create"}),m=()=>P,n=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,C)=>{const l=f("PtForm");return _(),b(R,null,[B(" \u6DFB\u52A0\u8868\u5355 "),F(l,{form:t.form,formData:t.formData,labelWidth:"80",method:m(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),x=A(v,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/role/views/admin/RoleUserRelManageAddPage.vue"]]);export{x as default};