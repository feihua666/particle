import{b as d,d as c,e as f}from"./tenantUserManage-CKm2J8Nf.js";import{d as l,a as b,r as o,b as h,o as U,g}from"./index-D8BtRcSm.js";import"./userCompItem-BbD-YbDP.js";import"./userAdminApi-frgTu-Du.js";const I=l({__name:"TenantUserManageUpdatePage",props:{tenantUserId:{type:String},userId:{type:String},userNickname:{type:String}},setup(r){const t=r,e=b({form:{id:t.tenantUserId,version:1},formData:{}}),s=o(d({props:t})),a=o({buttonText:"确认修改",permission:"admin:web:tenantUser:update"}),n=()=>c,m=()=>f({id:t.tenantUserId}),p=()=>"修改成功，请刷新数据查看";return(u,P)=>{const i=h("PtForm");return U(),g(i,{form:e.form,formData:e.formData,labelWidth:"100",dataMethod:m,method:n(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:u.$route.meta.formButtonsTeleportProps,inline:"",comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{I as default};