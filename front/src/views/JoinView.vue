<template>
    <div>
        <!-- if you want to modify, plz you modify this sourse -->
        <!-- CORS 해결 못함! -->
        <form class="row g-3 align-items-center" >
            <div class="col-md-12">
                <label for="InputUserId" class="form-label">UserID</label>
                <input type="text" class="form-control" id="userId" name = "userId" v-model="userId">
            </div>
            <div class="col-12"> <!--실패..-->
                <label for="checkButton" class="form-label"></label>
                <button type="button" class="btn btn-primary" @click="dupCheck">중복체크</button>
            </div>
            <div class="col-12"> <!--요샌 비번 한번밖에안쓰던데~-->
                <label for="inputPassword" class="form-label">Password</label>
                <input type="password" class="form-control" id="pwd" name ="pwd" v-model="pwd">
            </div>
            <div class="col-12">
                <label for="InputUserName" class="form-label">userName</label>
                <input type="text" class="form-control" id="username" placeholder="홍길동" v-model="username">
            </div>
            <div class="col-12">
                <label for="inputPhone" class="form-label">Phone Number</label>
                <input type="text" class="form-control" id="phone" placeholder="01011112222" v-model="phone">
            </div>
            <div class="col-12">
            </div>
            <div class="col-md-6">
            </div>
            <div class="col-md-6">
                <button type="button" class="btn btn-primary" @click="join">가입</button>
            </div>
        </form>
    </div>
</template>

<script>
import { ref } from "vue";
import axios from 'axios';

export default{
    setup(){
        const userId = ref('');
        const pwd = ref('');
        const username = ref('');
        const phone = ref('');
        const url = "http://192.168.61.190:58235/user-service/join";

        const dupCheck = () =>{
            alert("현수가 만들예정");
        }

        const join = () =>{
            axios.post(url, {
                "userId" : userId,
                "pwd" : pwd,
                "username" : username,
                "phone" : phone
            })
            .then(function (response) {
                // response
                alert("성공");
            }).catch(function (error) {
                // 오류발생시 실행
                alert("error");
            })

        }
        return{
            userId,
            pwd,
            username,
            phone,
            dupCheck,
            join,
        }
    }
}
    // data:{
        // userId : "1",
        // password : "",
        // userName : "",
        // phone : "",
    // }
</script>
