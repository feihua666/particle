import{d as u,a as i,r as o,b as f,o as l,c as F,e as _,f as b,F as g,ao as h,ap as B,_ as P}from"./index.3ad8a272.js";import{u as v}from"./funcGroupManage.8cbb0a00.js";const E=u({name:"FuncGroupManageUpdatePage"}),G=u({...E,props:{funcGroupId:{type:String}},setup(a){const t=a,e=i({form:{id:t.funcGroupId,version:1},formData:{}}),r=o(v),s=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:funcGroup:update"}),n=()=>h,m=()=>B({id:t.funcGroupId}),c=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(p,D)=>{const d=f("PtForm");return l(),F(g,null,[_(" \u6DFB\u52A0\u8868\u5355 "),b(d,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:c,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:p.$route.meta.formButtonsTeleportProps,inline:"",comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),A=P(G,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/func/views/admin/FuncGroupManageUpdatePage.vue"]]);export{A as default};