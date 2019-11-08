import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/views/LoginPage'
import RegisterPage from '@/views/RegisterPage'
import HomePage from '@/views/HomePage'
import AdminPage from '@/views/AdminPage'
import NotFoundPage from '@/views/NotFound'
import Role from '@/model/role'
import userService from '@/services/test/userservice'
import UnathorizedPage from '@/views/UnathorizedPage'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [{
    path: '/',
    name: 'LoginPage',
    component: LoginPage
  }, {
    path: '/login',
    component: LoginPage
  }, {
    path: '/registration',
    name: 'RegisterPage',
    component: RegisterPage
  }, {
    path: '/home',
    name: 'HomePage',
    component: HomePage,
    meta: { roles: [Role.ADMIN, Role.USER] }
  }, {
    path: '/admin',
    name: 'AdminPage',
    component: AdminPage,
    meta: { roles: [Role.ADMIN] }
  }, {
    path: '/unathorized',
    name: 'unathorized',
    component: UnathorizedPage
  }, {
    path: '/404',
    component: NotFoundPage
  }, {
    path: '*', redirect: '/404'
  }]
}
)

router.beforeEach((to, from, next) => {
  // TODO, HOW to get info from vuex
  const { roles } = to.meta
  const authificated = userService.authificatedSubject ? userService.authificatedSubject.value : null
  const currentUserRole = userService.userRoles ? userService.userRoles.replace(/"/g, '') : null
  const currentUserToken = userService.userToken

  console.log('authificated: ' + authificated)

  if (authificated === false) {
    console.log('forward to login')
    return next({ path: '/login' })
  }
  console.log('currentUserToken: ' + currentUserToken)
  console.log('currentUserRole: ' + currentUserRole)

  // eslint-disable-next-line eqeqeq, will be okey if values Falthy type
  if (currentUserToken == false || currentUserRole == false) {
    console.log('forward to login')
    return next({ path: '/login' })
  }

  if (roles) {
    if (!roles.includes(currentUserRole)) {
      console.log('forward to unathorized')
      return next({ name: 'unathorized' })
    }
  }

  next()
})

export default router
