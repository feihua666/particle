import{u as i,d as f,e as l}from"./openplatformDocApiDocResponseCodeManage.292b8f26.js";import{d as s,a as D,r as e,b,o as h,h as F}from"./index.bd091b8a.js";import"./openplatformDocCompItem.4d4052be.js";import"./openplatformDocDirNameAdminApi.76a93caa.js";import"./openplatformDocApiDocTemplateAdminApi.526aa2ef.js";const A=s({name:"OpenplatformDocApiDocResponseCodeManageUpdatePage"}),R=s({...A,props:{openplatformDocApiDocResponseCodeId:{type:String}},setup(r){const o=r,t=D({form:{id:o.openplatformDocApiDocResponseCodeId,version:1},formData:{}}),a=e(i),p=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:openplatformDocApiDocResponseCode:update"}),n=()=>f,m=()=>l({id:o.openplatformDocApiDocResponseCodeId}),u=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,C)=>{const d=b("PtForm");return h(),F(d,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:p.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{R as default};