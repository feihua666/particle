import{b as p,c as i}from"./crmCustomerContactManage.a0722cbb.js";import{d as e,a as d,r as o,b as f,o as l,h as b}from"./index.bd091b8a.js";import"./crmCompItem.d9c3dd30.js";import"./crmCustomerTagAdminApi.9e96f732.js";const C=e({name:"CrmCustomerContactManageAddPage"}),P=e({...C,props:{crmCustomerId:{type:String}},setup(r){const t=d({form:{crmCustomerId:r.crmCustomerId},formData:{}}),s=o(p),m=o({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:crmCustomerContact:create"}),u=()=>i,a=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(n,h)=>{const c=f("PtForm");return l(),b(c,{form:t.form,formData:t.formData,labelWidth:"80",method:u(),methodSuccess:a,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:n.$route.meta.formButtonsTeleportProps,inline:"",comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{P as default};