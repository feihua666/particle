import{w as d,x as f}from"./crmCompItem.34865f6e.js";import{u as l}from"./crmCustomerRelationDefineManage.3adbafd0.js";import{d as r,a as b,r as o,b as h,o as C,h as D}from"./index.6cdd7c8b.js";import"./crmCustomerTagAdminApi.5b6b7d74.js";const F=r({name:"CrmCustomerRelationDefineManageUpdatePage"}),R=r({...F,props:{crmCustomerRelationDefineId:{type:String}},setup(s){const t=s,e=b({form:{id:t.crmCustomerRelationDefineId,version:1},formData:{}}),a=o(l),m=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:crmCustomerRelationDefine:update"}),u=()=>d,n=()=>f({id:t.crmCustomerRelationDefineId}),i=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(p,_)=>{const c=h("PtForm");return C(),D(c,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:n,method:u(),methodSuccess:i,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:p.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{R as default};