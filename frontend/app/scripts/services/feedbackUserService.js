'use strict';

angular.module('frontendApp')
  .factory('UserFeedbackService',['$resource', function($resource) {
    return $resource('http://localhost:8080/user/newFeedback/?userId:=@userId', {userId: '@userId'},  {
      postFeedback: {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }
      }
    });
  }]);
