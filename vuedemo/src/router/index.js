import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import Employee from '../components/Employee.vue'
import Admin from '../components/Admin.vue'
import Rights from '../components/Rights.vue'
import Roles from '../components/Roles.vue'
import Shops from '../components/Shop.vue'
import menuClass from '../components/MenuClass.vue'
import menus from '../components/Menus.vue'
import showMenus from '../components/ShowMenus.vue'
import addPage from '../components/AddPage.vue'
import statistics from '../components/Statistics.vue'
import Code from '../components/Code.vue'
import FindPas from '../components/FindPas.vue'
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', redirect: '/login'},
    { path: '/login', component: Login},
    { path: '/code', component: Code},
    { path: '/findPas', component: FindPas},
    { 
      path: '/home', 
      component: Home, 
      redirect: '/welcome', 
      children: [
        { path: '/welcome', component: Welcome }, 
        { path: '/employees', component: Employee},
        { path: '/admins', component: Admin},
        { path: '/rights', component: Rights},
        { path: '/role', component: Roles},
        { path: '/shops', component: Shops},
        { path: '/menuClass', component: menuClass},
        { path: '/menus', component: menus},
        { path: '/addPage', component: addPage},
        { path: '/Chart', component: statistics},
        { path: '/showMenus', component: showMenus}
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if(to.path === '/findPas') return next();
  if(to.path === '/login') return next();
  if(to.path === '/code') return next();
  const tokenStr = window.sessionStorage.getItem('token')
  if(!tokenStr) return next('/login')
  next()
})

export default router