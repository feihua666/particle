// ==UserScript==
// @name         豆包会话管理工具
// @namespace    particle
// @version      0.0.3
// @author       feihua
// @description  豆包会话管理工具，帮助用户批量删除豆包网站的聊天记录。支持多选、逐条删除，同时保留未选中的对话。
// @license      MIT
// @icon         data:image/png;base64,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
// @homepage     https://github.com/feihua666/particle
// @supportURL   https://github.com/feihua666/particle/issues
// @match        https://www.doubao.com/*
// @require      https://cdn.jsdelivr.net/npm/vue@3.5.32/dist/vue.global.prod.js
// @require      data:application/javascript,%3Bwindow.Vue%3DVue%3B
// @require      https://cdn.jsdelivr.net/npm/@varlet/ui@3.15.1/umd/varlet.js
// @require      data:application/javascript,%3Bwindow.Varlet%3DVarlet%3B
// @require      https://cdn.jsdelivr.net/npm/@varlet/touch-emulator@3.15.1/iife.js
// @grant        GM_addStyle
// ==/UserScript==

(function (vue, Varlet) {
  'use strict';

  const d=new Set;const e = async e=>{d.has(e)||(d.add(e),(t=>{typeof GM_addStyle=="function"?GM_addStyle(t):(document.head||document.documentElement).appendChild(document.createElement("style")).append(t);})(e));};

  e(" .no-drag[data-v-9e1b93ce]{pointer-events:none} ");

  const logo = "data:image/png;base64,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";
  const doubaoLogo = "data:image/png;base64,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";
  function reliableClick(el) {
    if (!el) return false;
    const rect = el.getBoundingClientRect();
    const opts = {
      bubbles: true,
      cancelable: true,
clientX: rect.left + rect.width / 2,
      clientY: rect.top + rect.height / 2
    };
    const events = [
      "pointerdown",
      "mousedown",
      "pointerup",
      "mouseup",
      "click"
    ];
    events.forEach((type) => {
      const event = type.startsWith("pointer") ? new PointerEvent(type, opts) : new MouseEvent(type, opts);
      el.dispatchEvent(event);
    });
    return true;
  }
  function delayChain(target) {
    const queue = [];
    const api = {
      step(fn, delay = 0) {
        queue.push({ type: "step", fn, delay });
        return api;
      },
      wait(ms) {
        queue.push({ type: "wait", ms });
        return api;
      },
      waitFor(condition, timeout = 1e4, interval = 200) {
        queue.push({ type: "waitFor", condition, timeout, interval });
        return api;
      },
      async run() {
        for (const item of queue) {
          if (item.type === "wait") {
            await sleep(item.ms);
            continue;
          }
          if (item.type === "waitFor") {
            await waitUntil(item.condition, item.timeout, item.interval);
            continue;
          }
          if (item.type === "step") {
            if (item.delay) await sleep(item.delay);
            await item.fn?.(target);
          }
        }
      }
    };
    return api;
  }
  function sleep(ms) {
    return new Promise((r) => setTimeout(r, ms));
  }
  function waitUntil(fn, timeout = 1e4, interval = 200) {
    return new Promise((resolve, reject) => {
      const start = Date.now();
      const timer = setInterval(() => {
        if (Date.now() - start >= timeout) {
          clearInterval(timer);
          reject(new Error(`waitFor timed out after ${timeout}ms`));
          return;
        }
        try {
          if (fn()) {
            clearInterval(timer);
            resolve();
          }
        } catch (e) {
          clearInterval(timer);
          reject(e);
        }
      }, interval);
    });
  }
  const _sfc_main$2 = {
    __name: "Conversation",
    setup(__props) {
      const $alert = (message, type = "info") => {
        Varlet.Snackbar[type] ? Varlet.Snackbar[type](message) : Varlet.Snackbar(message);
      };
      vue.ref(true);
      const list = vue.ref([]);
      const selected = vue.ref([]);
      async function scan() {
        const sideBar = document.querySelector("#flow_chat_sidebar");
        if (!sideBar) return;
        const items = sideBar.querySelectorAll('[id^="conversation_"]');
        list.value = Array.from(items).map((el) => ({
          id: el.id,
          title: el.innerText.trim(),
          el,
          isRemoved: false
        })).filter((i) => i.title != "手机版对话");
        $alert(`已扫描 ${list.value.length} 个对话,注意 手机版对话 已排除，页面未显示的会话扫描不到`);
      }
      async function toggleAll() {
        if (!list.value.length) {
          $alert("请先扫描会话");
          return;
        }
        if (selected.value.length === list.value.length) {
          selected.value = [];
        } else {
          selected.value = list.value.map((i) => i.id);
        }
      }
      async function removeSelected() {
        const targets = list.value.filter(
          (i) => selected.value.includes(i.id)
        );
        if (!targets.length) {
          $alert("没有选中的会话");
          return;
        }
        for (const item of targets) {
          try {
            await delayChain(item).step((target) => {
              const wrapper = target.el.querySelectorAll(".items-center")[1]?.querySelector('[class*="chat-item-menu-wrapper"]');
              if (!wrapper) throw new Error("wrapper not found");
              wrapper.style.setProperty("display", "flex", "important");
              wrapper.style.setProperty("visibility", "visible", "important");
              const triggerButton = wrapper.querySelector('button[data-slot="dropdown-menu-trigger"]');
              if (!triggerButton) throw new Error("triggerButton not found");
              reliableClick(triggerButton);
            }).step(() => {
              const items = document.querySelectorAll(
                'div[data-radix-popper-content-wrapper] div[data-slot="dropdown-menu-item"]'
              );
              const last = items[items.length - 1];
              if (!last) throw new Error("dropdown item not found");
              reliableClick(last);
            }, 300).step(() => {
              const confirmButton = document.querySelectorAll('div[data-slot="dialog-content"] div[data-slot="dialog-footer"] button')[1];
              if (!confirmButton) throw new Error("confirmButton not found");
              reliableClick(confirmButton);
            }, 200).run();
            item.isRemoved = true;
          } catch (e) {
            $alert(`删除 ${item.id} 失败`, "error");
            console.error(`删除 ${item.id} 失败`, e);
          }
        }
        list.value = list.value.filter((x) => !selected.value.includes(x.id));
        selected.value = [];
      }
      return (_ctx, _cache) => {
        const _component_var_button = vue.resolveComponent("var-button");
        const _component_var_space = vue.resolveComponent("var-space");
        const _component_var_cell = vue.resolveComponent("var-cell");
        const _component_var_checkbox = vue.resolveComponent("var-checkbox");
        const _component_var_icon = vue.resolveComponent("var-icon");
        const _component_var_checkbox_group = vue.resolveComponent("var-checkbox-group");
        const _component_var_paper = vue.resolveComponent("var-paper");
        return vue.openBlock(), vue.createElementBlock(vue.Fragment, null, [
          vue.createVNode(_component_var_cell, null, {
            default: vue.withCtx(() => [
              vue.createVNode(_component_var_space, { justify: "space-around" }, {
                default: vue.withCtx(() => [
                  vue.createVNode(_component_var_button, {
                    type: "primary",
                    "auto-loading": true,
                    onClick: scan
                  }, {
                    default: vue.withCtx(() => [..._cache[1] || (_cache[1] = [
                      vue.createTextVNode("扫描", -1)
                    ])]),
                    _: 1
                  }),
                  vue.createVNode(_component_var_button, {
                    type: "warning",
                    "auto-loading": true,
                    onClick: toggleAll
                  }, {
                    default: vue.withCtx(() => [..._cache[2] || (_cache[2] = [
                      vue.createTextVNode("全选", -1)
                    ])]),
                    _: 1
                  }),
                  vue.createVNode(_component_var_button, {
                    type: "danger",
                    "auto-loading": true,
                    onClick: removeSelected
                  }, {
                    default: vue.withCtx(() => [..._cache[3] || (_cache[3] = [
                      vue.createTextVNode("删除", -1)
                    ])]),
                    _: 1
                  })
                ]),
                _: 1
              })
            ]),
            _: 1
          }),
          vue.createVNode(_component_var_paper, {
            height: "calc(100% - 44px)",
            style: { "overflow-y": "auto" }
          }, {
            default: vue.withCtx(() => [
              vue.createVNode(_component_var_checkbox_group, {
                modelValue: selected.value,
                "onUpdate:modelValue": _cache[0] || (_cache[0] = ($event) => selected.value = $event)
              }, {
                default: vue.withCtx(() => [
                  (vue.openBlock(true), vue.createElementBlock(vue.Fragment, null, vue.renderList(list.value, (item, index) => {
                    return vue.openBlock(), vue.createBlock(_component_var_cell, {
                      ripple: "",
                      key: item.id,
                      border: index !== list.value.length - 1,
                      "border-offset": 0,
                      style: { "padding-top": "0", "padding-bottom": "0" }
                    }, vue.createSlots({
                      default: vue.withCtx(() => [
                        vue.createVNode(_component_var_checkbox, {
                          "checked-value": item.id
                        }, {
                          default: vue.withCtx(() => [
                            vue.createTextVNode(vue.toDisplayString(1 + index) + ". " + vue.toDisplayString(item.title), 1)
                          ]),
                          _: 2
                        }, 1032, ["checked-value"])
                      ]),
                      _: 2
                    }, [
                      item.isRemoved ? {
                        name: "extra",
                        fn: vue.withCtx(() => [
                          vue.createVNode(_component_var_icon, {
                            name: "checkbox-marked-circle",
                            color: "var(--color-primary)"
                          })
                        ]),
                        key: "0"
                      } : void 0
                    ]), 1032, ["border"]);
                  }), 128))
                ]),
                _: 1
              }, 8, ["modelValue"])
            ]),
            _: 1
          })
        ], 64);
      };
    }
  };
  const _sfc_main$1 = {
    __name: "ConversationDialog",
    props: {
      show: Boolean
    },
    emits: ["update:show"],
    setup(__props, { emit: __emit }) {
      const props = __props;
      const emit = __emit;
      const computedShow = vue.computed({
        get: () => props.show,
        set: (val) => emit("update:show", val)
      });
      return (_ctx, _cache) => {
        const _component_var_app_bar = vue.resolveComponent("var-app-bar");
        const _component_var_paper = vue.resolveComponent("var-paper");
        const _component_var_popup = vue.resolveComponent("var-popup");
        return vue.openBlock(), vue.createBlock(_component_var_popup, {
          show: computedShow.value,
          "onUpdate:show": _cache[0] || (_cache[0] = ($event) => computedShow.value = $event),
          position: "right",
          "lock-scroll": false,
          style: { "display": "flex", "flex-direction": "column" }
        }, {
          default: vue.withCtx(() => [
            vue.createVNode(_component_var_app_bar, { title: "对话管理" }),
            vue.createVNode(_component_var_paper, { style: { "width": "320px", "flex": "1" } }, {
              default: vue.withCtx(() => [
                vue.createVNode(_sfc_main$2)
              ]),
              _: 1
            })
          ]),
          _: 1
        }, 8, ["show"]);
      };
    }
  };
  const _export_sfc = (sfc, props) => {
    const target = sfc.__vccOpts || sfc;
    for (const [key, val] of props) {
      target[key] = val;
    }
    return target;
  };
  const _sfc_main = {
    __name: "App",
    setup(__props) {
      const show = vue.ref(false);
      const drag = vue.ref(true);
      const logoImg = vue.ref(logo);
      function handleHover(hovering) {
        logoImg.value = hovering ? doubaoLogo : logo;
      }
      return (_ctx, _cache) => {
        const _component_var_avatar = vue.resolveComponent("var-avatar");
        const _component_var_button = vue.resolveComponent("var-button");
        const _component_var_fab = vue.resolveComponent("var-fab");
        const _directive_hover = vue.resolveDirective("hover");
        return vue.openBlock(), vue.createElementBlock(vue.Fragment, null, [
          vue.createVNode(_component_var_fab, {
            drag: drag.value,
            elevation: 10
          }, {
            trigger: vue.withCtx(({ active }) => [
              vue.withDirectives((vue.openBlock(), vue.createBlock(_component_var_button, {
                type: "primary",
                round: ""
              }, {
                default: vue.withCtx(() => [
                  vue.createVNode(_component_var_avatar, {
                    size: "small",
                    class: "no-drag",
                    src: logoImg.value
                  }, null, 8, ["src"])
                ]),
                _: 1
              })), [
                [_directive_hover, handleHover]
              ])
            ]),
            default: vue.withCtx(() => [
              vue.createVNode(_component_var_button, {
                onClick: _cache[0] || (_cache[0] = ($event) => show.value = !show.value)
              }, {
                default: vue.withCtx(() => [..._cache[2] || (_cache[2] = [
                  vue.createTextVNode("会话", -1)
                ])]),
                _: 1
              })
            ]),
            _: 1
          }, 8, ["drag"]),
          vue.createVNode(_sfc_main$1, {
            show: show.value,
            "onUpdate:show": _cache[1] || (_cache[1] = ($event) => show.value = $event)
          }, null, 8, ["show"])
        ], 64);
      };
    }
  };
  const App = _export_sfc(_sfc_main, [["__scopeId", "data-v-9e1b93ce"]]);
  const host = document.createElement("div");
  host.className = "particle";
  document.body.appendChild(host);
  const shadow = host.attachShadow({ mode: "open" });
  const appRoot = document.createElement("div");
  shadow.appendChild(appRoot);
  const customTheme = {
    ...Varlet.Themes.md3Light
  };
  Varlet.StyleProvider(customTheme);
  vue.createApp(App).use(Varlet).mount(appRoot);

})(Vue, Varlet);