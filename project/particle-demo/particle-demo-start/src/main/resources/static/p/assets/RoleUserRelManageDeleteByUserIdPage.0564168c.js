import{d as s,t as p,a as d,r as o,x as i,b as f,o as B,c as _,e as b,f as U,F as D,_ as F}from"./index.3ad8a272.js";import{d as P}from"./roleUserRelAdminApi.dd77d8dd.js";const g=s({name:"RoleUserRelManageDeleteByUserIdPage"}),h=s({...g,props:{...p},setup(r){const a=r,t=d({form:{},formData:{}}),u=o([i({props:a,required:!0})]),m=o({buttonText:"\u786E\u8BA4",permission:"admin:web:roleUserRel:deleteByUserId"}),n=e=>P({id:e.userId}),l=()=>"\u5220\u9664\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(e,R)=>{const c=f("PtForm");return B(),_(D,null,[b(" \u6DFB\u52A0\u8868\u5355 "),U(c,{form:t.form,formData:t.formData,labelWidth:"80",method:n,methodSuccess:l,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:u.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),I=F(h,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/role/views/admin/RoleUserRelManageDeleteByUserIdPage.vue"]]);export{I as default};