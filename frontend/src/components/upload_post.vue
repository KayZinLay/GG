<template>
<div>
    <h3>Upload Post</h3>
    <div class="container">
      <form id="importForm">
      <fieldset class="form-group">
          <label>Browse</label>
      <input type="file" class="form-control" />
        </fieldset>
        <button class="btn btn-success" v-on:click="uploadTask()">Upload</button>
      </form>
    </div>
</div>
</template>
<script>
// import TaskDataService from "../service/TaskDataService";

export default {
  name: "upload_post",
  data() {
    return {
      tasks: [],
      message: "",
    };
  },
  methods: {
    uploadTask() {
      // console.log("aaa")
      // let import_data = document.querySelector('#importForm');

      // let data = new FormData(import_form);
      // this.$axios.post('/api/importExcel', data)
      // TaskDataService.createTask()
      //   .then((response) => {
      //     this.$router.push("/tasks");
      //   })
    }
  }
  
  };
</script>
<style>
</style>
