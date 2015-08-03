'use strict';

angular.module('frontendApp').controller('TrainingListCtrl', ['$location', '$scope', 'TrainingList', 'data',
  function($location, $scope, TrainingList, data) {
    $scope.trainingsList = data;

    $scope.openTraining = function(trainingId) {
      $location.path('/training/' + trainingId);
    };
  }]);
