/**
 * 延时链式调用
 * 使用示例：
 * delayChain(document.querySelector('#btn')!)
 *   .wait(500)
 *   .step(async (el) => {
 *     await fetch('/api/log', { method: 'POST' })
 *     el.textContent = 'clicked'
 *   })
 *   .click(200)
 *   .waitFor(() => document.querySelector('.done') !== null)
 *   .remove()
 *   .run()
 *   .catch(console.error) // waitFor 超时会在这里捕获
 */
type StepFn<T> = (target?: T) => void | Promise<void>

type QueueItem<T> =
    | { type: "wait"; ms: number }
    | { type: "waitFor"; condition: () => boolean; timeout: number; interval: number }
    | { type: "step"; fn: StepFn<T>; delay: number }

export function delayChain<T extends Element>(target?: T) {
    const queue: QueueItem<T>[] = []

    const api = {
        step(fn: StepFn<T>, delay = 0) {
            queue.push({ type: "step", fn, delay })
            return api
        },

        wait(ms: number) {
            queue.push({ type: "wait", ms })
            return api
        },

        waitFor(condition: () => boolean, timeout = 10000, interval = 200) {
            queue.push({ type: "waitFor", condition, timeout, interval })
            return api
        },

        async run() {
            for (const item of queue) {
                if (item.type === "wait") {
                    await sleep(item.ms)
                    continue
                }

                if (item.type === "waitFor") {
                    // 修复：超时抛出错误中断队列，不再静默吞掉
                    await waitUntil(item.condition, item.timeout, item.interval)
                    continue
                }

                if (item.type === "step") {
                    if (item.delay) await sleep(item.delay)
                    // 修复：支持异步 step fn
                    await item.fn?.(target)
                }
            }
        },
    }

    return api
}

function sleep(ms: number): Promise<void> {
    return new Promise((r) => setTimeout(r, ms))
}

function waitUntil(
    fn: () => boolean,
    timeout = 10000,
    interval = 200
): Promise<void> {
    return new Promise((resolve, reject) => {
        const start = Date.now()

        const timer = setInterval(() => {
            // 修复：先判断超时，再执行条件，避免最后一帧漏掉超时
            if (Date.now() - start >= timeout) {
                clearInterval(timer)
                reject(new Error(`waitFor timed out after ${timeout}ms`))
                return
            }

            try {
                if (fn()) {
                    clearInterval(timer)
                    resolve()
                }
            } catch (e) {
                // 修复：fn 抛错时中断，而不是静默忽略
                clearInterval(timer)
                reject(e)
            }
        }, interval)
    })
}
