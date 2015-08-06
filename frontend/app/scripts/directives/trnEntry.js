'use strict';

angular.module('frontendApp')
  .directive('trnEntry', function() {
    return {
      restrict: 'E',
      templateUrl: 'views/templates/trnEntry.html',
      scope: {
        repeating: '=',
        entries: '=',
        index: '@',
        translation: '=',
        isAdmin: '&',
        newTraining: '='
      },
      controller: ['$scope', function($scope) {
        if(!$scope.newTraining && $scope.entries[$scope.index]) {
          $scope.time = new Date();
          $scope.duration = new Date();
          $scope.date = new Date($scope.entries[$scope.index].beginTime);
          $scope.time.setHours(new Date($scope.entries[$scope.index].beginTime).getHours());
          $scope.time.setMinutes(new Date($scope.entries[$scope.index].beginTime).getMinutes());
          $scope.duration.setHours(new Date($scope.entries[$scope.index].endTime).getHours() - new Date($scope.entries[$scope.index].beginTime).getHours());
          $scope.duration.setMinutes(new Date($scope.entries[$scope.index].endTime).getMinutes() - new Date($scope.entries[$scope.index].beginTime).getMinutes());
          $scope.time.setMilliseconds(0);
          $scope.time.setSeconds(0);
          $scope.duration.setMilliseconds(0);
          $scope.duration.setSeconds(0);
        }
      }],
      link: function(scope, element, attrs) {


        scope.entries[scope.index] = {};
        scope.days = ['Sunday', 'Monday','Tuesday','Wednesday', 'Thursday', 'Friday', 'Saturday'];
        scope.daySelect = function(day) {
          scope.entries[scope.index].dayOfWeek = scope.days.indexOf(day) + 1;
          angular.element(element[0]).find('#simple-btn-keyboard-nav').html(day + '<span class="caret"></span>');
        };

        scope.$watch('time', function(newVal) {
          if(newVal) {
            scope.entries[scope.index].beginTime = new Date(scope.date);
            scope.entries[scope.index].beginTime.setHours(newVal.getHours());
            scope.entries[scope.index].beginTime.setMinutes(newVal.getMinutes());
          }
        });

        scope.$watch('duration', function(newVal) {
          if(newVal) {
            scope.entries[scope.index].endTime = new Date(scope.entries[scope.index].beginTime);
            scope.entries[scope.index].endTime.setHours(scope.entries[scope.index].endTime.getHours() + newVal.getHours());
            scope.entries[scope.index].endTime.setMinutes(scope.entries[scope.index].endTime.getMinutes() + newVal.getMinutes());
          }
        });

        scope.$watch('repeating', function(newVal, oldVal) {
          if(oldVal !== newVal) {
            scope.date = null;
            scope.time = null;
            scope.duration = null;
          }
        });

        scope.$watch('city', function(newVal, oldVal) {
          if(oldVal !== newVal) {
            scope.entries[scope.index].place = newVal + ', ' + scope.unit;
          }
        });

        scope.$watch('unit', function(newVal, oldVal) {
          if(oldVal !== newVal) {
            scope.entries[scope.index].place = scope.city + ', ' + newVal;
          }
        });
      }
    }
  });
