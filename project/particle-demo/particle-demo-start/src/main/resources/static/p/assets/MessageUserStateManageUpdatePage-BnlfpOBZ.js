import{u as c,d as i,e as f}from"./messageUserStateManage-D5xB960W.js";import{d as l,a as b,r as s,b as g,o as h,g as S}from"./index-_8rL1G8m.js";const v=l({__name:"MessageUserStateManageUpdatePage",props:{messageUserStateId:{type:String}},setup(o){const t=o,e=b({form:{id:t.messageUserStateId,version:1},formData:{}}),a=s(c),r=s({buttonText:"确认修改",permission:"admin:web:messageUserState:update"}),m=()=>i,n=()=>f({id:t.messageUserStateId}),u=()=>"修改成功，请刷新数据查看";return(p,P)=>{const d=g("PtForm");return h(),S(d,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:n,method:m(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:r.value,buttonsTeleportProps:p.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};