import{u as c,d as f}from"./navigationSiteAdminApi.cb7d72e6.js";import{u as l}from"./navigationSiteManage.ae5f305f.js";import{d as a,a as b,r as e,b as v,o as h,h as F}from"./index.6cdd7c8b.js";const g=a({name:"NavigationSiteManageUpdatePage"}),P=a({...g,props:{navigationSiteId:{type:String}},setup(r){const t=r,o=b({form:{id:t.navigationSiteId,version:1},formData:{}}),s=e(l),u=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:navigationSite:update"}),n=()=>c,m=()=>f({id:t.navigationSiteId}),i=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(p,S)=>{const d=v("PtForm");return h(),F(d,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:i,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:p.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,3,1,1,1,1],comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{P as default};