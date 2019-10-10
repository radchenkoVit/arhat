<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" src="/static/images/logo.png" />
          <div class="tagline">Open source group management tool</div>
        </div>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="username">Email address</label>
            <input type="email" class="form-control" id="username" name="username" v-model="form.username" />
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password" />
          </div>
          <button type="submit" class="btn btn-primary btn-block">Sign in</button>
          <p class="text-center text-muted">
            Don't have account
            <a href="/vue/register">Create account</a>
          </p>
        </form>
      </div>
    </div>
    <footer class="footer">
      <span class="copyright">&copy; 2018 Arhat-do.com</span>
      <ul class="footer-links list-inline float-right">
        <li class="list-inline-item">
          <a href="#">About</a>
        </li>
        <li class="list-inline-item">
          <a href="#">Terms of Service</a>
        </li>
        <li class="list-inline-item">
          <a href="#">Privacy Policy</a>
        </li>
      </ul>
    </footer>
  </div>
</template>

<script>
import loginService from '@/services/login'

export default {
  name: 'LoginPage',
  data: function () {
    return {
      form: {
        username: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  methods: {
    submitForm () {
      loginService
        .register(this.form)
        .then(() => {
          this.$router.push({ name: 'HomePage' })
        })
        .catch(error => {
          this.errorMessage = 'Failed to login. Reason: ' + (error.message ? error.message : 'Uknown') + '.'
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  max-width: 900px;
}
.register-form {
  margin-top: 50px;
  max-width: 320px;
}
.logo-wrapper {
  margin-bottom: 40px;
}
.footer {
  width: 100%;
  line-height: 40px;
  margin-top: 50px;
}
</style>
