import{d as a,a as p,r as o,b as f,o as l,c as _,e as b,f as F,F as g,_ as h}from"./index.49d89bd8.js";import{u as B,d as D}from"./dictAdminApi.5022534a.js";import{u as P}from"./dictManage.3ec65254.js";import"./treeQueryComps.5854cd36.js";const v=a({name:"DictManageUpdatePage"}),E=a({...v,props:{dictId:{type:String}},setup(s){const t=s,e=p({form:{id:t.dictId,version:1},formData:{}}),r=o(P),u=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:dict:update"}),n=()=>B,m=()=>D({id:t.dictId}),c=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(i,M)=>{const d=f("PtForm");return l(),_(g,null,[b(" \u6DFB\u52A0\u8868\u5355 "),F(d,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:c,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:i.$route.meta.formButtonsTeleportProps,inline:"",comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),I=h(E,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/dict/views/admin/DictManageUpdatePage.vue"]]);export{I as default};