import{c}from"./dictAdminApi.87165c82.js";import{a as i}from"./dictManage.999c6dc4.js";import{d as e,a as d,r as o,b as f,o as l,h as b}from"./index.858f5c96.js";import"./treeQueryComps.5854cd36.js";const B=e({name:"DictManageAddPage"}),v=e({...B,props:{parentId:{type:String}},setup(r){const t=d({form:{parentId:r.parentId},formData:{}}),s=o(i),a=o({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:dict:create"}),m=()=>c,u=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(n,F)=>{const p=f("PtForm");return l(),b(p,{form:t.form,formData:t.formData,labelWidth:"80",method:m(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:a.value,inline:"",buttonsTeleportProps:n.$route.meta.formButtonsTeleportProps,comps:s.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};