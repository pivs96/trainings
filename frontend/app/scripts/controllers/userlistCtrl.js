'use strict';


angular.module('frontendApp')
  .controller('UserlistCtrl', ['$scope', 'ngDialog', 'data', 'userService', '$location',
    function ($scope, ngDialog, data, userService, $location) {
      $scope.users = data;

      $scope.openCreatePopup = function (type) {
        ngDialog.open({
          template: "views/popups/createExternal.html",
          controller: 'CreateExternalUserCtrl',
          resolve: {
            type: function () {
              return type;
            }
          }
        });
      };

      $scope.rowClickHandler = function (user){
        //todo set user.id here
        userService.setUserId('1');
        $location.path('/userprofile');
      }
    }]);


