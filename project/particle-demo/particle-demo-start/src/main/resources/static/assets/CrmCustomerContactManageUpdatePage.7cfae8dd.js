import{u as i,d as f,e as l}from"./crmCustomerContactManage.d9c567e5.js";import{d as r,a as C,r as e,b,o as h,h as F}from"./index.858f5c96.js";import"./crmCompItem.468fd5ef.js";import"./crmCustomerTagAdminApi.8c995151.js";const _=r({name:"CrmCustomerContactManageUpdatePage"}),g=r({..._,props:{crmCustomerContactId:{type:String}},setup(s){const t=s,o=C({form:{id:t.crmCustomerContactId,version:1},formData:{}}),a=e(i),u=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:crmCustomerContact:update"}),m=()=>f,n=()=>l({id:t.crmCustomerContactId}),c=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(p,B)=>{const d=b("PtForm");return h(),F(d,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:n,method:m(),methodSuccess:c,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:p.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{g as default};