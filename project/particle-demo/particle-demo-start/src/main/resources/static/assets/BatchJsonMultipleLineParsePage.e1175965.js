import{p as d}from"./toolsFrontApi.4852075a.js";import{d as n,a as f,r,b as C,o as F,h as B}from"./index.6cdd7c8b.js";const E=n({name:"BatchJsonMultipleLineParsePage"}),_=n({...E,setup(b){const a=f({form:{},formData:{}}),l=r([{field:{name:"jsonStrLines"},element:{comp:"el-input",formItemProps:{label:"json\u591A\u884C\u6587\u672C",required:!0,tips:"\u6CE8\u610F\uFF1A\u6BCF\u4E00\u884C\u5E94\u8BE5\u662F\u4E00\u4E2A\u5B8C\u6574\u7684json\u5B57\u7B26\u4E32"},compProps:{clearable:!0,rows:10,type:"textarea"}}},{field:{name:"handleLogical",value:"useBackendConvert"},element:{comp:"PtRadioGroup",formItemProps:{label:"\u5904\u7406\u903B\u8F91",required:!0,tips:"\u540E\u7AEF\u8F6C\u4E3A\u65B9\u4FBF\u5C06bigint\u5927\u6570\u503C\u8F6C\u4E3A\u5B57\u7B26\u4E32"},compProps:{options:[{id:"useBackendConvert",name:"\u4F7F\u7528\u540E\u7AEF\u63A5\u53E3\u8F6C\u6362"}]}}},{field:{name:"text"},element:{comp:"el-input",formItemProps:{label:"\u7ED3\u679C"},compProps:{clearable:!0,readonly:!0,rows:10,type:"textarea"}}}]),m=r({buttonText:"\u786E\u8BA4\u8F6C\u6362"}),i=e=>{c[e.handleLogical](e)},c={useBackendConvert:e=>{let s=e.jsonStrLines.split(`
`),u=[];for(let t=0;t<s.length;t++){let o=s[t];!o||u.push(o)}d({jsonStrs:u}).then(t=>{let o=t.data.data;e.text=o.join(`
`)})}},p=()=>"\u63D0\u53D6\u6210\u529F";return(e,s)=>{const u=C("PtForm");return F(),B(u,{form:a.form,formData:a.formData,labelWidth:"80",method:i,methodSuccess:p,defaultButtonsShow:"submit,reset",submitAttrs:m.value,layout:1,"label-position":"top",comps:l.value},null,8,["form","formData","submitAttrs","comps"])}}});export{_ as default};