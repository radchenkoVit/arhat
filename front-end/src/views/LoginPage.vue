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
            <div class="field-error" v-if="$v.form.email.$dirty">
              <div class="error" v-if="!$v.form.email.email">Specify email in form youremail@domain.com</div>
              <div class="error" v-if="!$v.form.email.required">Email is required</div>
              <div class="error" v-if="!$v.form.email.maxLength">Max length is 100 symbols</div>
            </div>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password" />
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div class="error" v-if="!$v.form.password.required">Password is required</div>
              <div class="error" v-if="!$v.form.password.minLength">Min length is 2 symbols</div>
              <div class="error" v-if="!$v.form.password.maxLength">Max length is 100 symbols</div>
            </div>
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
import Logo from '@/components/Logo'
import Footer from '@/components/PageFooter'
import userService from '@/services/test/userservice'
import { required, email, minLength, maxLength } from 'vuelidate/lib/validators'

export default {
  name: 'LoginPage',
  data: function () {
    return {
      form: {
        email: '',
        password: ''
      },
      errorMessage: '',
      token: ''
    }
  },
  components: {
    Logo,
    Footer
  },
  validations: {
    form: {
      email: {
        required,
        email,
        maxLength: maxLength(100)
      },
      password: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(30)
      }
    }
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }

      userService.login(this.form).then(() => {
        console.log('inside after loging page')
        this.$router.push({ name: 'HomePage' })
      }).catch((error) => {
        this.errorMessage = error.message.includes('headers') ? 'Somethins went wrong' : error.message
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
