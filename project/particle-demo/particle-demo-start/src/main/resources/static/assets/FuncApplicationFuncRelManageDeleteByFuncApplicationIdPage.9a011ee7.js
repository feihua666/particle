import{c as i}from"./funcApplicationFuncRelAdminApi.1d56045f.js";import{a as l}from"./funcApplicationCompItem.36befd72.js";import{r as f}from"./funcCompItem.1b9df3db.js";import{d as r,a as d,r as e,b as F,o as b,h as A}from"./index.147a787d.js";import"./funcApplicationAdminApi.16d6129a.js";import"./funcAdminApi.7f00bf0e.js";import"./funcGroupAdminApi.dec311b2.js";const B=r({name:"FuncApplicationFuncRelManageDeleteByFuncApplicationIdPage"}),y=r({...B,props:{...f},setup(u){const n=u,o=d({form:{},formData:{}}),s=e([l({props:n,required:!0})]),a=e({buttonText:"\u786E\u8BA4",permission:"admin:web:funcApplicationFuncRel:deleteByFuncApplicationId"}),m=t=>i({id:t.funcApplicationId}),c=()=>"\u5220\u9664\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(t,_)=>{const p=F("PtForm");return b(),A(p,{form:o.form,formData:o.formData,labelWidth:"80",method:m,methodSuccess:c,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:s.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])}}});export{y as default};