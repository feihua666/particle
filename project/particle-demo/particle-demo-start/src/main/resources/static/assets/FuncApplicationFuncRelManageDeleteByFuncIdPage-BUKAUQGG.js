import{d as p,q as l,a as i,r as o,s as d,b as f,o as b,g as F}from"./index-D8BtRcSm.js";import{d as h}from"./funcApplicationFuncRelAdminApi-1odi0gqI.js";const D=p({__name:"FuncApplicationFuncRelManageDeleteByFuncIdPage",props:{...l},setup(s){const r=s,e=i({form:{},formData:{}}),n=o([d({props:r,required:!0})]),a=o({buttonText:"确认",permission:"admin:web:funcApplicationFuncRel:deleteByFuncId"}),m=t=>h({id:t.funcId}),u=()=>"删除成功，请刷新数据查看";return(t,B)=>{const c=f("PtForm");return b(),F(c,{form:e.form,formData:e.formData,labelWidth:"80",method:m,methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:n.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])}}});export{D as default};