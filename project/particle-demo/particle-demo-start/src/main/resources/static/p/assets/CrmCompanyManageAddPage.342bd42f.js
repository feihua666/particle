import{d as o,a as c,r as e,b as i,o as d,c as f,e as l,f as _,F as b,_ as C}from"./index.49d89bd8.js";import{c as F}from"./crmCompItem.a6ee91d7.js";import{a as B}from"./crmCompanyManage.e8687ba6.js";import"./crmCustomerTagAdminApi.245e486d.js";import"./treeQueryComps.5854cd36.js";const g=o({name:"CrmCompanyManageAddPage"}),A=o({...g,props:{parentId:{type:String}},setup(r){const t=c({form:{parentId:r.parentId},formData:{}}),a=e(B),s=e({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:crmCompany:create"}),m=()=>F,n=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(u,P)=>{const p=i("PtForm");return d(),f(b,null,[l(" \u6DFB\u52A0\u8868\u5355 "),_(p,{form:t.form,formData:t.formData,labelWidth:"80",method:m(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:u.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),E=C(A,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/crm/views/company/admin/CrmCompanyManageAddPage.vue"]]);export{E as default};