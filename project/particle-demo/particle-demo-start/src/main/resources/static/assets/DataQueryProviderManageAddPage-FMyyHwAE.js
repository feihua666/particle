import{c as u}from"./dataQueryProviderAdminApi-CK2eKl63.js";import{a as c}from"./dataQueryProviderManage-Cn6PIMsA.js";import{d as i,a as p,r as o,b as d,o as f,g as l}from"./index-D8BtRcSm.js";const D=i({__name:"DataQueryProviderManageAddPage",setup(b){const t=p({form:{},formData:{}}),e=o(c),r=o({buttonText:"确认添加",permission:"admin:web:dataQueryProvider:create"}),s=()=>u,a=()=>"添加成功，请刷新数据查看";return(m,P)=>{const n=d("PtForm");return f(),l(n,{form:t.form,formData:t.formData,labelWidth:"80",method:s(),methodSuccess:a,defaultButtonsShow:"submit,reset",submitAttrs:r.value,buttonsTeleportProps:m.$route.meta.formButtonsTeleportProps,inline:"",comps:e.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{D as default};