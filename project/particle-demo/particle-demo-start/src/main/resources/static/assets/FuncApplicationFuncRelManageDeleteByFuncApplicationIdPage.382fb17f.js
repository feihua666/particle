import{e as i}from"./funcApplicationFuncRelAdminApi.1fef15ea.js";import{a as l}from"./funcApplicationCompItem.d985f350.js";import{d as r,K as f,a as d,r as o,b as F,o as b,h as A}from"./index.30ac3b34.js";import"./funcApplicationAdminApi.1155d7fc.js";const B=r({name:"FuncApplicationFuncRelManageDeleteByFuncApplicationIdPage"}),C=r({...B,props:{...f},setup(u){const n=u,e=d({form:{},formData:{}}),s=o([l({props:n,required:!0})]),a=o({buttonText:"\u786E\u8BA4",permission:"admin:web:funcApplicationFuncRel:deleteByFuncApplicationId"}),c=t=>i({id:t.funcApplicationId}),m=()=>"\u5220\u9664\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(t,_)=>{const p=F("PtForm");return b(),A(p,{form:e.form,formData:e.formData,labelWidth:"80",method:c,methodSuccess:m,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:s.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])}}});export{C as default};