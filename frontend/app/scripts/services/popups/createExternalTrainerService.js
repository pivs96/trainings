'use strict';

angular.module('frontendApp')
    .factory('createExternalTrainer', ['$resource', function($resource) {
        return $resource('http://localhost:8080/users/newTrainer', {}, {
            post: {
                method: 'POST'
            }
        });
    }]);