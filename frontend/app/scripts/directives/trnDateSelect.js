'use strict';

angular.module('frontendApp')
  .directive('trnDateSelect', function() {
    return {
      restrict: 'E',
      templateUrl: 'views/templates/dateSelect.html',
      scope: {
        entry: '=',
        req: '='
      },
      controller: ['$scope', function($scope) {

        $scope.today = function() {
          $scope.entry = new Date();
        };

        $scope.clear = function () {
          $scope.entry = null;
        };

        $scope.toggleMin = function() {
          $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function($event) {
          $event.preventDefault();
          $event.stopPropagation();

          $scope.opened = true;
        };

        $scope.dateOptions = {
          formatYear: 'yy',
          startingDay: 1
        };

        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];

        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        var afterTomorrow = new Date();
        afterTomorrow.setDate(tomorrow.getDate() + 2);
        $scope.events =
          [
            {
              date: tomorrow,
              status: 'full'
            },
            {
              date: afterTomorrow,
              status: 'partially'
            }
          ];


        $scope.getDayClass = function(date, mode) {
          if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0,0,0,0);

            for (var i=0;i<$scope.events.length;i++){
              var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

              if (dayToCheck === currentDay) {
                return $scope.events[i].status;
              }
            }
          }

          return '';
        };
      }],
      link: function(scope, element, attrs) {
        scope.$watch('req', function(newVal, oldVal) {
          if(newVal !== oldVal) {
            scope.entry = new Date();
          }
        });
      }
    };
  });
