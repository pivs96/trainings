'use strict';

angular.module('frontendApp')
    .factory('createExternalListener', ['$resource', function($resource) {
        return $resource('http://localhost:8080/training/register_new_visitor?trainingId=:trainingId',
          {trainingId: '@trainingId'}, {
            post: {
                method: 'POST'
            }
        });
    }]);
