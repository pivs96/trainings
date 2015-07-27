'use strict';

angular.module('frontendApp')
  .factory('UserFeedbackService',['$resource', function($resource) {
    return $resource('http://localhost:8080/user/newFeedback', {},  {
      postFeedback: {
        method: 'POST'
      }
    });
  }]);
