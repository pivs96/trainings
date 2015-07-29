'use strict';

angular.module('frontendApp')
  .controller('CreateTrainingCtrl', ['$scope', '$compile', '$templateRequest', '$sce',
    function($scope, $compile, $templateRequest, $sce) {

    $scope.data = {};

    $scope.entryNum = 1;

    $scope.repeating = false;

    $scope.days = ['Monday','Tuesday','Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

    $scope.addEntry = function() {
      var templateUrl = $sce.getTrustedResourceUrl('views/templates/newEntry.html');
      $templateRequest(templateUrl).then(function(template) {
        angular.element('#trainingEntries').append($compile(template)($scope));
        $scope.entryNum++;
      });
    };

    $scope.daySelect = function($event) {
      console.log($event);
    }
  }]);
