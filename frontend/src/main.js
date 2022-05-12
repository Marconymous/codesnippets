import { createApp } from 'vue'
import App from './App.vue'
import axios from "axios";
import VueAxios from "vue-axios";
import VueRouter from "vue-router"

const Home = { template: '<div>Home</div>' }
const About = { template: '<div>About</div>' }

// 2. Define some routes
// Each route should map to a component.
// We'll talk about nested routes later.
const routes = [
    { path: '/', component: Home },
    { path: '/about', component: About },
]

const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes
})

const app = createApp(App)
app.use(router)
app.use(VueAxios, axios)
app.mount('#app')
