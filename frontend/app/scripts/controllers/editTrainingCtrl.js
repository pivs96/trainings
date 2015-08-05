'use strict';

angular.module('frontendApp')
  .controller('EditTrainingCtrl', ['$scope', '$sce', '$templateRequest', '$route', '$compile', 'shareTrainingInfo',
    'FileUploader', 'editTraining',
    function($scope, $sce, $templateRequest, $route, $compile, shareTrainingInfo, FileUploader, editTraining) {

    $scope.newTraining = false;

    console.log(shareTrainingInfo.getData());
    $scope.data = shareTrainingInfo.getData();
    $scope.entries = angular.copy($scope.data.entries);
    console.log($scope.entries);
    $scope.entryNum = 1;


      $scope.addEntry = function() {
        var templateUrl = $sce.getTrustedResourceUrl('views/templates/newEntry.html');
        $templateRequest(templateUrl).then(function(template) {
          angular.element('#trainingEntries').append($compile(template)($scope));
          $scope.entryNum++;
        });
      };

      //TODO update logic
      var templateUrl = $sce.getTrustedResourceUrl('views/templates/newEntry.html');
      (function () {
        $templateRequest(templateUrl).then(function(template) {
          for(var i = 1; i < $scope.entries.length; i++) {
            //$scope.entryNum = i;
            angular.element('#trainingEntries').append($compile(template)($scope));
            $scope.entryNum++;
          }
          });
      })();

      $scope.save = function() {
        editTraining.updateTraining($scope.data).$promise.then(function(resp) {
          console.log(resp);
        });
      };


      var uploader = $scope.uploader = new FileUploader({
        url: 'http://localhost:8080/files/upload?trainingId' + $scope.data.trainingId
      });

      uploader.withCredentials = true;

  }]);
