angular.module('frontendApp').service('userService',function() {
  this.getUserInfo = function (userId, successGetUser) {
    return $.get("http://localhost:8080/user", {id: userId}, successGetUser, "json");
  }

  this.updateUserInfo = function (user, successUpdateUserInfo) {
    return $.post("http://localhost:8080/users/editTrainer", {user: user}, successUpdateUserInfo, "json");
    //TODO post editTrainer or editVisitor depending on the role
    //PUT users/editTrainer/
    //PUT users/editVisitor/
  }
});


