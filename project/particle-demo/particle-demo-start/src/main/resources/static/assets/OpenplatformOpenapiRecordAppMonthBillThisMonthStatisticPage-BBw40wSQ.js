import{m as p}from"./openplatformOpenapiRecordCustomerMonthBillManage-CXL5yZTQ.js";import{t as i}from"./openplatformOpenapiRecordAppMonthBillAdminApi-DV7tXceR.js";import{d as c,a as u,r as o,b as l,o as f,g as h}from"./index-BGwvwH0Y.js";import"./crmCompItem-tSmPV_B9.js";import"./crmCustomerTagAdminApi-CfgqsJYH.js";const A=c({__name:"OpenplatformOpenapiRecordAppMonthBillThisMonthStatisticPage",setup(d){const t=u({form:{},formData:{}}),e=o(p),r=o({buttonText:"确认统计",permission:"admin:web:openplatformOpenapiRecordAppOpenapiMonthSummary:thisMonthStatistic"}),s=()=>i,m=()=>"统计成功，请刷新数据查看";return(a,b)=>{const n=l("PtForm");return f(),h(n,{form:t.form,formData:t.formData,labelWidth:"80",method:s(),methodSuccess:m,defaultButtonsShow:"submit,reset",submitAttrs:r.value,buttonsTeleportProps:a.$route.meta.formButtonsTeleportProps,inline:"",comps:e.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{A as default};