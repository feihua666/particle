import{i as t}from"./index-D8BtRcSm.js";let r="/admin/web/tenant_user_invite";const p=e=>t.post(r+"/create",e),a=e=>t.delete(r+"/delete",{data:e}),l=e=>t.put(r+"/update",e),n=e=>t.get(r+"/detail-for-update",{params:e}),s=e=>t.get(r+"/page",{params:e}),i=[{field:{name:"inviteCode"},element:{comp:"el-input",formItemProps:{label:"邀请码"},compProps:{clearable:!0}}},{field:{name:"isExpired",value:!1},element:{comp:"el-switch",formItemProps:{label:"是否过期"},compProps:{}}},{field:{name:"inviterUserId"},element:{comp:"el-input",formItemProps:{label:"邀请人用户id"},compProps:{}}}],o=[{field:{name:"inviteCode"},element:{comp:"el-input",formItemProps:{label:"邀请码",required:!0},compProps:{clearable:!0}}},{field:{name:"maxInviteCount"},element:{comp:"el-input",formItemProps:{label:"最大邀请人数",required:!0},compProps:{}}},{field:{name:"invitedCount"},element:{comp:"el-input",formItemProps:{label:"已邀请人数"},compProps:{}}},{field:{name:"isExpired",value:!1},element:{comp:"el-switch",formItemProps:{label:"是否过期",required:!0},compProps:{}}},{field:{name:"expiredReason"},element:{comp:"el-input",formItemProps:{label:"过期原因"},compProps:{clearable:!0}}},{field:{name:"expireAt"},element:{comp:"el-input",formItemProps:{label:"到期时间"},compProps:{}}},{field:{name:"inviterUserId"},element:{comp:"el-input",formItemProps:{label:"邀请人用户id",required:!0},compProps:{}}}],d=o;export{s as a,o as b,p as c,l as d,n as e,i as p,a as r,d as u};