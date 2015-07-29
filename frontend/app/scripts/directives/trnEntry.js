'use strict';

angular.module('frontendApp')
  .directive('trnEntry', function() {
    return {
      restrict: 'E',
      templateUrl: 'views/templates/trnEntry.html',
      scope: true,
      link: function(scope, element, attrs) {

      }
    }
  });
