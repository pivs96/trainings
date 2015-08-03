angular.module('frontendApp').service('userServiceDelegate', function ($http) {
  this.updateUserInfo = function (saveType, user) {
    var _url = "http://localhost:8080/users/" + saveType;
    return $http.put(_url, {user: user});
  }
});

