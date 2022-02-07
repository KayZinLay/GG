import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: "/",
            name: "login",
            component: () => import("./components/login"),
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
            path: "/user/:id",
            name: "User",
            component: () => import("./components/User"),
        },
        {
            path: '/users',
            name: 'Users',
            component: () => import("./components/Users")
        },
        {
            path: '/logout',
            name: "logout",
            component: () => import("./components/login")
        },
        {
            path: '/uploadPost',
            name: "upload_post",
            component: () => import("./components/upload_post")
        },
        {
            path: '/nav',
            name: "navigation",
            component: () => import("./components/navigation")
        }
    ]
});

/**
 * This is to handle and check authentication for routing.
 */
//  router.beforeEach((to, from, next) => {
//     const loggedIn = store.getters.isLoggedIn;
//     if (!loggedIn && to.name != "login") {
//         return next("/login");
//     }
//     next();
// });


export default router;