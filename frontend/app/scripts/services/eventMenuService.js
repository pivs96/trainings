'use strict';

angular.module('frontendApp').factory('EventService',['$resource', function($resource) {
    return $resource('http://localhost:8080/events/unwatched', {index : '@index'}, {
      getEventList: {
        method: 'GET',
        isArray: true
      }
    });
  }]);
