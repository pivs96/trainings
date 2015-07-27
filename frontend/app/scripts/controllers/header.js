/**
 * Created by Natsik on 11.07.2015.
 */
angular.module('frontendApp')
  .controller('HeaderCtrl', ['$location', '$scope', '$localStorage', '$http', '$rootScope', 'AuthenticationService', 'userService',
    function ($location, $scope, $localStorage, $http, $rootScope, AuthenticationService, userService) {

    this.goToUserProfile = function (){
      userService.setUserId($localStorage.userData.id);
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
