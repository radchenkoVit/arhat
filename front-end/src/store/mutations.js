
const tokenName = 'user_token'
const roles = 'roles'

export default {
  updateMyData (state, data) {
    state.user.name = data.name
    state.user.authenticated = true
  },
  logout (state) {
    localStorage.removeItem(tokenName)
    localStorage.removeItem(roles)
    state.user.name = ''
    state.user.authenticated = false
    state.user.role = ''
  }
}
