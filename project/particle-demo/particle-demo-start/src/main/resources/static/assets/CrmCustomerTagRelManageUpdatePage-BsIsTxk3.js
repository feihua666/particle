import{u as d,d as i,e as l}from"./crmCustomerTagRelManage-CA596Zoy.js";import{d as f,a as b,r as o,b as g,o as h,g as C}from"./index-BGwvwH0Y.js";import"./crmCompItem-tSmPV_B9.js";import"./crmCustomerTagAdminApi-CfgqsJYH.js";const R=f({__name:"CrmCustomerTagRelManageUpdatePage",props:{crmCustomerTagRelId:{type:String}},setup(r){const t=r,e=b({form:{id:t.crmCustomerTagRelId,version:1},formData:{}}),s=o(d),a=o({buttonText:"确认修改",permission:"admin:web:crmCustomerTagRel:update"}),m=()=>i,n=()=>l({id:t.crmCustomerTagRelId}),u=()=>"修改成功，请刷新数据查看";return(p,T)=>{const c=g("PtForm");return h(),C(c,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:n,method:m(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:p.$route.meta.formButtonsTeleportProps,inline:"",comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{R as default};