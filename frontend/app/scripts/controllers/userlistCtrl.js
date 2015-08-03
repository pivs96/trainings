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
        userService.setUserId(user.id);
        $location.path('/userprofile');
      }
    }]);


