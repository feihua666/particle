import{u as i,d as f}from"./crmCustomerTagAdminApi.9e96f732.js";import{u as l}from"./crmCustomerTagManage.c4c9b00c.js";import{d as r,a as b,r as e,b as h,o as C,h as F}from"./index.bd091b8a.js";const g=r({name:"CrmCustomerTagManageUpdatePage"}),P=r({...g,props:{crmCustomerTagId:{type:String}},setup(s){const t=s,o=b({form:{id:t.crmCustomerTagId,version:1},formData:{}}),a=e(l),u=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:crmCustomerTag:update"}),m=()=>i,n=()=>f({id:t.crmCustomerTagId}),c=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(p,T)=>{const d=h("PtForm");return C(),F(d,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:n,method:m(),methodSuccess:c,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:p.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{P as default};