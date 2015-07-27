'use strict';

angular.module('frontendApp')
  .factory('UserDataService',['$resource', function($resource) {
    return $resource('http://localhost:8080/user/info?name=:login', {login: '@login'}, {
      getUserNameAndId: {
        method: 'GET'
      }
    });
  }]);

