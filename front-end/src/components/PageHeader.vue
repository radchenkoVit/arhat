<template>
  <div>
      <nav class="navbar navbar-expand navbar-dark bg-dark">
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <a href="/home" class="nav-link">Home</a>
        </li>
        <li class="nav-item" v-if="isAdmin">
          <a href="/admin" class="nav-link"> Admin Panel </a>
        </li>
      </div>

      <div class="navbar-nav ml-auto">
        <li class="nav-item">
          {{ user.name }}
        </li>
        <li class="nav-item">
          <a v-on:click="logout" class="nav-link"> LogOut </a>
        </li>
      </div>
      </nav>
  </div>
</template>

<script>
import Role from '@/model/role'
import userService from '@/services/test/userservice'
// eslint-disable-next-line
import meService from '@/services/user/meservice'
import { mapGetters } from 'vuex'

export default {
  name: 'Header',
  computed: {
    ...mapGetters([
      'user'
    ]),
    isAdmin () {
      return userService.userRoles.replace(/"/g, '') === Role.ADMIN
    }
  },
  created () {
    if (!this.user.authenticated) {
      this.$store.dispatch('getMyData')
    }
  },
  methods: {
    logout () {
      userService.logout()
      this.$store.dispatch('logout')
      this.$router.push({ name: 'LoginPage' })
      // userService.logout().then(() => {
      //   this.$router.push({ name: 'LoginPage' })
      // }).catch((error) => {
      //   console.log(error)
      // })
    }
  }
}
</script>

<style lang="scss" scoped>
.footer {
  width: 100%;
  font-size: 13px;
  color: #666;
  line-height: 40px;
  border-top: 1px solid #ddd;
  margin-top: 50px;

  .list-inline-item {
    margin-right: 10px;
  }

  a {
    color: #666;
  }
}
</style>
