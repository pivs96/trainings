'use strict';

angular.module('frontendApp').factory('trainingParticipants',['$resource',
  function($resource) {
    return $resource('http://localhost:8080/trainings/training/participants?id=:id', {id: '@id'}, {
      getTrainingInfo: {
        method: 'GET',
        isArray: true
      }

    });
  }]);
