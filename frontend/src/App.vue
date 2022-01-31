<template>
  <div id="app">
    <navigation /> 
    <login />
    <router-view />
  </div>
</template>

<script>
 import Navigation from './components/navigation.vue';
 export default {
        components: { 
        Navigation
        },
    name: "app",

};
</script>

<style>
@import url(https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css);

</style>