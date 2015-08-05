'use strict';

angular.module('frontendApp')
  .controller('WaitListCtrl', ['$scope', '$route', 'training', function($scope, $route, training) {

    training.getWaitList({id: $route.current.params.trainingId}, function(resp) {
      $scope.wlSize = resp.participants.length;
      $scope.participants = angular.copy(resp.participants);
    });

  }]);
