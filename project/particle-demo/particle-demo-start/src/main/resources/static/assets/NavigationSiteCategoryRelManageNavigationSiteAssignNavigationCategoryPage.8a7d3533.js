import{a as l,b as p}from"./navigationSiteCategoryRelAdminApi.e9283fcf.js";import{r as g,u as v}from"./navigationSiteCompItem.ef21a718.js";import{l as f}from"./navigationCategoryAdminApi.cb25ccb6.js";import{d as i,a as S,r,b as h,o as C,h as b}from"./index.6cdd7c8b.js";import"./navigationSiteAdminApi.cb7d72e6.js";const y=i({name:"NavigationSiteCategoryRelManageNavigationSiteAssignNavigationCategoryPage"}),A=i({...y,props:{...g},setup(n){const s=n,o=S({form:{},formData:{}}),u=r([v({props:s,required:!0}),{field:{name:"checkedNavigationCategoryIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"\u5BFC\u822A\u5206\u7C7B",required:!0},compProps:({form:e})=>({checkStrictly:!0,dataInitMethod:({param:a})=>{let t=a.navigationSiteId;return t?l({id:t}):{data:[]}},dataInitMethodParam:{navigationSiteId:e.navigationSiteId},dataMethod:f,dataMethodResultHandleConvertToTree:!0,showCheckbox:!0})}}]),m=r({buttonText:"\u786E\u8BA4",permission:"admin:web:navigationSiteCategoryRel:navigationSiteAssignNavigationCategory"}),c=()=>p,d=()=>"\u5206\u914D\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(e,a)=>{const t=h("PtForm");return C(),b(t,{form:o.form,formData:o.formData,labelWidth:"80",method:c(),methodSuccess:d,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:u.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{A as default};