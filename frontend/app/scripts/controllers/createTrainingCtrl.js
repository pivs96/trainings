'use strict';

angular.module('frontendApp')
  .controller('CreateTrainingCtrl', ['$scope', '$compile', function($scope, $compile) {

    $scope.data = {};

    $scope.entryNum = 1;

    $scope.repeating = false;

    $scope.days = ['Monday','Tuesday','Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

    $scope.addEntry = function() {
      angular.element('#trainingEntries').append($compile('<div class="col-sm-8"><div class="col-sm-offset-4"> <p class="entry-num"><b>{{translation.ENTRY}} {{::entryNum}}</b></p> </div> </div> <trn-entry></trn-entry>')($scope));
      $scope.entryNum++;
    };

    $scope.daySelect = function($event) {
      console.log($event);
    }
  }]);
