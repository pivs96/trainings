'use strict';

angular.module('frontendApp')
  .factory('trainerlist',['$resource', function($resource) {


    return $resource('http://localhost:8080/users/trainers', {}, {
      getUserList: {
        method: 'GET',
        isArray: true
      }
    });
}]);
