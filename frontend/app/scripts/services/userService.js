angular.module('frontendApp').factory('userService', function ($resource) {
  return $resource('http://localhost:8080/user?id=:id', {id: '@id'}, {
    update: {
      url: 'http://localhost:8080/users/:type',
      method: 'PUT'
    },
    getMentoringTrainings: {
      url: 'http://localhost:8080/user/mentoringTrainings?userId=:id',
      method: 'GET',
      isArray: true
    },
    getVisitingTrainings: {
      url: 'http://localhost:8080/user/visitingTrainings?userId=:id',
      method: 'GET',
      isArray: true
    }
  });
});


