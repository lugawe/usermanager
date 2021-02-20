const baseURL = '/api/auth/'
const registerURL = baseURL + 'register'
const checkURL = baseURL + 'check'
const logoutURL = baseURL + 'logout'
const loginURL = baseURL + 'login'

const register = function(axios, userName, userMail, userPassword) {
  return axios.post(registerURL, { name: userName, mail: userMail, password: userPassword })
}

const check = function(axios) {
  return axios.get(checkURL)
}

const logout = function(axios) {
  return axios.get(logoutURL)
}

const login = function(axios, userName, userPassword) {
  return axios.post(loginURL, { name: userName, password: userPassword })
}

export default {
  register,
  check,
  logout,
  login
}
