'use strict';

angular.module('frontendApp')
  .factory('createTraining', ['$resource', function($resource) {
      return $resource('http://localhost:8080/trainings/newTraining/', {}, {}
      );
  }]);
