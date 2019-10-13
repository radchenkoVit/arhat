import axios from 'axios'
import { BehaviorSubject } from 'rxjs'

const tokenName = 'user_token'
const roles = 'roles'
// eslint-disable-next-line no-unused-vars
var authificated = false
// eslint-disable-next-line no-undef
const currentUserToken = new BehaviorSubject(JSON.stringify(localStorage.getItem(tokenName)))
class UserService {
  get userToken () {
    return currentUserToken.value
  }

  get userTokenObserver () {
    return currentUserToken.asObserver()
  }

  get userRoles () {
    return localStorage.getItem(roles)
  }

  get isAuthificated () {
    return authificated
  }

  login (user) {
    return axios.post('/api/auth/login', user).then(response => {
      localStorage.setItem(tokenName, JSON.stringify(response.headers['authorization']))
      localStorage.setItem(roles, JSON.stringify(response.headers['roles']))
      currentUserToken.next(response.headers['authorization'])
      authificated = true
    })
  }

  logout () {
    return axios.post('/api/auth/logout').then(response => {
      localStorage.removeItem(tokenName)
      localStorage.removeItem(roles)
      currentUserToken.next(null)
      authificated = false
    })
  }
}

export default new UserService()
