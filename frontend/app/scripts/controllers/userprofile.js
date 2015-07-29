'use strict';

angular.module('frontendApp').controller('UserProfileCtrl', ['$scope', 'ngDialog', 'userService', function ($scope, ngDialog, userService) {
    var self = this;
    this.editMode = false;
    //TODO add real userId here
    userService.getUserInfo(1255, successGetUser);

    function successGetUser(result){
      if(result) {
        self.user = result;
      }
    }

  this.startEdit = function () {
    this.editMode = true;
  }

  this.saveChanges = function () {
    userService.updateUserInfo(self.user, successUpdateUser);
  }

  function successUpdateUser(){
    this.editMode = false;
  }

    $scope.leaveTrainingFeedback = function(){
      ngDialog.open({
        template: "views/popups/leaveTrainingFeedback.html",
        controller: 'LeaveFeedbackCtrl'
      })

    };
    $scope.leaveUserFeedback = function () {
      ngDialog.open({
        template: "views/popups/leaveUserFeedback.html",
        controller: 'LeaveFeedbackCtrl'
      })
    };
    $scope.openCreatePopup = function(type) {
          ngDialog.open({
              template: "views/popups/createExternal.html",
              controller: 'CreateExternalUserCtrl',
              resolve: {
                type: function() {
                  return type;
                }
              }
          });
      };
  }]);



