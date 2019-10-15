import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/views/LoginPage'
import RegisterPage from '@/views/RegisterPage'
import HomePage from '@/views/HomePage'
import AdminPage from '@/views/AdminPage'
import NotFoundPage from '@/views/NotFound'
import Role from '@/model/role'
import UserService from '@/services/test/userservice'

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
    path: '/401',
    name: 'NotFoundPage',
    component: NotFoundPage

  }]
}
)

router.beforeEach((to, from, next) => {
  const { roles } = to.meta
  const authificated = UserService.authificatedSubject ? UserService.authificatedSubject.value : null
  const currentUserRole = UserService.userRoles ? UserService.userRoles.replace(/"/g, '') : null
  const currentUserToken = UserService.userToken

  if (authificated === false) {
    return next({ path: '/login' })
  }

  // eslint-disable-next-line eqeqeq, will be okey if values Falthy type
  if (currentUserToken === false || currentUserRole === false) {
    return next({ path: '/login' })
  }

  if (roles) {
    if (!roles.includes(currentUserRole)) {
      return next({ path: '/401' })
    }
  }

  next()
})

export default router
