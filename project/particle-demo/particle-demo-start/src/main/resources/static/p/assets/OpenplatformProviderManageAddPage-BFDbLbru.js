import{c as p}from"./openplatformProviderAdminApi-B07nyAAe.js";import{a as c}from"./openplatformProviderManage-Cjxd3ofz.js";import{d as u,a as i,r as o,b as f,o as d,g as l}from"./index-B_FSv1tI.js";import"./dataqueryProviderCompItem-BPLC2Vf6.js";import"./dataQueryProviderAdminApi-CzgvZs1y.js";const A=u({__name:"OpenplatformProviderManageAddPage",setup(b){const t=i({form:{},formData:{}}),e=o(c),r=o({buttonText:"确认添加",permission:"admin:web:openplatformProvider:create"}),s=()=>p,m=()=>"添加成功，请刷新数据查看";return(a,P)=>{const n=f("PtForm");return d(),l(n,{form:t.form,formData:t.formData,labelWidth:"80",method:s(),methodSuccess:m,defaultButtonsShow:"submit,reset",submitAttrs:r.value,buttonsTeleportProps:a.$route.meta.formButtonsTeleportProps,inline:"",comps:e.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{A as default};