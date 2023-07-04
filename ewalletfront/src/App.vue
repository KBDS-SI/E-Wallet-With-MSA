
<template>
  <div>
    <button v-on:click="increment">click {{ state.count }}</button>
  </div>
</template>

<script>
import { reactive} from  'vue';
export default {
  setup(){
    const state = reactive({
      count : 0,
    })
    return {
      state,
    };
  },

};

</script>
