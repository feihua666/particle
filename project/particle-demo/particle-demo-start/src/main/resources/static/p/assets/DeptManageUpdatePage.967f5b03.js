import{d as a,a as i,r as o,b as f,o as l,c as _,e as b,f as F,F as g,aJ as h,aK as B,_ as P}from"./index.49d89bd8.js";import{a as v}from"./deptManage.7196eda8.js";import"./treeQueryComps.5854cd36.js";const D=a({name:"AreaManageUpdatePage"}),E=a({...D,props:{deptId:{type:String},masterUserId:{type:String},masterUserName:{type:String}},setup(s){const t=s,e=i({form:{id:t.deptId,version:1},formData:{}}),r=o(v({props:t})),u=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:dept:update"}),n=()=>h,m=()=>B({id:t.deptId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(d,U)=>{const c=f("PtForm");return l(),_(g,null,[b(" \u6DFB\u52A0\u8868\u5355 "),F(c,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:d.$route.meta.formButtonsTeleportProps,inline:"",comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),w=P(E,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dept/views/admin/DeptManageUpdatePage.vue"]]);export{w as default};