import{d as o,a as p,r as t,b as d,o as l,c as i,e as f,f as g,F as _,_ as b}from"./index.49d89bd8.js";import{c as w}from"./lowcodeSegmentTemplateAdminApi.63a8a06e.js";import{a as F}from"./lowcodeSegmentTemplateManage.08154acc.js";import"./treeQueryComps.5854cd36.js";const B=o({name:"LowcodeSegmentTemplateManageAddPage"}),A=o({...B,props:{parentLowcodeSegmentTemplateId:{type:String}},setup(a){const e=p({form:{parentId:a.parentLowcodeSegmentTemplateId},formData:{}}),r=t(F),s=t({buttonText:"\u786E\u8BA4\u6DFB\u52A0",permission:"admin:web:lowcodeSegmentTemplate:create"}),m=()=>w,n=()=>"\u6DFB\u52A0\u6210\u529F\uFF0C\u8BF7\u5237\u65B0\u6570\u636E\u67E5\u770B";return(u,T)=>{const c=d("PtForm");return l(),i(_,null,[f(" \u6DFB\u52A0\u8868\u5355 "),g(c,{form:e.form,formData:e.formData,labelWidth:"100",method:m(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:u.$route.meta.formButtonsTeleportProps,inline:"",layout:[2,2,1,1,1,1,1,1,1,1],comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])],2112)}}}),C=b(A,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/lowcode/views/generator/admin/LowcodeSegmentTemplateManageAddPage.vue"]]);export{C as default};