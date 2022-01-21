import Vue from 'vue'
import App from './App.vue'
import router from './routes';
import Vuelidate from 'vuelidate';
import Axios from 'axios'

Vue.config.productionTip = false

Vue.prototype.$http = Axios

const token = localStorage.getItem('token')

if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}

Vue.use(Vuelidate);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
