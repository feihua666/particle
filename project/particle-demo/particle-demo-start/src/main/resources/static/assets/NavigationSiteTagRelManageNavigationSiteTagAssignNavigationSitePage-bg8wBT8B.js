import{q as d,n as p}from"./navigationSiteTagRelAdminApi-BkUhgfac.js";import{r as c,u as l}from"./navigationSiteTagCompItem-du0i2Fph.js";import{l as v}from"./navigationSiteAdminApi-DBfQIG6y.js";import{d as f,a as S,r as i,b as T,o as h,g as b}from"./index-BGwvwH0Y.js";import"./navigationSiteTagAdminApi-bEmDeb6f.js";const A=f({__name:"NavigationSiteTagRelManageNavigationSiteTagAssignNavigationSitePage",props:{...c},setup(r){const n=r,a=S({form:{},formData:{}}),s=i([l({props:n,required:!0}),{field:{name:"checkedNavigationSiteIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"网站",required:!0},compProps:({form:e})=>({dataInitMethod:({param:o})=>{let t=o.navigationSiteTagId;return t?d({id:t}):{data:[]}},dataInitMethodParam:{navigationSiteTagId:e.navigationSiteTagId},dataMethod:v,dataMethodResultHandleConvertToTree:!0,showCheckbox:!0})}}]),m=i({buttonText:"确认",permission:"admin:web:navigationSiteTagRel:navigationSiteTagAssignNavigationSite"}),u=()=>p,g=()=>"分配成功，请刷新数据查看";return(e,o)=>{const t=T("PtForm");return h(),b(t,{form:a.form,formData:a.formData,labelWidth:"80",method:u(),methodSuccess:g,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{A as default};