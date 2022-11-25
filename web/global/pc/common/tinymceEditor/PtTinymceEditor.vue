<template>
    <textarea :id="tinymceId"></textarea>
</template>

<script>
// https://blog.csdn.net/qq_43574615/article/details/125977938
    import tinymce from 'tinymce/tinymce'
    import './zh_CN.js'
    import 'tinymce/themes/silver/theme'
    import 'tinymce/plugins/advlist'
    import 'tinymce/plugins/anchor'
    import 'tinymce/plugins/autolink'
    import 'tinymce/plugins/autosave'
    import 'tinymce/plugins/code'
    import 'tinymce/plugins/codesample'
    import 'tinymce/plugins/colorpicker'
    import 'tinymce/plugins/contextmenu'
    import 'tinymce/plugins/directionality'
    import 'tinymce/plugins/emoticons'
    import 'tinymce/plugins/fullscreen'
    import 'tinymce/plugins/hr'
    import 'tinymce/plugins/image'
    import 'tinymce/plugins/imagetools'
    import 'tinymce/plugins/importcss'
    import 'tinymce/plugins/insertdatetime'
    import 'tinymce/plugins/link'
    import 'tinymce/plugins/lists'
    import 'tinymce/plugins/media'
    import 'tinymce/plugins/nonbreaking'
    import 'tinymce/plugins/noneditable'
    import 'tinymce/plugins/pagebreak'
    import 'tinymce/plugins/paste'
    import 'tinymce/plugins/preview'
    import 'tinymce/plugins/print'
    import 'tinymce/plugins/save'
    import 'tinymce/plugins/searchreplace'
    import 'tinymce/plugins/spellchecker'
    import 'tinymce/plugins/tabfocus'
    import 'tinymce/plugins/table'
    import 'tinymce/plugins/template'
    import 'tinymce/plugins/textcolor'
    import 'tinymce/plugins/textpattern'
    import 'tinymce/plugins/visualblocks'
    import 'tinymce/plugins/visualchars'
    import 'tinymce/plugins/wordcount'
    import 'tinymce/skins/ui/oxide/skin.min.css'

    import 'tinymce/icons/default/index.js'

    const plugins = ['advlist anchor autolink autosave code codesample colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount']
    const toolbar = ['bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample', 'hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen']

    export default {
        name: 'PtTinymceEditor',
        props: {
            id: {
                type: String,
                default: function () {
                    return 'vue-tinymce-' + new Date().getTime() + '-' + ((Math.random() * 1000).toFixed(0) + '')
                }
            },
            value: {
                type: String,
                default: ''
            },
            // tinymce 选项
            originOptions:{
                type: Object,
                default: function () {
                    return {}
                }
            }
        },
        data() {
            return {
                hasInit: false,
                // 在输入的时候不监听值变化并更新值
                flag: true,
                tinymceId: this.id,
                pasteUploading: false
            }
        },
        mounted() {
            this.init()
        },
        activated() {
            this.init()
        },
        deactivated() {
            this.destroyTinymce()
        },
        destroyed() {
            this.destroyTinymce()
        },
        methods: {
            init() {
                let defaultOptions = {
                    selector: '#' + this.tinymceId,
                    height: 400,
                    toolbar: toolbar,
                    // 图片最大 宽度设置，注意这里不会和内容一起保存
                    content_style: 'img {max-width:100% !important }', // 初始化赋值
                    language: 'zh_CN',
                    plugin_preview_width: 400,
                    image_advtab: true,
                    // 允许图片粘贴
                    paste_data_images: true,
                    paste_preprocess: function (plugin, args) {

                    },
                    init_instance_callback: editor => {
                        if (this.value) {
                            editor.setContent(this.value)
                        }
                        this.hasInit = true
                        editor.on('Change KeyUp SetContent input undo redo', (e) => {
                            this.flag = false
                            this.$emit('input', editor.getContent())
                        })
                    },
                    plugins: plugins,
                    setup: function (editor) {

                    }
                }
                tinymce.init(Object.assign(defaultOptions,this.originOptions));
            },
            destroyTinymce() {
                if (tinymce.get(this.tinymceId)) {
                    tinymce.get(this.tinymceId).destroy()
                }
            },
            setContent(value) {
                if (value) {
                    tinymce.get(this.tinymceId).setContent(value)
                }
            },
            clearContent() {
                tinymce.get(this.tinymceId).setContent('')
            },
            getContent() {
                tinymce.get(this.tinymceId).getContent()
            },
            insertContent(val) {
                tinymce.get(this.tinymceId).insertContent(val)
            },
        },
        watch: {
            value(val) {
                if (this.hasInit && this.flag) {
                    this.setContent(val)
                }
                this.flag = true
            }
        }
    }
</script>

