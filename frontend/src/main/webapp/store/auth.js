export const state = () => ({
  user: {},
  isLoggedIn: false
})

export const mutations = {
  logout(state) {
    state.user = {}
    state.isLoggedIn = false
  },
  login(state, data) {
    if (data) {
      state.user = data.user
      state.isLoggedIn = true
    }
  }
}
