import{d as a,a as i,r as o,b as f,o as l,c as _,e as b,f as F,F as g,aQ as h,aR as T,_ as B}from"./index.49d89bd8.js";import{u as P}from"./deptTreeManage.81d94e60.js";import"./treeQueryComps.5854cd36.js";const v=a({name:"AreaManageUpdatePage"}),D=a({...v,props:{deptTreeId:{type:String}},setup(r){const e=r,t=i({form:{id:e.deptTreeId,version:1},formData:{}}),s=o(P),u=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:deptTree:update"}),n=()=>h,m=()=>T({id:e.deptTreeId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(d,E)=>{const c=f("PtForm");return l(),_(g,null,[b(" \u6DFB\u52A0\u8868\u5355 "),F(c,{form:t.form,formData:t.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:d.$route.meta.formButtonsTeleportProps,inline:"",comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),C=B(D,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dept/views/admin/DeptTreeManageUpdatePage.vue"]]);export{C as default};