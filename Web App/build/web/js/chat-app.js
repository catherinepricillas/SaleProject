        var config = {
                        apiKey: "AIzaSyA3v_LfsOeOLrYBMBQ36ZpywDkhlv8uhX8",
                        authDomain: "web-quickstart-d6657.firebaseapp.com",
                        databaseURL: "https://web-quickstart-d6657.firebaseio.com",
                        storageBucket: "web-quickstart-d6657.appspot.com",
                        messagingSenderId: "419652492436"
                      };
                firebase.initializeApp(config);
                var messaging = firebase.messaging();
                var app = angular.module('ChatApp',['ngCookies']);
   
                app.controller ('ChatCtrl',['$scope','$http','$cookies','$location',function($scope,$http,$cookies,$location){
                    
                    $scope.chatBoxShowed = false;
                    $scope.messages = [];
                    
                    $scope.token= $cookies.get('fcm_token');
                    $scope.username = $cookies.get('user_name');
                    console.log($scope.token);
                    console.log($scope.username);
                   $scope.byUserName="";
                    $scope.openChatBox = function(parms){
                       $scope.chatBoxShowed = true;
                       $scope.byUserName= parms;
                        $scope.receiver=parms;
                        console.log(parms);    
                    }
                    $scope.sendToken= function(){
                        var url = "http://localhost:8083/Chat_Service/SaveToken";
                        var parameter = {
                            token:$scope.token,
                            username:$scope.username
                        };
                        var config={
                            params:parameter
                        };
                    $http.get(url,config)
                              .success(function(data, status, headers, config) {
                                 console.log("Response from Chat Service: ",data);
                             })
                              .error(function(data, status, headers, config) {
                                console.log("sending request failed!");
                        });
                    };
                    $scope.sendToken();
                    $scope.push = function() {
                        console.log($scope.username);
                        console.log($scope.receiver);
                        console.log($scope.message);
                        var url = "http://localhost:8083/Chat_Service/SendMessage";
                        var parameter = {
                                sender:$scope.username,
                                receiver:$scope.receiver,
                                body:$scope.message
                            };
                            var config={
                                params:parameter
                            };
                            
                      $http.get(url,config)
                     .success(function(data, status, headers, config) {
                         console.log("Response from FCM: ",data);
                     })
                     .error(function(err) {
                        console.log("sending message failed!",err);
                      });
                        
                        var chat ={
                            body:$scope.message,
                            style:"me",
                            receiver:$scope.receiver
                        };
                        $scope.messages.push (chat);
                        //var messagesElement = angular.element(document.querySelector('#messages'));
                       // var dataElement = angular.element('<div/>');
                        //dataElement.attr('class','me');
                       // dataElement.text($scope.message);
                        //messagesElement.append(dataElement);
                        setTimeout(function(){
                            $scope.$apply(function(){
                        
                        var elem = document.getElementById('messages');
                        elem.scrollTop = elem.scrollHeight;
                            });
                    },100);
                        $scope.message="";
                        
                       };
                $scope.getMessage = function(){
                     messaging.onMessage(function(payload){
                      setTimeout(function(){
                         $scope.$apply(function(){
                        $scope.openChatBox(payload.data.sender);
                        $scope.receiver=payload.data.sender;
                        console.log($scope.chatBoxShowed);
                           var chat ={
                            body:payload.data.body,
                            style:"them",
                            receiver:payload.data.sender
                           };
                           $scope.messages.push (chat);   
                        //var messagesElement = angular.element(document.querySelector('#messages'));
                        //var dataElement = angular.element('<div/>');
                        //dataElement.attr('class','them');
                        //dataElement.text(payload.data.body);
                        //messagesElement.append(dataElement);
                        setTimeout(function(){
                          $scope.$apply(function(){
                                var elem = document.getElementById('messages');
                                elem.scrollTop = elem.scrollHeight;
                                    });
                            },100);
                          });
                        },10);
                    });
                   };
                 $scope.getMessage();       
                      
                }]);
                


