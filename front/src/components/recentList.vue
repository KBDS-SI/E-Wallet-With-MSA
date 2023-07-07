<template>
    <div>

        <div class="container text-center">
            <div class="row header">
                <div class="col">
                송금일시
                </div>
                <div class="col">
                전자지갑ID
                </div>
                <div class="col">
                금액
                </div>
                <div class="col">
                수취인
                </div>
                <div class="col">
                메모
                </div>
            </div>
            <div class="row" v-bind:key="list.id" v-for ="item in list" >
                <div class="col" >
                    {{ item.송금일시 }}
                </div>
                <div class="col" >
                    {{ item.ewalletId }}
                </div>
                <div class="col" >
                    {{ item.amt }}
                </div>
                <div class="col" >
                    {{ item.oppoUserId }}
                </div>
                <div class="col" >
                    {{ item.memo }}
                </div>
            </div>
        </div>
        <div style=" width:100px; margin:auto; padding-top: 10%;">
            <button type="button" class="btn btn-primary" @click="searchList">조회</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { ref } from "vue";
export default{
    setup() {
        let list = ref([]);
        const url = "http://192.168.61.190:8000/remit/remitHistory"; //송금이력 조회 성공
//         예시
//         remit데이터
//         {
//     "userId":"ntj362",
//     "remitCode":"1",
//     "amt":"50000",
//     "oppoUserId":"oppoUserId",
//     "ewalletId":"12345",
//     "cancelYn":"0",
//     "finBal":"50000"

// }
        const searchList = () =>{
            axios.post(url, {
                "userId":"ntj362",    //수정해야함.
                "ewalletId":"12345",  //수정해야함.
            })
            .then(function (response) {
                console.log(response.data.length)
                list.value=[]; //초기화
                for( let index in response.data){
                    list.value.push( response.data[index])
                }
                console.log(list)

            }).catch(function (error) {
                // 오류발생시 실행
                alert("error");
            }).finally(function (){

            })

        }

        return {
            searchList,
            list,
        }
    }
}
</script>
<style scoped>
.row{
    background-color: antiquewhite;
}
.row.header{
    background-color: cyan;
}
</style>