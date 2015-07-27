/**
 * Created by Natsik on 11.07.2015.
 */
angular.module('frontendApp')
  .controller('HeaderCtrl', ['$location','$scope','$http','$rootScope','AuthenticationService',
    function ($location, $scope,$http, $rootScope, AuthenticationService) {

    this.companyName = companyName;

    this.goToUserProfile = function (){
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
