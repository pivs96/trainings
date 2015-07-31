'use strict';

angular.module('frontendApp').controller('UserProfileCtrl', ['$scope', 'userService', 'appConstants', function ($scope, userService, appConstants) {
  //TODO remove to const
  var EXT_VISITOR = 'EXTERNAL_VISITOR';
  var EXT_TRAINER = 'EXTERNAL_TRAINER';
  $scope.editMode = false;
  $scope.languagesList = appConstants.LANGUAGES;

  //TODO add real userId here
  var userId = '2';
  userService.get({id:userId}, function(user) {
    $scope.selectedUser = user;
    $scope.isUserExternal = $scope.selectedUser.role == EXT_TRAINER || $scope.selectedUser.role == EXT_VISITOR;
  });

  userService.getMentoringTrainings({id: userId}, function(mentoringTrainings) {
    $scope.mentoringTrainingsList = mentoringTrainings;
  });

  userService.getVisitingTrainings({id: userId}, function(visitingTrainings) {
    $scope.visitingTrainingsList = visitingTrainings;
  });

  $scope.startEdit = function () {
    $scope.editMode = true;
  };

  $scope.saveChanges = function () {
    $scope.editMode = false;
    var saveType;
    if ($scope.selectedUser.role == EXT_TRAINER) {
      saveType = 'editTrainer';
    }else if ($scope.selectedUser.role == EXT_VISITOR){
      saveType = 'editVisitor';
    }else{
      alert("Users with role EXTERNAL_TRAINER or EXTERNAL_VISITOR can be updated");
      return;
    }
    userService.update({type: saveType}, $scope.selectedUser);
  }
}]);



