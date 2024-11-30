import{a1 as b,d as p,a as D,r as l,O as I,a2 as E,b as c,o as _,c as v,e as F,f as i,F as P,R as $,a3 as B,_ as w}from"./index.b53d3025.js";import{l as A}from"./dictAdminApi.c4334aea.js";const G=s=>{let u=[];return s.forEach(e=>{let a=`
	/**
	 * ${e.name}
	 */
	 ${e.value}
    `;u.push(a)}),u.join(",")},x=({className:s,author:u="generated",dictGroupData:e,dictItems:a=[]})=>`
import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * ${e.name} \u5B57\u5178\u9879
 * </p>
 *
 * @author ${u}
 * @since ${b()}
 */
public enum ${s} implements IDictItem {
	${G(a)};

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
    `,S=p({name:"DictEnumGenPage"}),T=p({...S,setup(s){const{proxy:u}=$(),e=D({form:{},formData:{},result:""}),a=l([{field:{name:"dictId",valueChange({form:t,formData:r}){setTimeout(()=>{var o;t.className=I(E((o=r.dictId)==null?void 0:o.code,["-"]))},400)}},element:{comp:"PtCascader",formItemProps:{label:"\u9009\u62E9\u4E00\u4E2A\u5B57\u5178\u7EC4",required:!0},compProps:{clearable:!0,dataMethod:()=>A({}),dataMethodResultHandleConvertToTree:!0}}},{field:{name:"className"},element:{comp:"el-input",formItemProps:{label:"\u7C7B\u540D\u79F0",required:!0},compProps:{clearable:!0}}},{field:{name:"author",value:"yw"},element:{comp:"el-input",formItemProps:{label:"\u4F5C\u8005",required:!0},compProps:{clearable:!0}}}]),n=l({buttonText:"\u786E\u8BA4\u751F\u6210",beforeMethod:()=>{var t;return e.form.dictId&&!((t=e.formData.dictId)!=null&&t.isGroup)?(f("\u4EC5\u652F\u6301\u9009\u62E9\u5B57\u5178\u7EC4"),!1):!0}}),d=t=>{B({groupCode:e.formData.dictId.code}).then(r=>{e.result=h(e.form.className,e.form.author,r.data.data)})},f=t=>{u.$message({showClose:!0,message:t,type:"error",showIcon:!0,grouping:!0})},h=(t,r,o)=>x({className:t,author:r,dictGroupData:e.formData.dictId,dictItems:o}),g=()=>"\u751F\u6210\u6210\u529F";return(t,r)=>{const o=c("PtForm"),m=c("el-input");return _(),v(P,null,[F(" \u6DFB\u52A0\u8868\u5355 "),i(o,{form:e.form,formData:e.formData,labelWidth:"120",method:d,methodSuccess:g,defaultButtonsShow:"submit,reset",submitAttrs:n.value,inline:"",comps:a.value},null,8,["form","formData","submitAttrs","comps"]),i(m,{modelValue:e.result,"onUpdate:modelValue":r[0]||(r[0]=C=>e.result=C),type:"textarea",rows:"20",placeholder:"\u751F\u6210\u7ED3\u679C\u8FD9\u91CC\u663E\u793A"},null,8,["modelValue"])],64)}}}),M=w(T,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/tools/views/front/DictEnumGenPage.vue"]]);export{M as default};
