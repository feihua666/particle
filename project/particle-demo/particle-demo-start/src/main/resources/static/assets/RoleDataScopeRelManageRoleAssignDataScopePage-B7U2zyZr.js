import{q as h,r as b}from"./roleDataScopeRelAdminApi-DJ9Ixxdb.js";import{r as I,a as D}from"./roleCompItem-C-i3agJa.js";import{l as S}from"./dataScopeAdminApi-C5BLIV3n.js";import{d as P,a as g,r as l,b as R,o as j,g as v}from"./index-BGwvwH0Y.js";import"./roleAdminApi-BQN1KCWT.js";const T=P({__name:"RoleDataScopeRelManageRoleAssignDataScopePage",props:{...I},setup(m){const n=m,s=g({form:{checkedDataScopeIdAgainstDataObjectIds:{}},formData:{}}),c=l([D({props:n,required:!0}),{field:{name:"checkedDataScopeIds",value:[]},element:{comp:"PtTree",formItemProps:{label:"数据范围",required:!0},compProps:({form:d})=>({dataInitMethod:({param:a})=>{let o=a.roleId;return o?h({id:o}):{data:[]}},dataInitMethodParam:{roleId:d.roleId},dataMethod:({param:a})=>S(a).then(o=>{let t=o.data.data,r=[];for(let e=0;e<t.length;e++)s.form.checkedDataScopeIdAgainstDataObjectIds[t[e].id]=t[e].dataObjectId,t[e].parentId=t[e].dataObjectId,r.some(f=>f.id==t[e].dataObjectId)||r.push({id:t[e].dataObjectId,name:t[e].dataObjectName,isDisabled:!0});for(let e=0;e<r.length;e++)t.push(r[e]);return Promise.resolve(o)}),dataMethodResultHandleConvertToTree:!0,showCheckbox:!0,modelValueIncludeHalfCheckedKeys:!1,modelValueIncludeLeafOnly:!0})}}]),u=l({buttonText:"确认",permission:"admin:web:roleDataScopeRel:roleAssignDataScope"}),p=()=>b,i=()=>"分配成功，请刷新数据查看";return(d,a)=>{const o=R("PtForm");return j(),v(o,{form:s.form,formData:s.formData,labelWidth:"80",method:p(),methodSuccess:i,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:d.$route.meta.formButtonsTeleportProps,inline:"",layout:1,comps:c.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{T as default};