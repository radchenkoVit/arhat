import meService from '@/services/user/meservice'

export const getMyData = ({ commit }) => {
  meService.getMyData().then(data => {
    commit('updateMyData', data)
  })
}

export const logout = ({ commit }) => {
  commit('logout')
}
