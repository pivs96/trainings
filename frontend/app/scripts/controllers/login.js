/**
 * Created by Natsik on 15.07.2015.
 */
angular.module('frontendApp')
  .controller('LoginCtrl',['$scope', '$cookies', '$base64', '$localStorage', '$rootScope', '$http', '$location', 'AuthenticationService',
    function ($scope, $cookies, $base64, $localStorage, $rootScope, $http, $location, AuthenticationService) {
    $scope.$storage = $localStorage;

    $scope.credentials = {};

    $scope.login = function () {
      AuthenticationService.Login($scope.credentials, function (response) {
        if (response) {
          AuthenticationService.SetCredentials(response.name, response.details.sessionId, true);
          if($rootScope.locationPath !== "/login" && $rootScope.locationPath){
            $location.path($rootScope.locationPath);
          } else {
            $location.path("/");
          }
        } else {
          $location.path("/login");
        }
      });
    }
  }]);

var user = {
  email: "",
  password: ""
};

// maybe use in some way

//user.email = $localStorage.email || "example@gmail.com";
//this.user = user;
//this.login = function () {
//  alert("This functionality in progress");
//
//  var rememberMeCheckbox = document.getElementById("rememberMe");
//  if (rememberMeCheckbox && rememberMeCheckbox.type == "checkbox" && rememberMeCheckbox.checked) {
//    $localStorage.email = this.user.email;
//  }
//  //TODO clear form after success
//}
//
//this.clearLocalStorage = function () {
//  delete $localStorage.email;
//}
