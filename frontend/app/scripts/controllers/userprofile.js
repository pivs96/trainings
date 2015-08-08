'use strict';

angular.module('frontendApp')
  .controller('UserProfileCtrl', ['$scope', 'ngDialog', 'userService', 'userServiceDelegate', 'appConstants',
    'userProfileData',
    function ($scope, ngDialog, userService, userServiceDelegate, appConstants, userProfileData) {
  $scope.editMode = false;
  $scope.selectedUser = userProfileData;
  $scope.isUserExternal = $scope.selectedUser.role == appConstants.EXT_TRAINER || $scope.selectedUser.role == appConstants.EXT_VISITOR;
  $scope._url = 'http://localhost:8080/user/stats?userId=' + userProfileData.id

  if($scope.selectedUser.role != appConstants.EXT_VISITOR) {
    userService.userResource.getMentoringTrainings(function (mentoringTrainings) {
      $scope.mentoringTrainingsList = mentoringTrainings;
    });
  }

  if($scope.selectedUser.role != appConstants.EXT_TRAINER) {
    userService.userResource.getVisitingTrainings(function (visitingTrainings) {
      $scope.visitingTrainingsList = visitingTrainings;
    });
  }

  $scope.startEdit = function () {
    $scope.editMode = true;
  };

  $scope.saveChanges = function () {
    $scope.editMode = false;
    var saveType;
    if ($scope.selectedUser.role == appConstants.EXT_TRAINER) {
      saveType = 'editTrainer';
    } else if ($scope.selectedUser.role == appConstants.EXT_VISITOR) {
      saveType = 'editVisitor';
    } else {
      alert("Users with role EXTERNAL_TRAINER or EXTERNAL_VISITOR can be updated");
      return;
    }
    userServiceDelegate.updateUserInfo(saveType, $scope.selectedUser);
  };


  $scope.wathchFeedback = function(){
    ngDialog.open({
      template: "views/popups/userFeedbacks.html",
      controller: 'UserFeedbackCtrl',
      size: "350px"
    });
  }

}]);



