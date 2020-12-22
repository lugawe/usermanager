export const state = () => ({
  user: {},
  token: {
    access: null,
    refresh: null
  },
  isLoggedIn: false
})

export const mutations = {
  logout(state) {
    state.user = {}
    state.token = {
      access: null,
      refresh: null
    }
    state.isLoggedIn = false
  },
  login(state, data) {
    state.user = data.user
    state.token = data.token
    state.isLoggedIn = true
  },
  accessToken(state, token) {
    state.token.access = token
  },
  refreshToken(state, token) {
    state.token.refresh = token
  }
}
