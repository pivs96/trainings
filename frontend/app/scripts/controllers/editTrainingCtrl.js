'use strict';

angular.module('frontendApp')
  .controller('EditTrainingCtrl', ['$scope', '$sce', '$templateRequest', '$route', '$compile', 'shareTrainingInfo',
    'FileUploader', 'editTraining',
    function($scope, $sce, $templateRequest, $route, $compile, shareTrainingInfo, FileUploader, editTraining) {

    $scope.newTraining = false;

      console.log(shareTrainingInfo.getData());
      $scope.data = angular.copy(shareTrainingInfo.getData());
      var constTrainingData = angular.copy($scope.data);
      $scope.entries = angular.copy($scope.data.entries);
      var constEntries = angular.copy($scope.data.entries);
      console.log($scope.entries);
      $scope.entryNum = 1;


      $scope.compareEntryData = function() {
        if(constEntries){
          for (var i = 0; i < constEntries.length; i++) {
            $scope.description = $scope.description + "In lecture " + (i+1) + ":" + "\n";
            angular.forEach(constEntries[i], function (value, key) {
              if (key !== 'id' && key !== 'absentees' && key !== 'trainingId' && value !== _.property(key)($scope.data.entries[i])) {
                $scope.description = $scope.description + key + " from " + value + " to " + _.property(key)($scope.entries[i]) + "\n";
              }
            });
          }
        }
      };

      $scope.compareTrainingData = function(){
        $scope.description = "Training " +  constTrainingData.name + " has been changed." + "\n" + "Changes : " + "\n";
        angular.forEach(constTrainingData, function(value, key) {
          if(key !== 'attachments' && key !== 'entries' && key !== '$promise' && key !== '$resolved' && value !== _.property(key)($scope.data)){
            $scope.description = $scope.description + key + " from " + value + " to " +  _.property(key)($scope.data) + "\n";
          }
        });
      };

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
          $scope.compareTrainingData();
          $scope.compareEntryData();
          console.log($scope.description);
          console.log(resp);
        });
      };


      var uploader = $scope.uploader = new FileUploader({
        url: 'http://localhost:8080/files/upload?trainingId' + $scope.data.trainingId
      });

      uploader.withCredentials = true;

  }]);
