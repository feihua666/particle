import{b as d,d as f}from"./openplatformDocCompItem.62e8b524.js";import{u as l}from"./openplatformDocDirManage.0b434d86.js";import{d as r,a as D,r as e,b,o as h,h as F}from"./index.d93ffe12.js";import"./openplatformDocDirNameAdminApi.b28fe086.js";import"./openplatformDocApiDocTemplateAdminApi.e621ed7e.js";import"./treeQueryComps.5854cd36.js";const _=r({name:"OpenplatformDocDirManageUpdatePage"}),I=r({..._,props:{openplatformDocDirId:{type:String}},setup(s){const t=s,o=D({form:{id:t.openplatformDocDirId,version:1},formData:{}}),a=e(l),m=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:openplatformDocDir:update"}),u=()=>d,n=()=>f({id:t.openplatformDocDirId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(i,B)=>{const c=b("PtForm");return h(),F(c,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:n,method:u(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:i.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{I as default};