import{d as s,a as i,r as o,u as l,b as f,o as b,h,l as F,m as _}from"./index.147a787d.js";const B=s({name:"UserManageUpdatePage"}),v=s({...B,props:{userId:{type:String}},setup(r){const t=r,e=i({form:{id:t.userId,version:1},formData:{}}),u=o(l),a=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:user:update"}),n=()=>F,m=()=>_({id:t.userId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,E)=>{const d=f("PtForm");return b(),h(d,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:a.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[1],comps:u.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};