import{u as l,d}from"./roleDataScopeRelAdminApi.22f51fa7.js";import{a as f}from"./roleDataScopeRelManage.bee57c48.js";import{r as b}from"./roleCompItem.3dc3c5ec.js";import{d as r,a as h,r as e,b as D,o as F,h as S}from"./index.d93ffe12.js";import"./dataconstraintCompItem.2d676559.js";import"./dataObjectAdminApi.33866185.js";import"./dataScopeAdminApi.d7064fe9.js";import"./roleAdminApi.9d30e9b4.js";const _=r({name:"RoleDataScopeRelManageUpdatePage"}),M=r({..._,props:{...b,roleDataScopeRelId:{type:String}},setup(a){const t=a,o=h({form:{id:t.roleDataScopeRelId,version:1},formData:{}}),s=e(f({props:t})),m=e({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:roleDataScopeRel:update"}),u=()=>l,p=()=>d({id:t.roleDataScopeRelId}),n=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(c,B)=>{const i=D("PtForm");return F(),S(i,{form:o.form,formData:o.formData,labelWidth:"80",dataMethod:p,method:u(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{M as default};