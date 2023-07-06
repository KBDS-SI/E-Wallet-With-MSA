import Vue from 'vue';
import { createWebHistory, createRouter } from 'vue-router'
import Home from '../views/Home.vue'
// import Info from '../views/Home2.vue';

// Vue.use(VueRouter);
const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router