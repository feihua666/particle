import{d as o,a as c,r as e,b as p,o as f,h as i,aS as d}from"./index.9463e69a.js";import{a as l}from"./deptTreeNameManage.c988c53a.js";const b=o({name:"DeptTreeNameManageAddPage"}),D=o({...b,setup(_){const t=c({form:{},formData:{}}),r=e(l),s=e({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:deptTreeName:create"}),a=()=>d,u=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(m,B)=>{const n=p("PtForm");return f(),i(n,{form:t.form,formData:t.formData,labelWidth:"80",method:a(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:m.$route.meta.formButtonsTeleportProps,inline:"",comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{D as default};