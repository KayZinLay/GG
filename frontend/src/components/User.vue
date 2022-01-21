<template>
  <div>
    <h3>User</h3>
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
      <input type="text" class="form-control" v-model="email" />
        </fieldset>
        <fieldset class="form-group">
          <label>Password</label>
          
       <input type="password" class="form-control" v-model="password" v-validate="'required|min:6|max:35|confirmed'" ref="password"/>
        </fieldset>
             <fieldset class="form-group">
          <label>Confirm Password</label>
       <input type="password" class="form-control" v-model="confirm_password" v-bind:rules="Required" />
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
         <fieldset class="form-group">
          <label>Profile</label>
       <input type="file" class="form-control" >
        </fieldset>
        <button class="btn btn-success" type="submit">Save</button>
        <button class="btn btn-back" type="submit">Back</button>
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
      profile_photo:"",
      dob:"",
      address:"",
      phone:"",
      type:"",
      errors: [],

      Required: [
         value => !!value || "The password field is required.",
      ],
       
    };
    
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  methods: {
      onChange(event) {
        this.profile_photo = event.target.files[0]
    },
    refreshUserDetails() {
      UserDataService.retrieveUser(this.id).then((res) => {
        this.name = res.data.name;
        this.email = res.data.email;
        this.address = res.data.address;
        this.phone = res.data.phone;
        this.dob = res.data.dob;
        this.created_at = res.data.created_at;
        this.updated_at = res.data.updated_at;
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
            profile_photo:this.profile_photo
          }).then(() => {
            this.$router.push("/users");
          });
        } 
      }
    },
  },
  created() {
    this.refreshUserDetails();
  },
};
</script>