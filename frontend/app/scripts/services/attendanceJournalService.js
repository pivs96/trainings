angular.module('frontendApp').service('attendanceJournalService', function ($http) {
  this.deleteAbsence = function (absenteeId, successCallback) {
    var _url = "http://localhost:8080/training/absentees?absenteeId=" + absenteeId;
    return $http.delete(_url).then(successCallback);
  };
  this.addAbsence = function (absentee, successCallback) {
    var _url = "http://localhost:8080/training/absentees";
    return $http.post(_url, absentee).then(successCallback);
  }

  this.editAbsence = function (absentee, successCallback) {
    var _url = "http://localhost:8080/training/absentees";
    return $http.put(_url, absentee).then(successCallback);
  }
});
