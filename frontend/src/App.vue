<template>
  <div id="app">
    <NavBar></NavBar>
    <RouterView/>
  </div>
</template>
<style>
#app {
  background: url("./assets/background.png");
  background-repeat: no-repeat;
  background-size: 100% auto;
  position: relative;
  z-index: 1;
  overflow: hidden;
  -ms-overflow-style: none;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#app::-webkit-scrollbar {
  display: none !important;
  width: 0 !important
}
nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
<script setup>
import NavBar from "@/components/NavBar.vue";
</script>