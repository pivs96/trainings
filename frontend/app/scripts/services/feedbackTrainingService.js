'use strict';

angular.module('frontendApp')
  .factory('TrainingFeedbackService',['$resource', function($resource) {
    return $resource('http://localhost:8080/trainings/training/newFeedback/?id:=@id', {id: '@id'}, {
      postFeedback: {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }
      }
    });
  }]);
