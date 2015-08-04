angular.module('frontendApp')
  .factory('eventslistService', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/events', {}, {
      getEventsList: {
        method: 'GET',
        isArray: true
      }
    });
  }]);
