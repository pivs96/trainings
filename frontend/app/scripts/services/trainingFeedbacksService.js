'use strict';

angular.module('frontendApp').factory('trainingFeedbacks',['$resource',
  function($resource) {
    return $resource('http://localhost:8080/trainings/training/feedbacks?id=:id', {id: '@id'}, {
      getTrainingInfo: {
        method: 'GET',
        isArray: true
      }

    });
  }]);
