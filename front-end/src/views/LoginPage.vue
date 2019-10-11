<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <Logo/>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email" name="email" v-model="form.email" />
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password" />
          </div>
          <button type="submit" class="btn btn-primary btn-block">Sign in</button>
          <p class="text-center text-muted">
            Don't have account
            <a href="/registration">Create account</a>
          </p>
        </form>
      </div>
    </div>
    <Footer/>
  </div>
</template>

<script>
import loginService from '@/services/login'
import Logo from '@/components/Logo'
import Footer from '@/components/PageFooter'

export default {
  name: 'LoginPage',
  data: function () {
    return {
      form: {
        email: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  components: {
    Logo,
    Footer
  },
  methods: {
    submitForm () {
      loginService
        .register(this.form)
        .then((data) => {
          // localStorage.token = req.data.token
          console.log(data)
          localStorage.dataFromReq = data
          this.$router.push({ name: 'HomePage' })
        })
        .catch(error => {
          this.errorMessage = 'Failed to login. Reason: ' + (error.message ? error.message : 'Uknown') + '.'
          // delete localStorage.token
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
