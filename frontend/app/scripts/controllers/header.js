/**
 * Created by Natsik on 11.07.2015.
 */
angular.module('frontendApp')
  .controller('HeaderCtrl', ['$location','$scope','$http','$rootScope','AuthenticationService','userService',
    function ($location, $scope, $http, $rootScope, AuthenticationService, userService) {

    this.companyName = companyName;

    this.goToUserProfile = function (){
      //TODO add logged in user Id here
      userService.setUserId('1');
      $location.path('/userprofile');
    };

    $scope.logout = function() {
      $http.post('http://localhost:8080/logout', {}).success(function() {
        AuthenticationService.ClearCredentials();
        //$location.path("/login");
      }).error(function(data) {
        AuthenticationService.ClearCredentials();
      });
    }

  }]);

var companyName = "Exadel";
