var config = {
        apiKey: "AIzaSyA3v_LfsOeOLrYBMBQ36ZpywDkhlv8uhX8",
        authDomain: "web-quickstart-d6657.firebaseapp.com",
        databaseURL: "https://web-quickstart-d6657.firebaseio.com",
        storageBucket: "web-quickstart-d6657.appspot.com",
        messagingSenderId: "419652492436"
      };
  firebase.initializeApp(config);
  var messaging = firebase.messaging();
  messaging.requestPermission()
          .then(function(){
      console.log("success");
      messaging.getToken(function(token){
          console.log(token);
      })
  });


