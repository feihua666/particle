import{m as c,d as p}from"./feedbackManage-DOFtFL_q.js";import{d as l,a as d,r as e,b as i,o as f,g as b}from"./index-_8rL1G8m.js";const v=l({__name:"FeedbackManageManualHandlePage",props:{feedbackId:{type:String}},setup(o){const t=d({form:{id:o.feedbackId,version:1},formData:{}}),a=e(c),s=e({buttonText:"确认处理",permission:"admin:web:feedback:manualHandle"}),r=()=>p,n=()=>"处理成功，请刷新数据查看";return(m,P)=>{const u=i("PtForm");return f(),b(u,{form:t.form,formData:t.formData,labelWidth:"80",method:r(),methodSuccess:n,defaultButtonsShow:"submit,reset",submitAttrs:s.value,buttonsTeleportProps:m.$route.meta.formButtonsTeleportProps,inline:"",layout:[1],comps:a.value},null,8,["form","formData","method","submitAttrs","buttonsTeleportProps","comps"])}}});export{v as default};