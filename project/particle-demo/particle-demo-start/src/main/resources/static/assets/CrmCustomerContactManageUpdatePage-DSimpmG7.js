import{u as d,d as i,e as f}from"./crmCustomerContactManage-DmN-Oz0R.js";import{d as l,a as C,r as e,b,o as h,g as P}from"./index-D8BtRcSm.js";import"./crmCompItem-DsX0QlD0.js";import"./crmCustomerTagAdminApi-CDprfrs5.js";const F=l({__name:"CrmCustomerContactManageUpdatePage",props:{crmCustomerContactId:{type:String}},setup(r){const t=r,o=C({form:{id:t.crmCustomerContactId,version:1},formData:{}}),s=e(d),a=e({buttonText:"确认修改",permission:"admin:web:crmCustomerContact:update"}),m=()=>i,n=()=>f({id:t.crmCustomerContactId}),u=()=>"修改成功，请刷新数据查看";return(c,_)=>{const p=b("PtForm");return h(),P(p,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:n,method:m(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{F as default};