'use strict';

angular.module('frontendApp')
  .factory('SingleEventService',['$resource', function($resource) {
    return $resource('http://localhost:8080/events/watched', {id : '@id'}, {
      update: {
        method: 'PUT'
      }
    });
  }]);

