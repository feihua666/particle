import{a as c,d as f}from"./usageCountCompItem.7cb65682.js";import{a as l}from"./usageCountDefineManage.66948c38.js";import{d as s,a as b,r as o,b as h,o as F,h as D}from"./index.bd091b8a.js";import"./treeQueryComps.5854cd36.js";const _=s({name:"AreaManageUpdatePage"}),v=s({..._,props:{usageCountDefineId:{type:String}},setup(r){const e=r,t=b({form:{id:e.usageCountDefineId,version:1},formData:{}}),a=o(l({form:t.form})),u=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:usageCountDefine:update"}),n=()=>c,m=()=>f({id:e.usageCountDefineId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(i,g)=>{const d=h("PtForm");return F(),D(d,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:i.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};