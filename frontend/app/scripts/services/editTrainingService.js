'use strict';

angular.module('frontendApp')
  .factory('editTraining', ['$resource', function($resource) {
    return $resource('http://localhost:8080/training', {}, {
      updateTraining: {
        method: 'PUT'
      },
      deleteAttachment: {
        url: 'http://localhost:8080/training/attachment?id=:aid',
        method: 'DELETE',
        params: {
          aid: '@aid'
        }
      }
    })
  }]);
