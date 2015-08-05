'use strict';

angular.module('frontendApp')
  .factory('UserFeedbackService',['$resource', function($resource) {
    return $resource('http://localhost:8080/user/newFeedback', {},  {
      postFeedback: {
        method: 'POST'
      },

      askUserFeedback: {
        url: 'http://localhost:8080/training/userFeedback/?',
        params: {
          userId: '@userId',
          trainingId: "@trainingId"
        },
        method: 'POST'
      }
    });
  }]);
