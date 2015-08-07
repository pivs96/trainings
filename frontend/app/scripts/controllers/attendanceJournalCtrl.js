angular.module('frontendApp').controller('AttendanceJournalCtrl', ['$scope', '$localStorage', '$q', 'training', 'attendanceJournalService', 'ngDialog', function ($scope, $localStorage, $q, training, attendanceJournalService, ngDialog) {

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
      newUserRow.userId = participant.id;
      var newUserAttendanceArray = [];

      entries.forEach(function (entry) {
        //by default suppose, that the user was present
        var entryAttendance = {entryId: entry.id, state: PRESENT};

        //check whether the user was registered to training for this entry
        for (var i = 0; i < participation.length; i++) {
          if (participation[i].userId == participant.id) {
            if (participation[i].beginDay > entry.beginTime || (participation[i].endDay != null && participation[i].endDay < entry.beginTime)) {
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
            entryAttendance.id = absenteesForEntry[j].id;
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

  $scope.cellClickHandler = function (attend, user) {
    if (!isActionAllowed(user)) {
      return;
    }
    $scope.user = user;
    $scope.attend = attend;
    switch (attend.state) {
      case ABSENT:
        //delete or edit absence
        if (isListener(user.id)) {
          //listener cannot edit or delete absence
          return;
        }
        showEditAbsenceDialog();
        break;
      case PRESENT:
        //add absence
        showAddAbsenceDialog();
        break;
      case NOT_ATTEND:
        //is not editable
        return;
        break;
      default:
        return;
    }
  };

  function isActionAllowed(user) {
    //admin, trainer for this training and listener for his records can edit journal
    if ($rootScope.isAdmin() || isMentor() || isListener(user.id)) {
      return true;
    }
    return false;
  }

  //TODO take this method from trainingCtrl
  function isMentor() {
    return $localStorage.userData.id === $scope.training.trainer.id;
  };

  function isListener(editUserId) {
    return $localStorage.userData.id === editUserId;
  };


  function showEditAbsenceDialog() {
    ngDialog.open({
      template: "views/popups/editAbsenceInfo.html",
      controller: ['$scope', 'attendanceJournalService', function ($scope, attendanceJournalService) {
        $scope.reason = $scope.attend.reason;
        $scope.editState = 'edit';

        $scope.editAbsence = function () {
          if ($scope.editState == 'edit') {
            attendanceJournalService.editAbsence(getAbsenceDTO($scope.attend.id, $scope.user.userId, $scope.attend.entryId, $scope.reason), function (result) {
              $scope.attend.reason = $scope.reason;
            });
          } else {
            attendanceJournalService.deleteAbsence($scope.attend.id, function (result) {
              delete $scope.attend.reason;
              delete $scope.attend.id;
              $scope.attend.state = PRESENT;
            });
          }
          $scope.closeThisDialog();
        }
      }],
      scope: $scope
    })

  };

  function showAddAbsenceDialog() {
    ngDialog.open({
      template: "views/popups/addAbsenceInfo.html",
      controller: ['$scope', 'attendanceJournalService', function ($scope, attendanceJournalService) {
        $scope.reason;
        $scope.addAbsence = function () {
          attendanceJournalService.addAbsence(getNewAbsenceDTO($scope.user.userId, $scope.attend.entryId, $scope.reason), function (result) {
            //TODO set new absence id here
            //$scope.attend.id = result.id;
            $scope.attend.reason = $scope.reason;
            $scope.attend.state = ABSENT;
          });
          $scope.closeThisDialog();
        };
      }],
      scope: $scope
    })
  };

  function getAbsenceDTO(_id, _userId, _entryId, _reason) {
    return {
      id: _id,
      userId: _userId,
      entryId: _entryId,
      reason: _reason
    }
  }

  function getNewAbsenceDTO(_userId, _entryId, _reason) {
    return {
      userId: _userId,
      entryId: _entryId,
      reason: _reason
    }
  }

}]);

