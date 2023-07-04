
<template>

    <div>
        <h2>반응형 메시지</h2>
        <p>{{ reactiveMessage }}</p>
      <button v-on:click="addReactiveMessage">add Message</button>
    </div>
    <div>
        <h2> 노말 메시지</h2>
        <p>{{ normalMessage }}</p>
      <button v-on:click="addNormalMessage">add Message</button>
    </div>
  </template>

  <script>
  import { ref} from  'vue';
  export default {
    setup(){
        let reactiveMessage = ref('Hello reactive message');
        const addReactiveMessage = () =>{
            reactiveMessage.value = reactiveMessage.value + '!';
        }

        let normalMessage ='Hello normal messgAE';
        const addNormalMessage = () =>{
            normalMessage = normalMessage +'!';
        }

        return {
            reactiveMessage,
            normalMessage,
            addReactiveMessage,
            addNormalMessage,

                };
    },

  };

  </script>
