import{u as c,b as d}from"./openplatformDocApiAdminApi-DIFTF2de.js";import{u as f}from"./openplatformDocApiManage-DG_4ThXl.js";import{d as l,a as b,r as e,b as D,o as h,g as A}from"./index-B_FSv1tI.js";import"./openplatformDocCompItem-Bjf0J-QP.js";import"./openplatformDocDirNameAdminApi-sZNYp1ol.js";import"./openplatformDocApiDocTemplateAdminApi-MmnX-4Gj.js";import"./openplatformOpenapiCompItem-Ca8vJa8p.js";const S=l({__name:"OpenplatformDocApiManageUpdatePage",props:{openplatformDocApiId:{type:String}},setup(r){const t=r,o=b({form:{id:t.openplatformDocApiId,version:1},formData:{}}),a=e(f),s=e({buttonText:"确认修改",permission:"admin:web:openplatformDocApi:update"}),m=()=>c,p=()=>d({id:t.openplatformDocApiId}),n=()=>"修改成功，请刷新数据查看";return(i,P)=>{const u=D("PtForm");return h(),A(u,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:p,method:m(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:i.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,3,3,1],comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{S as default};