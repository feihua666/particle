import{d as p}from"./roleUserRelAdminApi.a5215c45.js";import{d as s,n as d,a as i,r as o,q as f,b,o as B,h as _}from"./index.147a787d.js";const h=s({name:"RoleUserRelManageDeleteByUserIdPage"}),F=s({...h,props:{...d},setup(r){const u=r,t=i({form:{},formData:{}}),a=o([f({props:u,required:!0})]),m=o({buttonText:"\u786E\u8BA4",permission:"admin:web:roleUserRel:deleteByUserId"}),n=e=>p({id:e.userId}),c=()=>"\u5220\u9664\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(e,P)=>{const l=b("PtForm");return B(),_(l,{form:t.form,formData:t.formData,labelWidth:"80",method:n,methodSuccess:c,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:a.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])}}});export{F as default};