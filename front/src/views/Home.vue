<template>
    <div>
   <form v-on:submit.prevent="submitForm">
    <div>
      <label for="id">id:</label>
      <input id="id" type="text" v-model="id" />
    </div>
    <div>
      <label for="pass">PW:</label>
      <input id="pass" type="psassword" v-model="pass"  />
    </div>
    <button type="submit">login</button>
  </form>
  <div>
 <img
        class="kakao_btn"
        src="@/assets/kakao.png"
        @click="loginWithKakao"
    />
  </div>
    <div>
       <a :href="'https:'+'//accounts.google.com/o/oauth2/v2/auth?'+
        'scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email&'+
        'access_type=online&'+
        'include_granted_scopes=true&'+
        'response_type=code&'+
        'redirect_uri=http%3A//localhost:8000/mindwiki/GoogleOAuth&'+
        'client_id=659791765906-faeludmkkn7l8vqlk37pqlhhisu4n1hb.apps.googleusercontent.com'
 
       ">
            googleLogin
      </a>
    </div>
    </div>
</template>



<script>
import axios from 'axios';
 
export default {
  data:function(){
    return{
       id : '',
       pass : '',
    }
  },
  name: "LoginKakao",
  methods:{
      loginWithKakao() {
        const params = {
            redirectUri: "http://localhost:8000/api/kakaoLogin",
        };
        window.Kakao.Auth.authorize(params);
     },

    submitForm:function(){
      let form = new FormData()
      form.append('id', this.id) 
      form.append('pass',this.pass)

      axios.post(`http://localhost:8000/api/Login`,form).then(({ data }) => {
      
      alert(data.message);
      
    });
    
    }
  }
};
</script>