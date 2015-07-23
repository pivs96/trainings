'use strict';

angular.module('frontendApp')
    .factory('createExternalListener', ['$resource', function($resource) {
        return $resource('http://localhost:8080/trainings/training/newUser?trainingId=:trainingId', {trainingId: '@trainingId'}, {
            post: {
                method: 'POST'
            }
        });
    }]);
