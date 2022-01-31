<template>
 <div>
   <form class="login" @submit.prevent="login">
     <h1>Login in</h1>

     <div class="form-group">
        <label>E-Mail Address</label>
        <input required v-model="email" type="email" placeholder="E-Mail"/>
     </div>
     <div class="form-group">
        <label>Password</label>
        <input required v-model="password" type="password" placeholder="Password"/>
     </div>
     
    
     <button class="btn btn-primary btn-block">Login</button>
   </form>
 </div>
</template>

<script>

import UserDataService from '../service/UserDataService';

  export default {
    name:'login',

    data() {
      return {
        email: "",
        password: "",
        error:[]
      }
    },
    methods: {
      login() {
        console.log("login")
        UserDataService.login({
          email:this.email,
          password:this.password
        }).then(() => {
            this.error = ""
            this.$router.push({ path: '/' })
            this.$router.push({ name: 'Tasks' })
          });
      }
    }
  }
</script>