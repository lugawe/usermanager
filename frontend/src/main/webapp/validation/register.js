/**
 * 4-20 characters (a-Z, A-Z, 0-9, _, .)
 * no __ or ..
 * no _ or . at start
 * no _ or . at end
 */
const usernameRegex = /^(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]$/

/**
 * default email
 */
const emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/

/**
 * 6-64 characters (except whitespace and \)
 * at least one number
 * at least one uppercase character
 */
const passwordRegex = /^(?=[^\s\\]{6,64}$)(?=.*[0-9])(?=.*[A-Z])/

export default {
  isUsernameValid(username) {
    if (username) {
      return usernameRegex.test(username)
    }
    return false
  },
  isEmailValid(email) {
    if (email) {
      return emailRegex.test(email)
    }
    return false
  },
  isPasswordValid(password) {
    if (password) {
      return passwordRegex.test(password)
    }
    return false
  }
}
