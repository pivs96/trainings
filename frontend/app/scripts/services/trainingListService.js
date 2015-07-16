'use strict';

angular.module('frontendApp').factory('TrainingList',['$resource',
  function($resource) {
    return $resource('http://localhost:8080/trainings', {}, {
      getTrainingList: {method: 'GET',
        isArray: true
      }
    });
  }]);
