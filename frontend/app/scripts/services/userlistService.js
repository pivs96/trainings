'use strict';

angular.module('frontendApp')
  .factory('userlist',['$resource','$http', function($resource, $http) {


    return $resource('http://localhost:8080/users', {}, {
      getUserList: {
        method: 'GET',
        isArray: true
      }
    });
}]);
