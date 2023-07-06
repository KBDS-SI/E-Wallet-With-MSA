<template>
    <div class="home">
        <a href="https://naver.com">
            {{ msg }}

        </a>
    </div>
    </template>

    <script>
    export default {
      data() {
        return {
          msg: 'Hello world222!'
        }
      }
    }
    </script>


    <style scoped>
    .home {
      font-family: Avenir, Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      margin-top: 60px;
    }
    </style>