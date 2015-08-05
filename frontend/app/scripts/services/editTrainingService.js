'use strict';

angular.module('frontendApp')
  .factory('editTraining', ['$resource', function($resource) {
    return $resource('http://localhost:8080/training', {}, {
      updateTraining: {
        method: 'PUT'
      }
    })
  }]);
