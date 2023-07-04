
<template>
    <form id="ewalletFormData" method = "post">
      <p>userId</p>
      <input type="text" name="userId" v-model="userId">
      <p>amt</p>
      <input type="text" name="amt"  v-model="amt">
    </form>
    <button class="button1" type="submit" @click="ewalletPost">전자지갑생성</button>
  </template>
  <script>
  import Axios from 'axios';
  import {ref} from 'vue'
  export default {
    name: 'ewalletForm',
    data () {
      const userId = ref("ntj362");
      const amt = ref(0);
      return {
        userId,
        amt,
      }
    },
    methods : {
      ewalletPost(){
        let jsonData = {
          "userId" : this.userId,
          "amt" : this.amt,
        }
        Axios.post('http://192.168.61.190:8000/ewallet-service',  JSON.stringify(jsonData), {
          headers: {
            "Content-Type": `application/json`,
          },
        })
          .then((res)=>{
            console.log(res);
          })
          .catch((err) =>{
            console.log(err);
          }).finally(()=>{
            console.log("finally");
          });
      }
    },
  }
   </script>
<style scoped>
.button1 {width: 250px;}
</style>