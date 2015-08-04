angular.module('frontendApp').controller('EventsListCtrl', ['$scope', 'data',
  function ($scope, data) {
    $scope.eventsList = data;
  }]);
