import{p as n}from"./navigationSiteTagAdminApi-bEmDeb6f.js";const g={navigationSiteTagId:{type:String},navigationSiteTagName:String},m=({props:a,required:i})=>({field:{name:"navigationSiteTagId",value:a.navigationSiteTagId},element:{comp:"PtSelect",formItemProps:{label:"网站标签",required:i},compProps:()=>{let e=!!(a.navigationSiteTagId&&a.navigationSiteTagName);return{placeholder:"输入网站标签名称搜索",disabled:e,dataMethod:()=>e?{data:[{id:a.navigationSiteTagId,name:a.navigationSiteTagName}]}:{data:[]},remote:!e,remoteMethod:t=>t?n({name:t}):{data:[]},props:{label:"name"}}}}});export{g as r,m as u};