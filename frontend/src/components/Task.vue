<template>
  <div>
    <h3>Create Post</h3>
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
          <label>Title</label>
      <input type="text" class="form-control" v-model="title" />
        </fieldset>
        <fieldset class="form-group">
          <label>Description</label>
      <input type="text" class="form-control" v-model="description" />
        </fieldset>
        <button class="btn btn-success" type="submit">Save</button>
      </form>
    </div>
  </div>
</template>

<script>
import TaskDataService from '../service/TaskDataService';

export default {
  name: "Task",
  data() {
    return {
      title: "",
      description: "",
      errors: [],
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  methods: {
    refreshTaskDetails() {
      TaskDataService.retrieveTask(this.id).then((res) => {
        this.title = res.data.title;
        this.description = res.data.description;
      });
    },
    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.title) {
        this.errors.push("Enter Title");
      } 
      if (!this.description) {
        this.errors.push("Enter Description");
      } 
      if (this.errors.length === 0) {
        if (this.id == -1) {
          TaskDataService.createTask({
            title: this.title,
            description: this.description,
          }).then(() => {
            this.$router.push("/tasks");
          });
        } else {
          TaskDataService.updateTask(this.id, {
            id: this.id,
            title: this.title,
            description: this.description,
          }).then(() => {
            this.$router.push("/tasks");
          });
        }
      }
    },
  },
  created() {
    this.refreshTaskDetails();
  },
};
</script>