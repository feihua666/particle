import{d as o,a as i,r as a,b as l,o as f,c as _,e as b,f as F,F as g,ax as h,ay as B,_ as P}from"./index.3ad8a272.js";import{a as v}from"./tenantManage.dec86b7a.js";const E=o({name:"AreaManageUpdatePage"}),U=o({...E,props:{tenantId:{type:String},masterUserId:{type:String},masterUserNickname:{type:String}},setup(s){const t=s,e=i({form:{id:t.tenantId,version:1},formData:{}}),n=a(v({props:t})),r=a({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:tenant:update"}),u=()=>h,m=()=>B({id:t.tenantId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,y)=>{const d=l("PtForm");return f(),_(g,null,[b(" \u6DFB\u52A0\u8868\u5355 "),F(d,{form:e.form,formData:e.formData,labelWidth:"100",dataMethod:m,method:u(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:r.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,2,2,2,3,3,1,2],comps:n.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),M=P(U,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/tenant/views/admin/TenantManageUpdatePage.vue"]]);export{M as default};