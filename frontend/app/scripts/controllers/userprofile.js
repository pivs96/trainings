'use strict';

angular.module('frontendApp')
  .controller('UserProfileCtrl',['$scope','ngDialog', function ($scope,ngDialog) {
    $scope.leaveTrainingFeedback = function(){
      ngDialog.open({
        template: "views/popups/leaveTrainingFeedback.html",
        controller: 'LeaveFeedbackCtrl'
      })
    }
    $scope.leaveUserFeedback = function(){
      ngDialog.open({
        template: "views/popups/leaveUserFeedback.html",
        controller: 'LeaveFeedbackCtrl'
      })
    }
      $scope.openCreatePopup = function(type) {
          $rootScope.type = type;
          ngDialog.open({
              template: "../../views/popups/createExternal.html",
              controller: 'CreateExternalCtrl'
          });
      };


  }]);

