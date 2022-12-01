import axios from 'axios'

/**
 * 登录
 * @param loginForm
 */
export const login = ({username,password}) => {
    return axios.post('/login','username=' + username +'&password='+password)
}
/**
 * 退出登录
 */
export const logout = () => {
    return axios.post('/logout')
}