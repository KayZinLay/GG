import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: "/",
            name: "Tasks",
            component: () => import("./components/Tasks"),
        },
        {
            path: "/tasks",
            name: "Tasks",
            component: () => import("./components/Tasks"),
        },
        {
            path: "/task/:id",
            name: "Task",
            component: () => import("./components/Task"),
        },
        {
            path: '/user/-1',
            name: 'User',
            component: () => import("./components/User")
        },
        {
            path: '/users',
            name: 'Users',
            
            component: () => import("./components/Users")
        },
        {
            path: '/login',
            name: "login",
            component: () => import("./components/login")
        },
        {
            path: '/uploadPost',
            name: "upload_post",
            component: () => import("./components/upload_post")
        },
        {
            path: '/profile',
            name: "profile",
            component: () => import("./components/profile")
        }
    ]
});

export default router;