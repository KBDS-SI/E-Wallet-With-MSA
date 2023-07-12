<template>
  <div class="about">
        <form @submit.prevent="createUser">
            <div class="mb-3">
                <label class="form-label" for="userId">userId</label>
                <input type="text" class="form-control" id="userId" placeholder="userId" v-model="user.userId" required>
            </div>
            <div class="mb-3">
                <label class="form-label" for="pwd">pwd</label>
                <input type="password" class="form-control" id="pwd" placeholder="pwd" v-model="user.pwd" required>
            </div>
            <div class="mb-3">
                <label class="form-label" for="pwd">username</label>
                <input type="text" class="form-control" id="pwd" placeholder="username" v-model="user.username" required>
            </div>
            <div class="mb-3">
                <label class="form-label" for="pwd">phone</label>
                <input type="text" class="form-control" id="phone" placeholder="phone" v-model="user.phone" required>
            </div>
            <div class="mb-3">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="checkRemember">
                    <label class="form-check-label" for="checkRemember">Remember me</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Join</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      user: {
        userId: '',
        pwd: '',
        username: '',
        phone: ''
      }
    };
  },
  methods: {
    createUser() {
      axios.post('http://192.168.61.252:8082/join', this.user)
        .then(response => {
          console.log(response.data);
          // 회원가입 성공 시 추가 동작을 수행합니다.
        })
        .catch(error => {
          console.error(error);
          // 회원가입 실패 시 오류 처리를 수행합니다.
        });
    }
  }
};
</script>
 
<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>