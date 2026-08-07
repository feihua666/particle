import {v4 as uuidv4} from 'uuid';

/**
 * 生成UUID
 */
export function generateUUID(): string {
    return uuidv4();
}
