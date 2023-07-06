<template>
  <div id ="app">
    <Header>    </Header>
    <!-- <Home></Home> -->
    <Footer></Footer>
    <router-link to="/main">main</router-link>
    <router-link to="/info">info</router-link>
		<router-view></router-view>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import Header from './views/LayoutHeader.vue'
import Footer from './views/LayoutFooter.vue'



export default {
  name: 'App',
  components: {
    // HelloWorld
    Header,
    Footer
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
