import{u as p,d as l}from"./lowcodeModelAdminApi-C0eGQ532.js";import{u as i}from"./lowcodeModelManage-CS32E_iQ.js";import{d as f,a as b,r as e,b as h,o as M,g as w}from"./index-_8rL1G8m.js";const D=f({__name:"LowcodeModelManageUpdatePage",props:{lowcodeModelId:{type:String}},setup(r){const o=r,t=b({form:{id:o.lowcodeModelId,version:1},formData:{}}),s=e(i),a=e({buttonText:"确认修改",permission:"admin:web:lowcodeModel:update"}),m=()=>p,n=()=>l({id:o.lowcodeModelId}),d=()=>"修改成功，请刷新数据查看";return(u,P)=>{const c=h("PtForm");return M(),w(c,{form:t.form,formData:t.formData,labelWidth:"120",dataMethod:n,method:m(),methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:u.$route.meta.formButtonsTeleportProps,inline:"",layout:[2,2,2,2,1],comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{D as default};