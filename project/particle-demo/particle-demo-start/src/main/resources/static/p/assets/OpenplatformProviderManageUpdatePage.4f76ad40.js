import{d as r,a as c,r as o,b as f,o as l,c as v,e as P,f as _,F as b,_ as F}from"./index.3ad8a272.js";import{u as g,d as h}from"./openplatformProviderAdminApi.58a171b7.js";import{u as B}from"./openplatformProviderManage.e97a844a.js";import"./dataqueryProviderCompItem.2777c275.js";import"./dataQueryProviderAdminApi.a0748a53.js";const E=r({name:"AreaManageUpdatePage"}),A=r({...E,props:{openplatformProviderId:{type:String}},setup(a){const e=a,t=c({form:{id:e.openplatformProviderId,version:1},formData:{}}),s=o(B),m=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:openplatformProvider:update"}),n=()=>g,u=()=>h({id:e.openplatformProviderId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(d,D)=>{const i=f("PtForm");return l(),v(b,null,[P(" \u6DFB\u52A0\u8868\u5355 "),_(i,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:u,method:n(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:d.$route.meta.formButtonsTeleportProps,inline:"",comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),S=F(A,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/openplatform/views/provider/admin/OpenplatformProviderManageUpdatePage.vue"]]);export{S as default};