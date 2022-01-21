<template>
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">SCM Bulletin Board</a><br/><br/>
      <!-- <router-link to="/user" class="navbar-brand">User Create</router-link>| -->
      <router-link to="/users" class="navbar-brand">Users</router-link>|
      <router-link to="/tasks" class="navbar-brand">Posts</router-link>|
      <span v-if="isLoggedIn"> | <a @click="logout">Logout</a></span>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "app",
};
</script>

<style>
@import url(https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css);

</style>
