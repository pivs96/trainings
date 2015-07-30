angular.module('frontendApp').factory('userService', function ($resource) {
  return $resource('http://localhost:8080/user?id=:id', {id: '@id'}, {
    update: {
      url: 'http://localhost:8080/trainings/users/:type',
      method: 'PUT' // this method issues a PUT request
    }
  });
});


