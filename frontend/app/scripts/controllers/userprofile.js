'use strict';

angular.module('frontendApp').controller('UserProfileCtrl', ['$scope', 'userService', 'appConstants', function ($scope, userService, appConstants) {
  //TODO remove to const
  var EXT_VISITOR = 'EXTERNAL_VISITOR';
  var EXT_TRAINER = 'EXTERNAL_TRAINER';
  $scope.editMode = false;
  $scope.languagesList = appConstants.LANGUAGES;

  //TODO add real userId here
  userService.get({id:'2'}, function(user) {
    $scope.selectedUser = user;
    $scope.isUserExternal = $scope.selectedUser.role == EXT_TRAINER || $scope.selectedUser.role == EXT_VISITOR;
  });

  $scope.startEdit = function () {
    $scope.editMode = true;
  }

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
    userService.update({type: 'saveType'}, $scope.selectedUser);
  }
}]);



