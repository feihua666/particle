import{u as l,d}from"./openplatformDocApiDocTemplateAdminApi.15074b53.js";import{u as f}from"./openplatformDocApiDocTemplateManage.fb7afcab.js";import{d as a,a as D,r as e,b,o as h,h as F}from"./index.858f5c96.js";const A=a({name:"OpenplatformDocApiDocTemplateManageUpdatePage"}),P=a({...A,props:{openplatformDocApiDocTemplateId:{type:String}},setup(r){const t=r,o=D({form:{id:t.openplatformDocApiDocTemplateId,version:1},formData:{}}),s=e(f),m=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:openplatformDocApiDocTemplate:update"}),p=()=>l,u=()=>d({id:t.openplatformDocApiDocTemplateId}),n=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,T)=>{const i=b("PtForm");return h(),F(i,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:u,method:p(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,3,[8],1],comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{P as default};