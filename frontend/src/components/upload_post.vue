<template>
  <div>
    <div class="container">
        <form @submit.prevent="onSubmit">
            <div class="form-group">
                <input type="file" @change="onFileUpload">
            </div>
            <div class="form-group">
                <input type="text" v-model="name" placeholder="Name" class="form-control">
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-block btn-lg">Upload File</button>
            </div>
        </form>
    </div>    
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "upload_post",
  data() {
    return {
       FILE: null,
       name:""
    };
  },
   methods: {
    onFileUpload (event) {
      this.FILE = event.target.files[0]
      console.log(this.FILE)
    },
    onSubmit() {
      // upload file
      const formData = new FormData()
      formData.append('file', this.FILE, this.FILE.name)
      formData.append('name', this.name)
      axios.post('http://localhost:9091/api/v1/uploadCSV', formData, {
      }).then(() => {
        this.$router.push("/tasks")
      })
    }  
    }
  };
</script>
<style>
</style>