import{a as p,b as c}from"./roleUserRelAdminApi-qlPUxbNb.js";import{r as d,a as f}from"./roleCompItem-BusJC2tO.js";import{l as b}from"./userAdminApi-C_soRCCg.js";import{d as h,a as I,r as s,b as P,o as R,g as U}from"./index-B_FSv1tI.js";import"./roleAdminApi-DEKs4yJg.js";const k=h({__name:"RoleUserRelManageRoleAssignUserPage",props:{...d},setup(a){const m=a,o=I({form:{},formData:{}}),n=s([f({props:m,required:!0}),{field:{name:"checkedUserIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"用户",required:!0},compProps:({form:t})=>({dataInitMethod:({param:r})=>{let e=r.roleId;return e?p({id:e}):{data:[]}},dataInitMethodParam:{roleId:t.roleId},dataMethod:b,showCheckbox:!0,props:{label:"nickname"}})}}]),l=s({buttonText:"确认",permission:"admin:web:roleUserRel:roleAssignUser"}),u=()=>c,i=()=>"分配成功，请刷新数据查看";return(t,r)=>{const e=P("PtForm");return R(),U(e,{form:o.form,formData:o.formData,labelWidth:"80",method:u(),methodSuccess:i,defaultButtonsShow:"submit,reset",submitAttrs:l.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:n.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{k as default};