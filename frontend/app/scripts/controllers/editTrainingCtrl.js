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

      //CALLBACKS

      uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
      };
      uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
      };
      uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
      };
      uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
      };
      uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
      };
      uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
      };
      uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
      };
      uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
      };
      uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
      };
      uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
      };
      uploader.onCompleteAll = function() {
        console.info('onCompleteAll');
      };

      console.info('uploader', uploader);

      //END_CALLBACKS


  }]);
