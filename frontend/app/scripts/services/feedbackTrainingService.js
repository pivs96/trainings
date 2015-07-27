'use strict';

angular.module('frontendApp')
  .factory('TrainingFeedbackService',['$resource', function($resource) {
    return $resource('http://localhost:8080/training/newFeedback', {}, {
      postFeedback: {
        method: 'POST'
      }
    });
  }]);
