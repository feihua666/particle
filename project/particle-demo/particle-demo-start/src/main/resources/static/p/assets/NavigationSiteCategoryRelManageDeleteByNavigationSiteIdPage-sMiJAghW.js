import{f as p}from"./navigationSiteCategoryRelAdminApi-CsH5Wemy.js";import{r as c,u as l}from"./navigationSiteCompItem-CSKfKXyO.js";import{d as f,a as d,r as o,b as g,o as v,g as S}from"./index-B_FSv1tI.js";import"./navigationSiteAdminApi-YNDYxchn.js";const y=f({__name:"NavigationSiteCategoryRelManageDeleteByNavigationSiteIdPage",props:{...c},setup(a){const r=a,e=d({form:{},formData:{}}),s=o([l({props:r,required:!0})]),i=o({buttonText:"确认",permission:"admin:web:navigationSiteCategoryRel:deleteByNavigationSiteId"}),n=t=>p({id:t.navigationSiteId}),m=()=>"删除成功，请刷新数据查看";return(t,b)=>{const u=g("PtForm");return v(),S(u,{form:e.form,formData:e.formData,labelWidth:"80",method:n,methodSuccess:m,defaultButtonsShow:"submit,reset",submitAttrs:i.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:s.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])}}});export{y as default};