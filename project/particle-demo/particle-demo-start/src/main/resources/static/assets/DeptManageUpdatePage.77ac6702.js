import{b as i,d as f,e as l}from"./deptManage.46d91978.js";import{d as r,a as b,r as o,b as h,o as F,h as _}from"./index.147a787d.js";import"./treeQueryComps.5854cd36.js";import"./deptCompItem.9d9dca33.js";const B=r({name:"AreaManageUpdatePage"}),g=r({...B,props:{deptId:{type:String}},setup(s){const t=s,e=b({form:{id:t.deptId,version:1},formData:{}}),a=o(i({props:{}})),u=o({buttonText:"\u786E\u8BA4\u4FEE\u6539",permission:"admin:web:dept:update"}),m=()=>f,n=()=>l({id:t.deptId}),p=()=>"\u4FEE\u6539\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(d,E)=>{const c=h("PtForm");return F(),_(c,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:n,method:m(),methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:u.value,buttonsTeleportProps:d.$route.meta.formButtonsTeleportProps,inline:"",comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{g as default};