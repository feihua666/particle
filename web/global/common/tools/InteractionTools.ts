/**
 * 模拟点击
 * @param el DOM元素
 */
export function reliableClick (el: HTMLElement) {
    if (!el) return false

    const rect = el.getBoundingClientRect()

    const opts = {
        bubbles: true,
        cancelable: true,
        // view: window,
        clientX: rect.left + rect.width / 2,
        clientY: rect.top + rect.height / 2
    }

    const events = [
        "pointerdown",
        "mousedown",
        "pointerup",
        "mouseup",
        "click"
    ]

    events.forEach(type => {
        const event =
            type.startsWith("pointer")
                ? new PointerEvent(type, opts)
                : new MouseEvent(type, opts)

        el.dispatchEvent(event)
    })

    return true
}
