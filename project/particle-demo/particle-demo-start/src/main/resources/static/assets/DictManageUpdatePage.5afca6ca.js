import{u as p,d as f}from"./dictAdminApi.aa285078.js";import{u as l}from"./dictManage.59530aa4.js";import{d as r,a as b,r as e,b as h,o as F,h as _}from"./index.147a787d.js";import"./treeQueryComps.5854cd36.js";const B=r({name:"DictManageUpdatePage"}),A=r({...B,props:{dictId:{type:String}},setup(s){const t=s,o=b({form:{id:t.dictId,version:1},formData:{}}),a=e(l),u=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:dict:update"}),m=()=>p,n=()=>f({id:t.dictId}),c=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(d,E)=>{const i=h("PtForm");return F(),_(i,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:n,method:m(),methodSuccess:c,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:d.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{A as default};