import{u as p,d,e as f}from"./trackingPageManage-BdJF6DFg.js";import{d as l,a as g,r as o,b,o as P,g as h}from"./index-BGwvwH0Y.js";const D=l({__name:"TrackingPageManageUpdatePage",props:{trackingPageId:{type:String}},setup(a){const t=a,e=g({form:{id:t.trackingPageId,version:1},formData:{}}),r=o(p),s=o({buttonText:"确认修改",permission:"admin:web:trackingPage:update"}),n=()=>d,m=()=>f({id:t.trackingPageId}),u=()=>"修改成功，请刷新数据查看";return(c,k)=>{const i=b("PtForm");return P(),h(i,{form:e.form,formData:e.formData,labelWidth:"80",dataMethod:m,method:n(),methodSuccess:u,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:c.$route.meta.formButtonsTeleportProps,inline:"",layout:[3,1,1,1],comps:r.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{D as default};