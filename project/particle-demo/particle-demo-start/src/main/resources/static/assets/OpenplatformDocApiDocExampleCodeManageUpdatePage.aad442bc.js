import{u as i,d as l,e as f}from"./openplatformDocApiDocExampleCodeManage.8f4ad197.js";import{d as r,a as D,r as e,b,o as E,h}from"./index.bd091b8a.js";import"./openplatformDocCompItem.4d4052be.js";import"./openplatformDocDirNameAdminApi.76a93caa.js";import"./openplatformDocApiDocTemplateAdminApi.526aa2ef.js";const F=r({name:"OpenplatformDocApiDocExampleCodeManageUpdatePage"}),v=r({...F,props:{openplatformDocApiDocExampleCodeId:{type:String}},setup(a){const o=a,t=D({form:{id:o.openplatformDocApiDocExampleCodeId,version:1},formData:{}}),s=e(i),m=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:openplatformDocApiDocExampleCode:update"}),p=()=>l,u=()=>f({id:o.openplatformDocApiDocExampleCodeId}),n=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,A)=>{const d=b("PtForm");return E(),h(d,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:u,method:p(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,1,1],comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};