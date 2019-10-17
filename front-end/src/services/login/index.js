import axios from 'axios'

export default {
  register (user) {
    return axios.post('/api/auth/login', user).then(response => {
      localStorage.setItem('token', JSON.stringify(response.headers['authorization']))
    })
  }
}
