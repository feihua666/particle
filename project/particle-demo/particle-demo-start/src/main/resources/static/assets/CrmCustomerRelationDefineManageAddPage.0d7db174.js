import{v as c}from"./crmCompItem.468fd5ef.js";import{a as i}from"./crmCustomerRelationDefineManage.403cb329.js";import{d as e,a as p,r as o,b as f,o as l,h as d}from"./index.858f5c96.js";import"./crmCustomerTagAdminApi.8c995151.js";const b=e({name:"CrmCustomerRelationDefineManageAddPage"}),C=e({...b,setup(_){const t=p({form:{},formData:{}}),r=o(i),s=o({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:crmCustomerRelationDefine:create"}),m=()=>c,a=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(u,B)=>{const n=f("PtForm");return l(),d(n,{form:t.form,formData:t.formData,labelWidth:"80",method:m(),methodSuccess:a,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:u.$route.meta.formButtonsTeleportProps,inline:"",comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{C as default};