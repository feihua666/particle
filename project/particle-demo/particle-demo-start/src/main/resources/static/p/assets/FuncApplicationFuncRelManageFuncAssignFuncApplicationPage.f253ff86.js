import{d as a,M as l,a as d,r as u,N as f,b as F,o as A,c as b,e as h,f as _,F as g,_ as P}from"./index.49d89bd8.js";import{l as I}from"./funcApplicationAdminApi.58b01f31.js";import{a as B,b as M}from"./funcApplicationFuncRelAdminApi.76fa9390.js";const v=a({name:"FuncApplicationFuncRelManageFuncAssignFuncApplicationPage"}),D=a({...v,props:{...l},setup(s){const c=s,o=d({form:{},formData:{}}),r=u([f({props:c,required:!0}),{field:{name:"checkedFuncApplicationIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"\u529F\u80FD\u5E94\u7528",required:!0},compProps:({form:t})=>({dataInitMethod:({param:n})=>{let e=n.funcId;return e?B({id:e}):{data:[]}},dataInitMethodParam:{funcId:t.funcId},dataMethod:I,showCheckbox:!0})}}]),i=u({buttonText:"\u786E\u8BA4",permission:"admin:web:funcApplicationFuncRel:funcAssignFuncApplication"}),m=()=>M,p=()=>"\u5206\u914D\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(t,n)=>{const e=F("PtForm");return A(),b(g,null,[h(" \u6DFB\u52A0\u8868\u5355 "),_(e,{form:o.form,formData:o.formData,labelWidth:"80",method:m(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:i.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),R=P(D,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/func/views/admin/FuncApplicationFuncRelManageFuncAssignFuncApplicationPage.vue"]]);export{R as default};