'use strict';

angular.module('frontendApp').factory('trainingInfo',['$resource',
  function($resource) {
    return $resource('http://localhost:8080/trainings/training?id=:id', {id: '@id'}, {
      getTrainingInfo: {
        method: 'GET'
      }

    });
  }]);
