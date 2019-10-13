import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

Vue.config.productionTip = false

axios.defaults.headers.common.Accept = 'application/json'
axios.interceptors.response.use(
  response => response,
  (error) => {
    if (error.response.status === 401) {
      console.log('Unauthorized, logging out ...')
      store.dispatch('logout')
      router.replace('LoginPage')
      return Promise.reject(error)
    }
  })

new Vue({
  router,
  store,
  axios,
  render: h => h(App)
}).$mount('#app')
