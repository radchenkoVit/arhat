import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/views/LoginPage'
import RegisterPage from '@/views/RegisterPage'
import HomePage from '@/views/HomePage'
import AdminPage from '@/views/AdminPage'
import NotFoundPage from '@/views/NotFound'
import Role from '@/model/role'
import userService from '@/services/test/userservice'
import ForbiddenPage from '@/views/Forbidden'

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
    path: '/forbidden',
    name: 'forbidden',
    component: ForbiddenPage
  }, {
    path: '/404',
    component: NotFoundPage
  }, {
    path: '*', redirect: '/404'
  }]
}
)

router.beforeEach((to, from, next) => {
  const { roles } = to.meta
  const authificated = userService.authificatedSubject ? userService.authificatedSubject.value : null
  const currentUserRole = userService.userRoles ? userService.userRoles.replace(/"/g, '') : null
  const currentUserToken = userService.userToken

  if (authificated === false) {
    return next({ path: '/login' })
  }

  // eslint-disable-next-line eqeqeq, will be okey if values Falthy type
  if (currentUserToken === false || currentUserRole === false) {
    return next({ path: '/login' })
  }

  if (roles) {
    if (!roles.includes(currentUserRole)) {
      return next({ name: 'forbidden' })
    }
  }

  next()
})

export default router
