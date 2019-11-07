import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Vuelidate from 'vuelidate'

Vue.config.productionTip = false
Vue.use(Vuelidate)

axios.defaults.headers.common.Accept = 'application/json'

axios.interceptors.request.use((config) => {
  let tokenBarer = localStorage.getItem('user_token') ? localStorage.getItem('user_token').replace(/"/g, '') : null
  console.log('token value', tokenBarer)

  if (tokenBarer) {
    config.headers['Authorization'] = tokenBarer
  }

  return config
}, (error) => {
  return Promise.reject(error)
})

axios.interceptors.response.use(
  response => response,
  (error) => {
    return Promise.reject(error)
    // if (error.response.status === 401) {
    //   console.log('Unauthorized, logging out ...')
    //   store.dispatch('logout')
    //   router.replace('LoginPage')
    //   return Promise.reject(error)
    // }
  })

new Vue({
  router,
  store,
  axios,
  render: h => h(App)
}).$mount('#app')
