import{u as l,d,e as u}from"./openplatformDocApiDocTemplateExampleCodeManage-B5-8PrVa.js";import{d as f,a as D,r as e,b,o as h,g as T}from"./index-B_FSv1tI.js";import"./openplatformDocCompItem-Bjf0J-QP.js";import"./openplatformDocDirNameAdminApi-sZNYp1ol.js";import"./openplatformDocApiAdminApi-DIFTF2de.js";import"./openplatformDocApiDocTemplateAdminApi-MmnX-4Gj.js";const E=f({__name:"OpenplatformDocApiDocTemplateExampleCodeManageUpdatePage",props:{openplatformDocApiDocTemplateExampleCodeId:{type:String}},setup(a){const o=a,t=D({form:{id:o.openplatformDocApiDocTemplateExampleCodeId,version:1},formData:{}}),r=e(l),m=e({buttonText:"确认修改",permission:"admin:web:openplatformDocApiDocTemplateExampleCode:update"}),p=()=>d,s=()=>u({id:o.openplatformDocApiDocTemplateExampleCodeId}),n=()=>"修改成功，请刷新数据查看";return(c,A)=>{const i=b("PtForm");return h(),T(i,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:s,method:p(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,1,1],comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{E as default};
