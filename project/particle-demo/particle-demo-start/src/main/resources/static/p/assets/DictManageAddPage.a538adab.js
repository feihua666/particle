import{d as o,a as p,r as e,b as i,o as d,c as f,e as l,f as _,F as b,_ as F}from"./index.3ad8a272.js";import{c as B}from"./dictAdminApi.4626ab19.js";import{a as D}from"./dictManage.112b0a7f.js";import"./treeQueryComps.5854cd36.js";const g=o({name:"DictManageAddPage"}),A=o({...g,props:{parentId:{type:String}},setup(r){const t=p({form:{parentId:r.parentId},formData:{}}),s=e(D),a=e({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:dict:create"}),n=()=>B,u=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(m,P)=>{const c=i("PtForm");return d(),f(b,null,[l(" \u6DFB\u52A0\u8868\u5355 "),_(c,{form:t.form,formData:t.formData,labelWidth:"80",method:n(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:a.value,inline:"",buttonsTeleportProps:m.$route.meta.formButtonsTeleportProps,comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),E=F(A,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dict/views/admin/DictManageAddPage.vue"]]);export{E as default};