import{a as u,c}from"./tenantUserManage-CKm2J8Nf.js";import{d as p,a as i,r as o,b as d,o as f,g as l}from"./index-D8BtRcSm.js";import"./userCompItem-BbD-YbDP.js";import"./userAdminApi-frgTu-Du.js";const D=p({__name:"TenantUserManageAddPage",setup(b){const t=i({form:{},formData:{}}),e=o(u({props:{},isForAdd:!0})),r=o({buttonText:"确认添加",permission:"admin:web:tenantUser:create"}),s=()=>c,a=()=>"添加成功，请刷新数据查看";return(m,_)=>{const n=d("PtForm");return f(),l(n,{form:t.form,formData:t.formData,labelWidth:"100",method:s(),methodSuccess:a,defaultButtonsShow:"submit,reset",submitAttrs:r.value,buttonsTeleportProps:m.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,3,3,[8,8]],comps:e.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{D as default};