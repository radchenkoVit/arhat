<template>
  <div>
      <Header/>
      <h1 class="page-title">Home Page</h1>
      <button v-on:click="admin">Admin click</button>
      <button v-on:click="user">User-Admin click</button>
      <button v-on:click="anybody">Authorized click</button>
      <p v-if="responseString"> {{ responseString }} </p>
  </div>
</template>

<script>
import Header from '@/components/PageHeader'
import axios from 'axios'

export default {
  name: 'HomePage',
  data: function () {
    return {
      responseString: ''
    }
  },
  components: {
    Header
  },
  methods: {
    admin () {
      return axios.post('/api/secure/admin').then(response => {
        this.responseString = response.data
      })
    },
    user () {
      return axios.post('/api/secure/useradmin').then(response => {
        this.responseString = response.data
      })
    },
    anybody () {
      return axios.post('/api/secure/authenticated').then(response => {
        this.responseString = response.data
      })
    }
  }
}
</script>
