<template>
  <div>
    <h3>User Create</h3>
    <div class="container">
      <form @submit="validateAndSubmit">
        <div v-if="errors.length">
          <div
            class="alert alert-danger"
            v-bind:key="index"
            v-for="(error, index) in errors"
          >
            {{ error }}
          </div>
        </div>
        <fieldset class="form-group">
          <label>Name</label>
      <input type="text" class="form-control" v-model="name" />
        </fieldset>
        <fieldset class="form-group">
          <label>Email</label>
      <input type="email" v-model="email" class="form-control" required />
        </fieldset>
        <fieldset class="form-group">
          <label>Password</label>
          
       <input type="password" class="form-control" v-model="password" />
        </fieldset>
             <fieldset class="form-group">
          <label>Confirm Password</label>
       <input type="password" class="form-control" v-model="confirm_password" />
        </fieldset>
        <fieldset class="form-group">
          <label>Phone</label>
       <input type="text" class="form-control" v-model="phone" />
        </fieldset>
         <fieldset class="form-group">
          <label>Address</label>
       <input type="text" class="form-control" v-model="address" />
        </fieldset>
        <fieldset class="form-group">
          <label>Type</label>
       <select class="form-control" v-model="type">
            <option>--- Select ---</option>
            <option value="0">Admin</option>
            <option value="1">User</option>
        </select>
        </fieldset>
            <fieldset class="form-group">
          <label>Date of Birth</label>
       <input type="date" class="form-control" v-model="dob" />
        </fieldset>
        <button class="btn btn-success" type="submit">Save</button>
        <button class="btn btn-secondary" type="submit" @click="clear()">Clear</button>
      </form>
    </div>
  </div>
</template>
<script>
import UserDataService from "../service/UserDataService";

export default {
  name: "User",
  data() {
    return {
      name: "",
      email: "",
      password: "",
      confirm_password:"",
      dob: '2017-07-04',
      address:"",
      phone:"",
      type:"",
      errors: [],
    };
    
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  methods: {
    refreshUserDetails() {
      UserDataService.retrieveUser(this.id).then((res) => {
        this.name = res.data.name;
        this.email = res.data.email;
        this.address = res.data.address;
        this.phone = res.data.phone;
        this.dob = res.data.dob;
      });
    },
      validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.name) {
        this.errors.push("Enter Name");
      } 
      if (!this.email) {
       
        this.errors.push("Enter Email");
      } 
      if (!this.type) {
        this.errors.push("Select Type");
      }
      if (!this.password) {
        this.errors.push("Enter Password");
      }
      if (!this.confirm_password) {
        this.errors.push("Enter Confirm Password");
      }
      if ( this.password != this.confirm_password) {
        this.errors.push("Password and Confirm Password must be same")
      }
      if (!this.address) {
        this.errors.push("Enter Address");
      }
      if (!this.phone) {
        this.errors.push("Enter Phone");
      }
      if (!this.dob) {
        this.errors.push("Enter Date of Birth");
      }
      if (this.errors.length === 0) {
        
        if (this.id == -1) {
          UserDataService.createUser({
            name: this.name,
            email: this.email,
            password: this.password,
            address: this.address,
            phone:this.phone,
            type:this.type,
            dob:this.dob,
          }).then(() => {
            this.$router.push("/users");
          });
        } else {
           UserDataService.updateUser(this.id, {
            id: this.id,
            name: this.name,
            email: this.email,
            phone:this.phone,
            address:this.address,
            password:this.password,
            type:this.type,
            dob:this.dob,
          }).then(() => {
            this.$router.push("/users");
          });
        }
      }
    },
    clear() {
      this.name = ''
      this.email = ''
      this.password = ''
      this.confirm_password = ''
      this.type = ''
      this.phone = ''
      this.address = ''
      this.dob = ''

    },
  },
  created() {
    this.refreshUserDetails();
  },
};
</script>