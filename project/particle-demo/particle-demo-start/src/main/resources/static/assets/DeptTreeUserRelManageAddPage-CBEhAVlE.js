import{b as c,c as u}from"./deptTreeUserRelManage-BotUR5wC.js";import{d as p,a as i,r as e,b as f,o as l,g as d}from"./index-BGwvwH0Y.js";const D=p({__name:"DeptTreeUserRelManageAddPage",setup(b){const t=i({form:{},formData:{}}),o=e(c),r=e({buttonText:"确认添加",permission:"admin:web:deptTreeUserRel:create"}),s=()=>u,a=()=>"添加成功，请刷新数据查看";return(m,_)=>{const n=f("PtForm");return l(),d(n,{form:t.form,formData:t.formData,labelWidth:"80",method:s(),methodSuccess:a,defaultButtonsShow:"submit,reset",submitAttrs:r.value,buttonsTeleportProps:m.$route.meta.formButtonsTeleportProps,inline:"",comps:o.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{D as default};