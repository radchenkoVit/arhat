import axios from 'axios'

export default {
  getMyData () {
    return new Promise((resolve, reject) => {
      axios.get('/api/users/me').then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(error)
      })
    })
  }
}
