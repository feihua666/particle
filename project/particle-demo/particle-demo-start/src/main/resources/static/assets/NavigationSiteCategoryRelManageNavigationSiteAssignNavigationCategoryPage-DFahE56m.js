import{a as d,b as l}from"./navigationSiteCategoryRelAdminApi-BT3wKf4P.js";import{r as p,u as g}from"./navigationSiteCompItem-BLk12lju.js";import{l as v}from"./navigationCategoryAdminApi-CHybieh4.js";import{d as f,a as S,r,b as h,o as b,g as C}from"./index-BGwvwH0Y.js";import"./navigationSiteAdminApi-DBfQIG6y.js";const T=f({__name:"NavigationSiteCategoryRelManageNavigationSiteAssignNavigationCategoryPage",props:{...p},setup(i){const n=i,o=S({form:{},formData:{}}),s=r([g({props:n,required:!0}),{field:{name:"checkedNavigationCategoryIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"导航分类",required:!0},compProps:({form:e})=>({checkStrictly:!0,dataInitMethod:({param:a})=>{let t=a.navigationSiteId;return t?d({id:t}):{data:[]}},dataInitMethodParam:{navigationSiteId:e.navigationSiteId},dataMethod:v,dataMethodResultHandleConvertToTree:!0,showCheckbox:!0})}}]),m=r({buttonText:"确认",permission:"admin:web:navigationSiteCategoryRel:navigationSiteAssignNavigationCategory"}),u=()=>l,c=()=>"分配成功，请刷新数据查看";return(e,a)=>{const t=h("PtForm");return b(),C(t,{form:o.form,formData:o.formData,labelWidth:"80",method:u(),methodSuccess:c,defaultButtonsShow:"submit,reset",submitAttrs:m.value,buttonsTeleportProps:e.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{T as default};