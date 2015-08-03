'use strict';

angular.module('frontendApp')
  .controller('EditTrainingCtrl', ['$scope', '$sce', '$templateRequest', '$compile', 'shareTrainingInfo',
    function($scope, $sce, $templateRequest, $compile, shareTrainingInfo) {


    console.log(shareTrainingInfo.getData());
    $scope.data = shareTrainingInfo.getData();
    $scope.entries = angular.copy($scope.data.entries);
    console.log($scope.entries);
    $scope.entryNum = 1;


      $scope.addEntry = function() {
        var templateUrl = $sce.getTrustedResourceUrl('views/templates/newEntry.html');
        $templateRequest(templateUrl).then(function(template) {

          angular.element('#trainingEntries').append($compile(template)($scope));

        });
      };

    for(var i = 1; i < $scope.entries.length; i++) {
      var templateUrl = $sce.getTrustedResourceUrl('views/templates/newEntry.html');
      $templateRequest(templateUrl).then(function(template) {
        $scope.entryNum = i;
        angular.element('#trainingEntries').append($compile(template)($scope));

      });
    }






  }]);
