import{d as r,a as p,r as o,b as i,o as d,c as f,e as B,f as R,F as _,_ as b}from"./index.49d89bd8.js";import{e as D}from"./roleUserRelAdminApi.961980c4.js";import{r as F,a as P}from"./roleCompItem.d08fb299.js";import"./roleAdminApi.63f31953.js";const g=r({name:"RoleUserRelManageDeleteByRoleIdPage"}),h=r({...g,props:{...F},setup(s){const a=s,t=p({form:{},formData:{}}),u=o([P({props:a,required:!0})]),m=o({buttonText:"\u786E\u8BA4",permission:"admin:web:roleUserRel:deleteByUserId"}),l=e=>D({id:e.roleId}),n=()=>"\u5220\u9664\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(e,v)=>{const c=i("PtForm");return d(),f(_,null,[B(" \u6DFB\u52A0\u8868\u5355 "),R(c,{form:t.form,formData:t.formData,labelWidth:"80",method:l,methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:u.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),w=b(h,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/role/views/admin/RoleUserRelManageDeleteByRoleIdPage.vue"]]);export{w as default};