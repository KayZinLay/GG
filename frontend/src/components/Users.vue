<template>
  <div class="container">
    <h3>All Users</h3>
<div v-if="message" class="alert alert-success">{{ this.message }}
  </div>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
           
            <th>Name</th>
            <th>Email</th>
            <th>Created User</th>
            <th>Phone</th>
            <th>Date of Birth</th>
            <th>Address</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" v-bind:key="user.id">
          
            <td >{{ user.name }}</td>
            <td>{{ user.email }}</td>
            <td>{{ user.name}}</td>
            <td>{{ user.phone }}</td>
            <td>{{ user.dob }}</td>
            <td>{{ user.address }}</td>
            <td>
  <button class="btn btn-danger" v-on:click="deleteUser(user.id)">
                Delete
              </button>
            </td>
            <td>
  <button class="btn btn-warning" v-on:click="updateUser(user.id)">
                Update
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="row">
<button class="btn btn-success" v-on:click="addUser()">Add</button>
      </div>
      <div class="row">
        <input type="text" class="form-control" placeholder="Name" v-model="name"/>
        <input type="text" class="form-control" placeholder="Email" v-model="email"/>
        <button class="btn btn-primary" v-on:click="searchUser()">Search</button>
      </div>
    </div>
  </div>
</template>


<script>
import UserDataService from "../service/UserDataService";

export default {
  name: "Users",
  data() {
    return {
      users: [],
      name: '',
      email: '',
      createFrom: '',
      createTo: '',
      message: "",
      search_data:"",
      searchedUsers:"", 
      searchQuery: "",
      toISODate:""
    };
  },
  computed : {
    
  },
  methods: {
    refreshUsers() {
      UserDataService.retrieveAllUsers().then((res) => {
        this.users = res.data;
      });
    },
    addUser() {
      this.$router.push(`/user/-1`);
    },
    updateUser(id) {
      this.$router.push(`/user/${id}`);
    },
    deleteUser(id) {
      UserDataService.deleteUser(id).then(() => {
        this.refreshUsers();
      });
    },
    searchUser() {
       UserDataService.findBySearchData(
         this.name,
         this.email
         )
        .then(response => {
          this.users = response.data;
          console.log(this.users)
        })
    },
    
  },
  created() {
    this.refreshUsers();
  },
};
</script>