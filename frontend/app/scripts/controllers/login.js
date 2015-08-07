/**
 * Created by Natsik on 15.07.2015.
 */
angular.module('frontendApp')
  .controller('LoginCtrl',['$scope', '$cookies', '$base64', '$localStorage', '$rootScope', '$http', '$location', 'AuthenticationService', 'UserDataService',
    function ($scope, $cookies, $base64, $localStorage, $rootScope, $http, $location, AuthenticationService, UserDataService) {
    $scope.$storage = $localStorage;

    $scope.credentials = {};

    $scope.login = function () {
      AuthenticationService.Login($scope.credentials, function (response) {
        if (response) {
          AuthenticationService.SetCredentials(response.name, response.details.sessionId, true);
          $scope.userData = UserDataService.getUserNameAndId({login : $localStorage.userName}, function(response) {
            $localStorage.userData = response;
            $location.path("/myTainings");
          });
          if($rootScope.locationPath !== "/login" && $rootScope.locationPath){
            $location.path($rootScope.locationPath);
          }
        } else {
          $location.path("/login");
        }
      });
    }
  }]);
