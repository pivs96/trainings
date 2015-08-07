angular.module('frontendApp').factory('attendanceJournalService', ['$resource', function ($resource) {
  return $resource('http://localhost:8080/training/absentees', {}, {
    deleteAbsence: {
      method: 'DELETE',
      params: {
        absenteeId: '@absenteeId'
      }
    },
    addAbsence: {
      method: 'POST'
    },
    editAbsence: {
      method: 'PUT'
    }
  });
}]);

