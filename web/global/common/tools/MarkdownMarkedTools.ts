import { Marked } from 'marked';
import { markedHighlight } from "marked-highlight";
import DOMPurify from 'dompurify';
import Prism from 'prismjs';
import 'prismjs/themes/prism-tomorrow.css';
import 'prismjs/components/prism-bash.min'

/**
 * 创建一个新实例
 * 参考：https://marked.js.org/using_advanced#instance
 */
export function newMarked() {
    return new Marked();
}
export function newMarkedWithHl() {
    const marked = new Marked(
        markedHighlight({
            emptyLangClass: '',
            langPrefix: 'language-',
            highlight(code, lang, info) {
                if (lang && Prism.languages[lang]) {
                    code = Prism.highlight(code, Prism.languages[lang], lang);
                }
                return code

            }
        })
    );
    // 自定义 renderer
    const renderer = new marked.Renderer()
    const rendererCode = renderer.code

    renderer.code = function({ text, lang, escaped }) {
        let result =  rendererCode({ text, lang, escaped })
        result = result.replace('<pre><code', '<pre class="language-' + lang + '"><code')

        const codeBlock = `
            <div class="pt-chat-code-container">
                <div class="pt-chat-code-container-header">
                    <div class="pt-chat-code-container-header-content pt-height-100-pc pt-flex-align-between pt-flex-item-1 pt-flex-align-cross-center">
                        <div class="pt-chat-code-container-header-content-left">
                            <div class="pt-chat-code-container-header-content-left-lang">${lang || ''}</div>
                        </div>
                        <div class="pt-chat-code-container-header-content-right">
                            <div class="pt-chat-code-container-header-content-right-copy pt-pointer">复制</div>
                        </div>
                    </div>
                </div>
                <div class="pt-chat-code">${result}</div>
                <div class="pt-chat-code-container-footer">
                    <div class="pt-chat-code-container-footer-content pt-height-100-pc pt-flex-align-between pt-flex-item-1 pt-flex-align-cross-center">
                        <div class="pt-chat-code-container-footer-content-left">
                            <div class="pt-chat-code-container-footer-content-left-lang">${lang || ''}</div>
                        </div>
                        <div class="pt-chat-code-container-footer-content-right">
                            <div class="pt-chat-code-container-footer-content-right-copy pt-pointer">复制</div>
                        </div>
                    </div>
                </div>
            </div>
            `

        return codeBlock
    };
    marked.use({ renderer})

    return marked
}
/**
 * 渲染markdown
 * 将markdown渲染成html
 * @param markedInstance
 * @param content
 */
export function renderMarkdown(markedInstance: Marked, markdownString: string) {
    const rawHtml = markedInstance.parse(markdownString);
    let purify = DOMPurify.sanitize(rawHtml)
    return purify;
}
