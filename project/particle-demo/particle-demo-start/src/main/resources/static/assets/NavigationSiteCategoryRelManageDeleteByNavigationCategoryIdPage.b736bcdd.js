import{e as p}from"./navigationSiteCategoryRelAdminApi.e9283fcf.js";import{u as c}from"./navigationCategoryCompItem.20edcd76.js";import{d as a,a as d,r as e,b as g,o as l,h as f}from"./index.6cdd7c8b.js";import"./navigationCategoryAdminApi.cb25ccb6.js";const C=a({name:"NavigationSiteCategoryRelManageDeleteByNavigationCategoryIdPage"}),h=a({...C,props:{navigationCategoryId:{type:String}},setup(r){const o=d({form:{navigationCategoryId:r.navigationCategoryId},formData:{}}),n=e([c({fieldName:"navigationCategoryId",label:"\u5BFC\u822A\u5206\u7C7B",required:!0})]),s=e({buttonText:"\u786E\u8BA4",permission:"admin:web:navigationSiteCategoryRel:deleteByNavigationCategoryId"}),i=t=>p({id:t.navigationCategoryId}),u=()=>"\u5220\u9664\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(t,y)=>{const m=g("PtForm");return l(),f(m,{form:o.form,formData:o.formData,labelWidth:"80",method:i,methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:n.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","comps"])}}});export{h as default};