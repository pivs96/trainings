'use strict';

angular.module('frontendApp')
  .factory('postAttachLinks',['$resource', function($resource) {
    return $resource('http://localhost:8080/training/attachments', {}, {

    });
  }]);
