<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <Logo/>
        <form @submit.prevent="submitForm">
          <div v-show="form.errorMessage" class="alert alert-danger failed">{{ form.errorMessage }}</div>
          <div class="form-group">
            <label for="name">Full Name</label>
            <input type="name" class="form-control" id="name" v-model="form.name" />
            <div class="field-error" v-if="$v.form.name.$dirty">
              <div class="error" v-if="!$v.form.name.required">Username is required, should be more than 2 symbols and less than 50</div>
              <div class="error" v-if="!$v.form.name.alpha">Only symbols</div>
              <div class="error" v-if="!$v.form.name.minLength">Min length is 2 symbols</div>
              <div class="error" v-if="!$v.form.name.maxLength">Max length is 50 symbols</div>
            </div>
          </div>
          <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email" v-model="form.email" />
            <div class="field-error" v-if="$v.form.email.$dirty">
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
import { required, email, minLength, maxLength, alpha } from 'vuelidate/lib/validators'

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
  validations: {
    form: {
      name: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
        alpha
      },
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
