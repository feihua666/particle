import{B as l,C as d}from"./openplatformDocCompItem-Bjf0J-QP.js";import{u}from"./openplatformDocApiDocTemplateParamFieldManage-BoP_bJFU.js";import{d as f,a as D,r as e,b,o as P,g as h}from"./index-B_FSv1tI.js";import"./openplatformDocDirNameAdminApi-sZNYp1ol.js";import"./openplatformDocApiAdminApi-DIFTF2de.js";import"./openplatformDocApiDocTemplateAdminApi-MmnX-4Gj.js";import"./treeQueryComps-CWIG2rsy.js";const I=f({__name:"OpenplatformDocApiDocTemplateParamFieldManageUpdatePage",props:{openplatformDocApiDocTemplateParamFieldId:{type:String}},setup(r){const t=r,o=D({form:{id:t.openplatformDocApiDocTemplateParamFieldId,version:1},formData:{}}),a=e(u),m=e({buttonText:"确认修改",permission:"admin:web:openplatformDocApiDocTemplateParamField:update"}),p=()=>l,s=()=>d({id:t.openplatformDocApiDocTemplateParamFieldId}),n=()=>"修改成功，请刷新数据查看";return(i,F)=>{const c=b("PtForm");return P(),h(c,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:s,method:p(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:i.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{I as default};