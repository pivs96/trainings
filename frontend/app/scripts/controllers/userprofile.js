'use strict';

angular.module('frontendApp').controller('UserProfileCtrl', ['$scope', 'ngDialog', function ($scope, ngDialog) {

  this.user = {
    email: "example@gmail.com",
    name: "Alex",
    surname: "Alexis",
    phone: "12444455",
    role: "Administrator"
  };

  this.editMode = false;

  this.startEdit = function () {
    this.editMode = true;
  }

  this.saveChanges = function () {
    //TODO save changes
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

