'use strict';

angular.module('frontendApp')
  .controller('CreateTrainingCtrl', ['$scope', '$compile', function($scope, $compile) {

    $scope.data = {};


    $scope.addEntry = function($event) {
      //TODO: make this work
      console.log(angular.element('.col-sm-7')[2].append($compile('<training-date-select></training-date-select>')));
    };

    $scope.repeating = false;

    $scope.days = ['Monday','Tuesday','Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

    $scope.daySelect = function($event) {
      console.log($event);
    }
  }]);
