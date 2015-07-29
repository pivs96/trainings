'use strict';

angular.module('frontendApp').controller('UserProfileCtrl', ['$scope', 'userService', function ($scope, userService) {
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
    //TODO remove this line after userService.updateUserInfo starts work
    this.editMode = false;
  }

  function successUpdateUser(){
    this.editMode = false;
  }
  }]);



