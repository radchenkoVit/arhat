<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <Logo/>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="name">Full Name</label>
            <input type="name" class="form-control" id="name" v-model="form.name" />
          </div>
          <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email" v-model="form.email" />
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password" />
          </div>
          <button type="submit" class="btn btn-primary btn-block">Create account</button>
          <p class="accept-terms text-muted">
            By clicking “Create account”, you agree to our
            <a href="#">terms of service</a> and
            <a href="#">privacy policy</a>.
          </p>
          <p class="text-center text-muted">
            Already have an account?
            <a href="/login">Sign in</a>
          </p>
        </form>
      </div>
    </div>
    <Footer/>
  </div>
</template>

<script>
import registrationService from '@/services/registration'
import Logo from '@/components/Logo'
import Footer from '@/components/PageFooter'

export default {
  name: 'RegisterPage',
  data: function () {
    return {
      form: {
        name: '',
        email: '',
        password: '',
        errorMessage: ''
      }
    }
  },
  components: {
    Logo,
    Footer
  },
  methods: {
    submitForm () {
      registrationService
        .register(this.form)
        .then(() => {
          this.$router.push({ name: 'LoginPage' })
        })
        .catch(error => {
          this.errorMessage =
            'Failed to register user. Reason: ' + (error.message ? error.message : 'Uknown') + '.'
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
