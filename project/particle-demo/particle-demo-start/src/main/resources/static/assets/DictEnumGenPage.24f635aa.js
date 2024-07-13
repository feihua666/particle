import{l as g}from"./dictAdminApi.c2d3f448.js";import{a1 as I,d as p,a as D,r as l,M as E,a2 as v,b as c,o as F,c as _,e as i,F as P,P as $,a3 as B}from"./index.7013aad1.js";const A=m=>{let u=[];return m.forEach(e=>{let a=`
	/**
	 * ${e.name}
	 */
	 ${e.value}
    `;u.push(a)}),u.join(",")},M=({className:m,author:u="generated",dictGroupData:e,dictItems:a=[]})=>`
import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * ${e.name} \u5B57\u5178\u9879
 * </p>
 *
 * @author ${u}
 * @since ${I()}
 */
public enum ${m} implements IDictItem {
	${A(a)};

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.${e.code}.groupCode();
	}

	/**
	 * ${e.name} \u5B57\u5178\u7EC4
	 */
	public enum Group implements IDictGroup {
		${e.code};

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
    `,S=p({name:"DictEnumGenPage"}),x=p({...S,setup(m){const{proxy:u}=$(),e=D({form:{},formData:{},result:""}),a=l([{field:{name:"dictId",valueChange({form:t,formData:r}){setTimeout(()=>{var o;t.className=E(v((o=r.dictId)==null?void 0:o.code,["-"]))},400)}},element:{comp:"PtCascader",formItemProps:{label:"\u9009\u62E9\u4E00\u4E2A\u5B57\u5178\u7EC4",required:!0},compProps:{clearable:!0,dataMethod:()=>g({}),dataMethodResultHandleConvertToTree:!0}}},{field:{name:"className"},element:{comp:"el-input",formItemProps:{label:"\u7C7B\u540D\u79F0",required:!0},compProps:{clearable:!0}}},{field:{name:"author",value:"yw"},element:{comp:"el-input",formItemProps:{label:"\u4F5C\u8005",required:!0},compProps:{clearable:!0}}}]),s=l({buttonText:"\u786E\u8BA4\u751F\u6210",beforeMethod:()=>{var t;return e.form.dictId&&!((t=e.formData.dictId)!=null&&t.isGroup)?(f("\u4EC5\u652F\u6301\u9009\u62E9\u5B57\u5178\u7EC4"),!1):!0}}),d=t=>{B({groupCode:e.formData.dictId.code}).then(r=>{e.result=h(e.form.className,e.form.author,r.data.data)})},f=t=>{u.$message({showClose:!0,message:t,type:"error",showIcon:!0,grouping:!0})},h=(t,r,o)=>M({className:t,author:r,dictGroupData:e.formData.dictId,dictItems:o}),C=()=>"\u751F\u6210\u6210\u529F";return(t,r)=>{const o=c("PtForm"),n=c("el-input");return F(),_(P,null,[i(o,{form:e.form,formData:e.formData,labelWidth:"120",method:d,methodSuccess:C,defaultButtonsShow:"submit,reset",submitAttrs:s.value,inline:"",comps:a.value},null,8,["form","formData","submitAttrs","comps"]),i(n,{modelValue:e.result,"onUpdate:modelValue":r[0]||(r[0]=b=>e.result=b),type:"textarea",rows:"20",placeholder:"\u751F\u6210\u7ED3\u679C\u8FD9\u91CC\u663E\u793A"},null,8,["modelValue"])],64)}}});export{x as default};
