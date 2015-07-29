'use strict';


angular.module('frontendApp')
  .controller('UserlistCtrl', ['$scope', 'ngDialog', 'data',
    function ($scope, ngDialog, data) {
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
    }]);


