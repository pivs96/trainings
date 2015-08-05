'use strict';

angular.module('frontendApp')
  .controller('CreateTrainingCtrl', ['$scope', '$rootScope', '$localStorage', '$compile', '$templateRequest', '$sce',
    'FileUploader' ,'createTraining', 'userlist',
    function($scope, $rootScope, $localStorage, $compile, $templateRequest, $sce, FileUploader, createTraining, userlist) {


    $scope.data = {};
    $scope.data.entries = [];

    $scope.data.trainerId = $localStorage.userData.id;

    $scope.entryNum = 1;

    $scope.me = $localStorage.userData.name;

    $scope.data.repeating = false;

    $scope.newTraining = true;

    $scope.removeEntry = function($event) {
      angular.element($event.currentTarget).parents('.entry').remove();
      var index = angular.element($event.currentTarget).parents('.entry').children('[index]').attr('index');
      if(index == $scope.entryNum - 1) {
        $scope.entryNum--;
      } else {
        $scope.data.entries[index] = null;
      }
    };

    $scope.$watch('data.repeating', function(newVal, oldVal) {
      if(oldVal !== newVal) {
        $scope.data.begin = null;
        $scope.data.end = null;
        angular.element('.entry').remove();
        $scope.entryNum = 1;

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
      var create = new createTraining();
      create.data = angular.copy($scope.data);
      console.log(create.data);
      for(var i = 0; i < create.data.entries.length; i++) {
        create.data.entries[i].beginTime = create.data.entries[i].beginTime.valueOf();
        create.data.entries[i].endTime = create.data.entries[i].endTime.valueOf();
      }
      if($scope.data.repeating) {
        create.data.begin = create.data.begin.valueOf();
        create.data.end = create.data.end.valueOf();
      }
      createTraining.save({},$scope.data).$promise.then(function(resp){
        $scope.uploadUrl = 'http://localhost:8080/files/upload?trainingId=' + resp.id;
        uploader.uploadAll();
      });

    };


      var uploader = $scope.uploader = new FileUploader();
      uploader.withCredentials = true;



      //CALLBACKS
      uploader.onBeforeUploadItem = function(item) {
        item.url = $scope.uploadUrl;
      };
      //END_CALLBACKS

      $scope.users = [];
      if($rootScope.isAdmin() && _.isEmpty($scope.users)) {
        userlist.getUserList(function(resp) {
          $scope.users = angular.copy(resp);
        })
      }
      else {
        $scope.users = $localStorage.userData;
      }

    }]);
