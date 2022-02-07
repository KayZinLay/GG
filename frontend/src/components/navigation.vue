<template>
  <div class="nav">
    <nav class="navbar navbar-dark bg-primary" id="navbar">
      <h3>SCM Bulletin Board</h3>
      <ul  class="nav justify-content-end">
        <li class="nav-item">
          <button
            class="btn btn-secondary"
            style="display: inline-block; margin-left: 10px;"
            @click="goToUsers"
          >
            Users
          </button>
        </li>
        <li class="nav-item">
          <button
            class="btn btn-secondary"
            style="display: inline-block; margin-left: 10px;"
            @click="goToPosts"
          >
            Posts
          </button>
        </li>
        <li class="nav-item">
          <button
            class="btn btn-secondary"
            style="display: inline-block; margin-left: 10px;"
            @click="goToUser"
          >
            User
          </button>
        </li>
        <!-- <li class="nav-item">
          <button
            class="btn btn-secondary"
            style="display: inline-block; margin-left: 10px;"
            @click="goToProfile"
          >
            Profile
          </button>
        </li> -->
        <li class="nav-item">
          <button
            class="btn btn-secondary"
            style="display: inline-block; margin-left: 10px;"
            @click="logout"
          >
            Logout
          </button>
        </li>
      </ul>
    </nav>
    
  </div>
</template>

<script>
export default {
  name: "navigation",
  methods: {
    logout() {
      this.$router.push("/logout");
    },
    
    goToPosts() {
      this.$router.push({ path: "/tasks" });
    },
    goToUsers() {
      this.$router.push({path: "/users"});
    },
    goToUser() {
      this.$router.push({path: "/user/-1"});
    }
  },
};
</script>

<style scoped>
#brand-title {
  font-family: "Roboto Slab", serif;
}
#navbar {
  width: 100%;
}
</style>