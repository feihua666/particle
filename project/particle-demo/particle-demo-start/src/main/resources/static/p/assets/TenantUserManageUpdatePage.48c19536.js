import{d as a,a as i,r as o,b as f,o as l,c as b,e as _,f as F,F as g,_ as U}from"./index.3ad8a272.js";import{b as h,d as B,e as P}from"./tenantUserManage.a278e8bc.js";const v=a({name:"AreaManageUpdatePage"}),E=a({...v,props:{tenantUserId:{type:String},userId:{type:String},userNickname:{type:String}},setup(s){const e=s,t=i({form:{id:e.tenantUserId,version:1},formData:{}}),r=o(h({props:e})),n=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:tenantUser:update"}),u=()=>B,m=()=>P({id:e.tenantUserId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,A)=>{const d=f("PtForm");return l(),b(g,null,[_(" \u6DFB\u52A0\u8868\u5355 "),F(d,{form:t.form,formData:t.formData,labelWidth:"100",dataMethod:m,method:u(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:n.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),S=U(E,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/tenant/views/admin/TenantUserManageUpdatePage.vue"]]);export{S as default};