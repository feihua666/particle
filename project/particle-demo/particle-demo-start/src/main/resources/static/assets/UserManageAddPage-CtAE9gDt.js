import{c as m}from"./userAdminApi-frgTu-Du.js";import{a as p}from"./userManage-BNavWI8S.js";import{d as u,a as c,r as o,b as d,o as f,g as l,k as b,j as _}from"./index-D8BtRcSm.js";import"./userCompItem-BbD-YbDP.js";import"./deptCompItem-CnfCti79.js";import"./deptTreeNameAdminApi-B8sFHLzb.js";const B=u({__name:"UserManageAddPage",setup(h){const e=c({form:{},formData:{}}),r=o(p.filter(t=>!!t)),s=o({buttonText:"确认添加",permission:"admin:web:user:create"}),a=t=>(t.identifiers=[],t.identifiers.push({identifier:t.identifier,identityTypeDictId:t.identityTypeDictId}),m(t)),n=()=>"添加成功，请刷新数据查看";return(t,D)=>{const i=d("PtForm");return f(),l(i,{form:e.form,formData:e.formData,labelWidth:"80",method:a,methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:t.$route.meta.formButtonsTeleportProps,inline:"",layout:[1,3,3,3,3,b(_)("dept")?3:2],comps:r.value},null,8,["form","formData","submitAttrs","buttonsTeleportProps","layout","comps"])}}});export{B as default};