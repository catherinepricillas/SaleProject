/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 var config = {
        apiKey: "AIzaSyA3v_LfsOeOLrYBMBQ36ZpywDkhlv8uhX8",
        authDomain: "web-quickstart-d6657.firebaseapp.com",
        databaseURL: "https://web-quickstart-d6657.firebaseio.com",
        storageBucket: "web-quickstart-d6657.appspot.com",
        messagingSenderId: "419652492436"
      };
  firebase.initializeApp(config);
  var messaging = firebase.messaging();
  messaging.requestPermission().then(function(){
      console.log("Have permission");
      var token =messaging.getToken().then(function(token){
          console.log("Token: ",token);
          var tokenTemp = document.getElementById('token');
    	  tokenTemp.innerHTML = token;
          document.cookie="fcm_token="+token;
      }).catch(function(err){
         console.log(err); 
      });
     
  }).catch(function(err){
      console.log("Not have permission: ",err);
  });
