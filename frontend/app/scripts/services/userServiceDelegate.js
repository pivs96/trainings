angular.module('frontendApp').service('userServiceDelegate', function ($http) {
  this.updateUserInfo = function (saveType, user) {
    var _url = "http://localhost:8080/users/" + saveType;
    return $http.put(_url, user);
  };
  this.getUserByFeedbackId = function (id) {
    var _url = "http://localhost:8080/user/byFeedback?id=" + id;
    return $http.get(_url);
  };
  this.getPage = function (pageStart, number, tableState) {
    return $http.get('http://localhost:8080/users/pages/'+pageStart+'?size='+number+'&state='+tableState);
  };
  this.getPageCount = function(start, number) {
    return $http.get('http://localhost:8080/users/pages/count/'+start+'?size='+number);
  };
});

