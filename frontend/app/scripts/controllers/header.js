/**
 * Created by Natsik on 11.07.2015.
 */
angular.module('frontendApp')
  .controller('HeaderCtrl', ['$location','$scope','$http','$rootScope','AuthenticationService','userService',
    function ($location, $scope, $http, $rootScope, AuthenticationService, userService) {

    this.goToUserProfile = function (){
      //TODO add logged in user Id here
      userService.setUserId('1');
      $location.path('/userprofile');
    };

    $scope.logout = function() {
      $http.get('http://localhost:8080/logout',{})
        .finally(function(data){
          AuthenticationService.ClearCredentials();
          $location.path("/login");
        });
    };

      $scope.isLoginPage = function(){
        var url = $location.path();
        return url !== "/login";
      };

  }]);
