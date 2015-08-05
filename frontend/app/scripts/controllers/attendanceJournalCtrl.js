angular.module('frontendApp').controller('AttendanceJournalCtrl', ['$scope', 'training', function ($scope, training) {

  training.getEntries({id: $scope.trainingId}, function (data) {
    data.forEach(function (entry) {
      entry.beginTime = new Date(Number(entry.beginTime));
    });
    $scope.entries = angular.copy(data);

  });

  training.getParticipants({id: $scope.trainingId}, function (data) {
    $scope.participants = angular.copy(data);
  });

  training.getParticipation({id: $scope.trainingId}, function (data) {
    $scope.participation = angular.copy(data);
  });

  //TODO user real dates here
  // var _beginDate = 1432501200000;
  //var _endDate = 1432846800000;
  // training.getAbsentees({id: $scope.trainingId, beginDate: _beginDate, endDate: _endDate}, function(data){
  // $scope.absentees = angular.copy(data);
  //});
}]);
