import{u as i,d as l}from"./openplatformDocApiDocTemplateAdminApi-KL8o9Ln5.js";import{u as d}from"./openplatformDocApiDocTemplateManage-Dn06Tpxk.js";import{d as f,a as D,r as e,b,o as h,g as T}from"./index-_8rL1G8m.js";const g=f({__name:"OpenplatformDocApiDocTemplateManageUpdatePage",props:{openplatformDocApiDocTemplateId:{type:String}},setup(r){const t=r,o=D({form:{id:t.openplatformDocApiDocTemplateId,version:1},formData:{}}),a=e(d),s=e({buttonText:"确认修改",permission:"admin:web:openplatformDocApiDocTemplate:update"}),m=()=>i,p=()=>l({id:t.openplatformDocApiDocTemplateId}),n=()=>"修改成功，请刷新数据查看";return(c,A)=>{const u=b("PtForm");return h(),T(u,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:p,method:m(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,3,[8,8],[8,8],1],comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{g as default};