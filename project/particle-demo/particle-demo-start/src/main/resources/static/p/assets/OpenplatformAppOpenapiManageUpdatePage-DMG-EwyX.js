import{d,e as c,f}from"./openplatformAppOpenapiManage-C8CcglWr.js";import{d as l,a as b,r as e,b as h,o as A,g as P}from"./index-_8rL1G8m.js";import"./openplatformAppCompItem-DhvLbyKP.js";import"./openplatformAppAdminApi-BfcI6YuV.js";import"./openplatformOpenapiCompItem-C_C29-BD.js";import"./openplatformOpenapiFeeCompItem-CDoTJAzK.js";import"./openplatformOpenapiFeeAdminApi-CZIOoqKg.js";import"./openplatformProviderCompItem-w-CMeKd4.js";import"./openplatformProviderAdminApi-CWwCkmnE.js";import"./openplatformOpenapiLimitRuleCompItem-3fhm1zVA.js";import"./openplatformOpenapiLimitRuleAdminApi-BFr753sj.js";const U=l({__name:"OpenplatformAppOpenapiManageUpdatePage",props:{openplatformAppOpenapiId:{type:String}},setup(p){const t=p,o=b({form:{id:t.openplatformAppOpenapiId,version:1},formData:{}}),r=e(d({props:t})),a=e({buttonText:"确认修改",permission:"admin:web:openplatformAppOpenapi:update"}),s=()=>c,m=()=>f({id:t.openplatformAppOpenapiId}),n=()=>"修改成功，请刷新数据查看";return(i,_)=>{const u=h("PtForm");return A(),P(u,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:m,method:s(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:i.$route.meta.formButtonsTeleportProps,inline:"",comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{U as default};