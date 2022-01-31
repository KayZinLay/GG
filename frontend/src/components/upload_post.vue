<template>
<div class="container">
    <div>
      <h2>Upload Post</h2>
      <hr/>
      <label>File
        <input type="file" @change="getExcelData"/>
      </label>
      <br>
      <button v-on:click="submitFile()">Submit</button>
    </div>
  </div>
</template>
<script>
import TaskDataService from "../service/TaskDataService";

export default {
  name: "upload_post",
  data() {
    return {
      file: '',
      message: "",
    };
  },
  methods: {
    getExcelData(e){
      
    const file = e.target.files[0];
     console.log(file);
    //formData.append('file', file);
  TaskDataService.uploadTask({file}).then(function(response){
    console.log(response)
          }.bind(this))
    }
    // submitFile(){
    //         let formData = new FormData();
    //         formData.append('file', this.file);
    //         console.log("file"+formData)
    //         TaskDataService.uploadTask(formData,{
    //           // headers : {
    //           //   'Content-Type': 'multipart/form-data'
    //           // }
    //         }).then(function(){
    //       console.log('SUCCESS!!');
    //     })
    //     .catch(function(){
    //       console.log('FAILURE!!');
    //     });
    //   },

  //   handleFileUpload(){
  //       this.file = this.$refs.file[0]
  // }

  }
  
  };
</script>
<style>
</style>
