'use strict';

angular.module('frontendApp')
  .controller('CreateTrainingCtrl', ['$scope', '$rootScope', '$compile', '$templateRequest', '$sce',
    'FileUploader' ,'createTraining', 'training',
    function($scope, $rootScope, $compile, $templateRequest, $sce, FileUploader, createTraining, training) {

    $scope.data = {};
    $scope.data.entries = [];
      //STAB
      $scope.data.trainerId = 1;

    $scope.entryNum = 1;

    $scope.repeating = false;

    $scope.$watch('repeating', function(newVal, oldVal) {
      if(oldVal !== newVal) {
        $scope.data.begin = null;
        $scope.data.end = null;
        while(angular.element(".entry").length > 0) {
          angular.element(".entry").last().remove();
          $scope.entryNum--;
        }

      }
    });


    $scope.addEntry = function() {
      var templateUrl = $sce.getTrustedResourceUrl('views/templates/newEntry.html');
      $templateRequest(templateUrl).then(function(template) {
        angular.element('#trainingEntries').append($compile(template)($scope));
        $scope.entryNum++;
      });
    };



    $scope.save = function() {

      $scope.data.status = ($rootScope.isAdmin()) ? "APPROVED" : "DRAFTED";
      $scope.data.repeating = $scope.repeating;
      var create = new createTraining();
      create.data = angular.copy($scope.data);
      createTraining.save($scope.data).$promise.then(function(resp){});

    };


      $scope.cancel = function() {
        training.cancel({id: $route.current.params.trainingId}, function (resp) {
          console.log(resp);
        });
      };

      var uploader = $scope.uploader = new FileUploader({
        url: 'http://localhost:8080/files/upload'
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
