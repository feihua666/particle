// indexdb-tools.ts

/**
 * 打开数据库连接
 * @param dbName 数据库名称
 * @param version 版本号
 * @param upgradeCallback 升级回调（可选，用于创建表）
 */
export const openDB = (
    dbName: string,
    version: number = 1,
    upgradeCallback?: (db: IDBDatabase) => void
): Promise<IDBDatabase> => {
    return new Promise((resolve, reject) => {
        const request = indexedDB.open(dbName, version)

        request.onerror = () => reject(request.error)
        request.onsuccess = () => resolve(request.result)

        if (upgradeCallback) {
            request.onupgradeneeded = (event) => {
                const db = (event.target as IDBOpenDBRequest).result
                upgradeCallback(db)
            }
        }
    })
}

/**
 * 关闭数据库连接
 */
export const closeDB = (db: IDBDatabase | null): void => {
    if (db) {
        db.close()
    }
}

/**
 * 获取事务
 * @param db 数据库实例
 * @param storeName 存储对象名称
 * @param mode 事务模式
 */
export const getTransaction = (
    db: IDBDatabase,
    storeName: string,
    mode: IDBTransactionMode = 'readonly'
): IDBTransaction => {
    return db.transaction(storeName, mode)
}

/**
 * 获取存储对象
 */
export const getStore = (
    transaction: IDBTransaction,
    storeName: string,
): IDBObjectStore => {
    return transaction.objectStore(storeName)
}
/**
 * 获取游标（返回 cursor，让调用者自己遍历）
 * @param store 存储对象
 * @returns 返回游标请求对象
 */
export const openCursor = (store: IDBObjectStore): IDBRequest => {
    return store.openCursor()
}
/**
 * 查找单条数据（找到即停止）
 */
export const findFirst = (
    store: IDBObjectStore,
    predicate: (value: any) => boolean
): Promise<any | null> => {
    return new Promise((resolve) => {
        const request = store.openCursor()

        request.onsuccess = (event) => {
            const cursor = (event.target as IDBRequest).result
            if (cursor) {
                if (predicate(cursor.value)) {
                    resolve(cursor.value)  // 找到，直接返回
                } else {
                    cursor.continue()  // 继续找
                }
            } else {
                resolve(null)  // 没找到
            }
        }

        request.onerror = () => resolve(null)
    })
}
/**
 * 获取游标（支持自定义处理，可提前终止）
 * @param store 存储对象
 * @param onItem 每条数据的回调，返回 false 可停止遍历
 * @returns 返回收集的数据数组
 */
export const openCursorWithCallback = <T = any>(
    store: IDBObjectStore,
    shouldContinue: (value: T, key: any) => boolean | void
): Promise<T[]> => {
    return new Promise((resolve) => {
        const result: T[] = []
        const request = store.openCursor()

        request.onsuccess = (event) => {
            const cursor = (event.target as IDBRequest).result
            if (cursor) {
                const isShouldContinue = shouldContinue(cursor.value, cursor.key)
                if (isShouldContinue !== false) {
                    result.push(cursor.value)
                    cursor.continue()
                } else {
                    resolve(result)
                }
            } else {
                resolve(result)
            }
        }

        request.onerror = () => resolve(result)
    })
}
/**
 * 获取键游标（只遍历键，不取值，性能更好）
 * @param store 存储对象
 * @returns 返回游标请求对象
 */
export const openKeyCursor = (store: IDBObjectStore): IDBRequest => {
    return store.openKeyCursor()
}

/**
 * 获取键游标（支持自定义处理，可提前终止）
 * @param store 存储对象
 * @param onKey 每条键的回调，返回 false 可停止遍历
 * @returns 返回 Promise，在遍历完成后 resolve
 */
export const openKeyCursorWithCallback = (
    store: IDBObjectStore,
    shouldContinue: (key: any) => boolean | void
): Promise<void> => {
    return new Promise((resolve) => {
        const request = store.openKeyCursor()

        request.onsuccess = (event) => {
            const cursor = (event.target as IDBRequest).result
            if (cursor) {
                const isShouldContinue = shouldContinue(cursor.key)
                if (isShouldContinue !== false) {
                    cursor.continue()
                } else {
                    resolve()
                }
            } else {
                resolve()
            }
        }

        request.onerror = () => resolve()
    })
}


/**
 * 获取所有数据（一次性）
 */
export const getAllData = <T = any>(
    store: IDBObjectStore
): Promise<T[]> => {
    return new Promise((resolve, reject) => {
        const request = store.getAll()
        request.onsuccess = () => resolve(request.result)
        request.onerror = () => reject(request.error)
    })
}

/**
 * 根据主键获取单条数据
 */
export const getDataByKey = <T = any>(
    store: IDBObjectStore,
    key: string | number
): Promise<T | null> => {
    return new Promise((resolve, reject) => {
        const request = store.get(key)
        request.onsuccess = () => resolve(request.result || null)
        request.onerror = () => reject(request.error)
    })
}

/**
 * 添加数据
 */
export const addData = <T>(
    store: IDBObjectStore,
    data: T
): Promise<T> => {
    return new Promise((resolve, reject) => {
        const request = store.add(data)
        request.onsuccess = () => resolve(data)
        request.onerror = () => reject(request.error)
    })
}

/**
 * 更新数据（存在则更新，不存在则添加）
 */
export const putData = <T>(
    store: IDBObjectStore,
    data: T
): Promise<T> => {
    return new Promise((resolve, reject) => {
        const request = store.put(data)
        request.onsuccess = () => resolve(data)
        request.onerror = () => reject(request.error)
    })
}

/**
 * 根据主键删除数据
 */
export const deleteData = (
    store: IDBObjectStore,
    key: string | number
): Promise<void> => {
    return new Promise((resolve, reject) => {
        const request = store.delete(key)
        request.onsuccess = () => resolve()
        request.onerror = () => reject(request.error)
    })
}

/**
 * 清空存储对象
 */
export const clearStore = (
    store: IDBObjectStore
): Promise<void> => {
    return new Promise((resolve, reject) => {
        const request = store.clear()
        request.onsuccess = () => resolve()
        request.onerror = () => reject(request.error)
    })
}

/**
 * 获取数据数量
 */
export const countData = (
    store: IDBObjectStore,
    key?: string | IDBKeyRange
): Promise<number> => {
    return new Promise((resolve, reject) => {
        const request = key ? store.count(key) : store.count()
        request.onsuccess = () => resolve(request.result)
        request.onerror = () => reject(request.error)
    })
}

/**
 * 使用索引查询
 */
export const getByIndex = <T = any>(
    store: IDBObjectStore,
    indexName: string,
    value: any
): Promise<T[]> => {
    return new Promise((resolve, reject) => {
        const index = store.index(indexName)
        const request = index.getAll(value)
        request.onsuccess = () => resolve(request.result)
        request.onerror = () => reject(request.error)
    })
}

/**
 * 使用范围查询
 */
export const getByRange = <T = any>(
    store: IDBObjectStore,
    range: IDBKeyRange
): Promise<T[]> => {
    return new Promise((resolve, reject) => {
        const request = store.getAll(range)
        request.onsuccess = () => resolve(request.result)
        request.onerror = () => reject(request.error)
    })
}

/**
 * 创建存储对象（在 onupgradeneeded 中使用）
 */
export const createObjectStore = (
    db: IDBDatabase,
    storeName: string,
    options: IDBObjectStoreParameters = { autoIncrement: true }
): IDBObjectStore => {
    return db.createObjectStore(storeName, options)
}

/**
 * 创建索引
 */
export const createIndex = (
    store: IDBObjectStore,
    indexName: string,
    keyPath: string,
    options?: IDBIndexParameters
): IDBIndex => {
    return store.createIndex(indexName, keyPath, options)
}
