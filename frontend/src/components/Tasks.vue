<template>
  <div class="container">
    <h3>Post List Screen</h3>
<div v-if="message" class="alert alert-success">{{ this.message }}
  </div>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
           
            <th>Title</th>
            <th>Description</th>
            <th>Posted User</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="task in tasks" v-bind:key="task.id">
          
            <td>{{ task.title }}</td>
            <td>{{ task.description }}</td>
            <td>{{ task.create_user_id }}</td>
            <td>
        <button class="btn btn-warning" v-on:click="updateTask(task.id)">
                Update
              </button>
            </td>
            <td>
        <button class="btn btn-danger" v-on:click="deleteTask(task.id)">
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="row">
        <button class="btn btn-success" v-on:click="addTask()">Add</button>
        <button v-on:click="downloadTask()">DownLoad</button>
        <button v-on:click="uploadTask()">Upload</button>
        
            <input type="text" class="form-control" placeholder="Search by title" v-model="title"/>
            <button class="btn btn-success" v-on:click="searchTask()">Search</button>
        
      </div>
    </div>
  </div>
</template>


<script>
import TaskDataService from "../service/TaskDataService";

export default {
  name: "Tasks",
  data() {
    return {
      tasks: [],
      message: "",
    };
  },
  methods: {
    refreshTasks() {
      TaskDataService.retrieveAllTasks().then((res) => {
        this.tasks = res.data;
      });
    },
    addTask() {
      this.$router.push(`/task/-1`);
    },
    updateTask(id) {
      this.$router.push(`/task/${id}`);
    },
    deleteTask(id) {
      TaskDataService.deleteTask(id).then(() => {
        this.refreshTasks();
      });
    },
    searchTask() {
        TaskDataService.findByTitle(this.title)
        .then(response => {
          this.tasks = response.data;
        })
    },
    downloadTask() {
      TaskDataService.downloadTask()
          .then(response => {
              const url = window.URL.createObjectURL(new Blob([response.data]));
              const link = document.createElement('a');
              link.href = url;
              link.setAttribute('download', 'task.csv');
              document.body.appendChild(link);
              link.click();
          });
      },
    uploadTask() {
      this.$router.push('/uploadPost');
    },
  },
  
  created() {
    this.refreshTasks();
  },
};
</script>