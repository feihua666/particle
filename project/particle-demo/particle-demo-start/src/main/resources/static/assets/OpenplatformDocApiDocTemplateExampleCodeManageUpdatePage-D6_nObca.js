import{u as l,d,e as u}from"./openplatformDocApiDocTemplateExampleCodeManage-DIlRXGhm.js";import{d as f,a as D,r as e,b,o as h,g as T}from"./index-BGwvwH0Y.js";import"./openplatformDocCompItem-DSMbI1-l.js";import"./openplatformDocDirNameAdminApi-DNoibnDF.js";import"./openplatformDocApiAdminApi-D2XlYoOS.js";import"./openplatformDocApiDocTemplateAdminApi-CCoUrFuj.js";const E=f({__name:"OpenplatformDocApiDocTemplateExampleCodeManageUpdatePage",props:{openplatformDocApiDocTemplateExampleCodeId:{type:String}},setup(a){const o=a,t=D({form:{id:o.openplatformDocApiDocTemplateExampleCodeId,version:1},formData:{}}),r=e(l),m=e({buttonText:"确认修改",permission:"admin:web:openplatformDocApiDocTemplateExampleCode:update"}),p=()=>d,s=()=>u({id:o.openplatformDocApiDocTemplateExampleCodeId}),n=()=>"修改成功，请刷新数据查看";return(c,A)=>{const i=b("PtForm");return h(),T(i,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:s,method:p(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,1,1],comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{E as default};