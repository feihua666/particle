import{d as a,a as p,r as s,L as d,b as f,o as b,c as h,e as R,f as _,F as g,_ as P}from"./index.49d89bd8.js";import{a as U,b as I}from"./roleUserRelAdminApi.961980c4.js";import{r as A,a as B}from"./roleCompItem.d08fb299.js";import"./roleAdminApi.63f31953.js";const F=a({name:"RoleUserRelManageUserAssignRolePage"}),v=a({...F,props:{...A},setup(u){const n=u,o=p({form:{},formData:{}}),m=s([B({props:n,required:!0}),{field:{name:"checkedUserIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"\u7528\u6237",required:!0},compProps:({form:t})=>({dataInitMethod:({param:r})=>{let e=r.roleId;return e?U({id:e}):{data:[]}},dataInitMethodParam:{roleId:t.roleId},dataMethod:d,showCheckbox:!0,props:{label:"nickname"}})}}]),l=s({buttonText:"\u786E\u8BA4",permission:"admin:web:roleUserRel:roleAssignUser"}),c=()=>I,i=()=>"\u5206\u914D\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(t,r)=>{const e=f("PtForm");return b(),h(g,null,[R(" \u6DFB\u52A0\u8868\u5355 "),_(e,{form:o.form,formData:o.formData,labelWidth:"80",method:c(),methodSuccess:i,defaultButtonsShow:"submit,reset",submitAttrs:l.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:m.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),k=P(v,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/role/views/admin/RoleUserRelManageRoleAssignUserPage.vue"]]);export{k as default};