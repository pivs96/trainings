'use strict';

angular.module('frontendApp')
  .factory('FeedbackUserService',['$resource', function($resource){
    return $resource('http://localhost:8080/user/feedbacks?userId=:id', {id: '@id'}, {
      getFeedbacks: {
        method: 'GET',
        isArray: true
      }
    });
  }]);

