<template>
    <div class="about">
        <form @submit.prevent="login">
            <div class="mb-3">
                <label class="form-label" for="userId">userId</label>
                <input type="text" class="form-control" id="userId" name="userId" placeholder="userId" v-model="user.userId" required>
            </div>
            <div class="mb-3">
                <label class="form-label" for="pwd">pwd</label>
                <input type="password" class="form-control" id="pwd" name="pwd" placeholder="pwd" v-model="user.pwd" required>
            </div>
            <div class="mb-3">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="checkRemember">
                    <label class="form-check-label" for="checkRemember">Remember me</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Log In</button>
        </form>
        <br>
        <div>
            <button @click="hello" class="btn btn-primary">Hello</button>
        </div>
        <br>
        <div>
            <button @click="healthcheck" class="btn btn-primary">health-check</button>
        </div>
        <br>
        <div>
            <button @click="dd" class="btn btn-primary">dd</button>
        </div>
        <br>
        <div>
            <button @click="ss" class="btn btn-primary">ss</button>
        </div>
        <br>
        <br>
        <br>
        <form @submit.prevent="userLogin">
            <div class="mb-3">
                <label class="form-label" for="userId">userId</label>
                <input type="text" class="form-control" id="userId" name="userId" placeholder="userId" v-model="user.userId" required>
            </div>
            <div class="mb-3">
                <label class="form-label" for="pwd">pwd</label>
                <input type="password" class="form-control" id="pwd" name="pwd" placeholder="pwd" v-model="user.pwd" required>
            </div>
            <div class="mb-3">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="checkRemember">
                    <label class="form-check-label" for="checkRemember">Remember me</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Log In</button>
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
                pwd: ''
            }
        };
    },
    methods: {
        login() {
            axios.post('http://192.168.61.252:8082/login', this.user)
                .then(response => {
                    console.log(response.data);
                    // 로그인 성공 시 추가 동작을 수행합니다.
                })
                .catch(error => {
                    console.error(error);
                    // 로그인 실패 시 오류 처리를 수행합니다.
                });
        },
        userLogin() {
            axios.post('http://192.168.61.252:8082/userLogin', this.user)
                .then(response => {
                    console.log(response.data);
                    // 로그인 성공 시 추가 동작을 수행합니다.
                })
                .catch(error => {
                    console.error(error);
                    // 로그인 실패 시 오류 처리를 수행합니다.
                });
        },
        hello() {
            axios.get('http://192.168.61.252:8082/hello')
                .then(response => {
                    console.log(response.data);
                    // API 호출 성공 시 실행할 로직
                })
                .catch(error => {
                    console.error(error);
                    // API 호출 실패 시 실행할 로직
                });
        },
        healthcheck() {
            axios.get('http://192.168.61.252:8082/health-check')
                .then(response => {
                    console.log(response.data);
                    // API 호출 성공 시 실행할 로직
                })
                .catch(error => {
                    console.error(error);
                    // API 호출 실패 시 실행할 로직
                });
        },
        dd() {
            axios.get('http://192.168.61.252:8082/dd')
                .then(response => {
                    console.log(response.data);
                    // API 호출 성공 시 실행할 로직
                })
                .catch(error => {
                    console.error(error);
                    // API 호출 실패 시 실행할 로직
                });
        },
        ss() {
            axios.get('http://192.168.61.252:8082/ss')
                .then(response => {
                    console.log(response.data);
                    // API 호출 성공 시 실행할 로직
                })
                .catch(error => {
                    console.error(error);
                    // API 호출 실패 시 실행할 로직
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