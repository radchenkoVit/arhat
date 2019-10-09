<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" src="/static/images/logo.png">
          <div class="tagline">Open source task management tool</div>
        </div>
        <form>
          <div class="form-group">
            <label for="emailAddress">Email address</label>
            <input type="email" class="form-control" id="emailAddress">
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password">
          </div>
          <button type="submit" class="btn btn-primary btn-block">Create account</button>
          <p class="accept-terms text-muted">By clicking “Create account”, you agree to our <a href="#">terms of service</a> and <a href="#">privacy policy</a>.</p>
          <p class="text-center text-muted">Already have an account? <a href="/login">Sign in</a></p>
        </form>
      </div>
    </div>
    <footer class="footer">
      <span class="copyright">&copy; 2018 Arhat-do.com</span>
      <ul class="footer-links list-inline float-right">
        <li class="list-inline-item"><a href="#">About</a></li>
        <li class="list-inline-item"><a href="#">Terms of Service</a></li>
        <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
      </ul>
    </footer>
  </div>
</template>

<style lang="scss" scoped>
.container {max-width: 900px;}
.register-form {margin-top: 50px; max-width: 320px;} .logo-wrapper {margin-bottom: 40px;}
.footer {width: 100%; line-height: 40px; margin-top: 50px; }
</style>
