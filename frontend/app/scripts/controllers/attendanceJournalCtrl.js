angular.module('frontendApp').controller('AttendanceJournalCtrl', ['$scope', '$q', 'training', function ($scope, $q, training) {

  //TODO user real dates here
  var _beginDate = 1430450800000;
  var _endDate = 1431939600000;

  var ABSENT = 'н';
  var PRESENT = 'б';
  var NOT_ATTEND = 'x';

  $q.all({
    participants: training.getParticipants({id: $scope.trainingId}).$promise,
    participation: training.getParticipation({id: $scope.trainingId}).$promise,
    entries: training.getAbsentees({id: $scope.trainingId, beginDate: _beginDate, endDate: _endDate}).$promise
  }).then(function (values) {
    var participants = values.participants;
    var participation = values.participation;
    var entries = values.entries;

    //sort by date
    entries.sort(function (a, b) {
      var c = new Date(a.beginTime);
      var d = new Date(b.beginTime);
      return c - d;
    });

    var ajArray = [];

    participants.forEach(function (participant) {
      var newUserRow = {};
      newUserRow.userName = participant.name + " " + participant.surname;

      var newUserAttendanceArray = [];

      entries.forEach(function (entry) {
        //by default suppose, that the user was present
        var entryAttendance = {entryId: entry.id, state: PRESENT};

        //check whether the user was registered to training for this entry
        for (var i = 0; i < participation.length; i++) {
          if (participation[i].userId == participant.id) {
            if (participation[i].beginDay > entry.beginTime || participation[i].endDay < entry.beginTime) {
              entryAttendance.state = NOT_ATTEND;
            }
            break;
          }
        }

        //check list with absentees for this entry
        var absenteesForEntry = entry.absentees;
        for (var j = 0; j < absenteesForEntry.length; j++) {
          if (absenteesForEntry[j].userId == participant.id) {
            //found that user was absent
            entryAttendance.state = ABSENT;
            entryAttendance.reason = absenteesForEntry[j].reason;
            break;
          }
        }
        newUserAttendanceArray.push(entryAttendance);
      });
      newUserRow.attendance = newUserAttendanceArray;
      ajArray.push(newUserRow);
    });

    $scope.entries = entries;
    $scope.ajArray = ajArray;
  });

}]);

