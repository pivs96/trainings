'use strict';

angular.module('frontendApp').controller('TrainingListCtrl', [ '$scope', 'TrainingList', 'data',
  function($scope, TrainingList, data) {
    $scope.trainingsList = data;
  }]);
