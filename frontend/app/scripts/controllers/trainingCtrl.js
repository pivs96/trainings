'use strict';

angular.module('frontendApp')
  .controller('TrainingCtrl', ['$route', '$scope', '$location', 'ngDialog', 'training', 'shareTrainingInfo',
    function ($route, $scope, $location, ngDialog, training, shareTrainingInfo) {

      $scope.feedbacks = [];
      $scope.training = {};
      $scope.participants = [];
      $scope.entries = [];


      $scope.rating = 0;
      $scope.ratings = {
        current: 0,
        max: 5
      };

      $scope.checkDate = function(date) {
        return date < new Date;
      };

      $scope.askFeedback = function (userId, $event) {
        // TODO add new "Ask feedback" Form. See http://chewbacca.myjetbrains.com/youtrack/issue/App-41
        console.log(userId);
        $event.stopPropagation();
      };

      $scope.leaveUserFeedbackDialog = function (userId, $event) {
        ngDialog.open({
          template: "views/popups/leaveUserFeedback.html",
          controller: 'LeaveFeedbackCtrl'
        });
        $event.stopPropagation();
      };

      $scope.openUserpage = function (userId) {
        if ($scope.isAdmin()) {
          console.log(userId);
          $location.path('/userprofile/' + userId);
        }
      };

      $scope.openCreatePopup = function(type) {
        ngDialog.open({
          template: "views/popups/createExternal.html",
          controller: 'CreateExternalUserCtrl',
          resolve: {
            type: function() {
              return type;
            }
          }
        });
      };

      $scope.openWaitList = function() {
        ngDialog.open({
          template: "views/popups/waitList.html",
          controller: 'WaitListCtrl',
          resolve: {
            training: function() {
              return _.extend(
                _.pick($scope.training, 'membersCountMax', 'membersCount'),
                $scope.participants);
            }
          }
        })
      };

      $scope.leaveTrainingFeedback = function () {
        ngDialog.open({
          template: "views/popups/leaveTrainingFeedback.html",
          controller: 'LeaveFeedbackCtrl'
        })
      };

      $scope.openInfo = function() {
        if(_.isEmpty($scope.training)) {
          training.getInfo({id: $route.current.params.trainingId}, function(resp){
            $scope.training = angular.copy(resp);
            $scope.ratings.current = $scope.training.rating;
            training.getEntry({id: $route.current.params.trainingId}, function(resp){
              _.extend($scope.training, {entry: angular.copy(resp)});
            });
            training.getTrainer({id: $scope.training.trainerId}, function(resp) {
              _.extend($scope.training, {trainer: angular.copy(resp)});
            });
            training.getAttachments({id: $route.current.params.trainingId}, function(resp) {
              _.extend($scope.training, {attachments: angular.copy(resp)});
            })
          });
          training.checkParticipation({uid: '2', trainingId: $route.current.params.trainingId}, function(resp) {

            $scope.registrated = (angular.copy(resp)[0] !== 'N');
          });
        }
      };

      $scope.openParticipantsList = function() {
        if(_.isEmpty($scope.participants)) {
          training.getParticipants({id: $route.current.params.trainingId}, function(resp){
            $scope.participants = angular.copy(resp);
          });
        }
      };

      $scope.openFeedbacks = function() {
        if (_.isEmpty($scope.feedbacks)) {
          //TODO: make placeholder which is shown while request is ongoing
          training.getFeedbacks({id: $route.current.params.trainingId}, function (resp) {
            $scope.feedbacks = angular.copy(resp);
          });
        }
      };

      $scope.openJournal = function() {

      };

      $scope.openEntries = function() {
        if(_.isEmpty($scope.entries)) {
          training.getEntries({id: $route.current.params.trainingId}, function (resp) {
            $scope.entries = angular.copy(resp);
          });
        }
      };


      $scope.registrated = false;

      $scope.edit = function() {
        if(_.isEmpty($scope.entries)) {
          training.getEntries({id: $route.current.params.trainingId}, function (resp) {
            $scope.entries = angular.copy(resp);
            shareTrainingInfo.setData(_.extend($scope.training, {entries: $scope.entries}));
            $location.path('/training/' + $route.current.params.trainingId + '/edit');
          });
        }
        else {
          shareTrainingInfo.setData(_.extend($scope.training, {entries: $scope.entries}));
          $location.path('/training/' + $route.current.params.trainingId + '/edit');
        }
      };



      $scope.deleteFeedback = function(fid) {
        training.deleteFeedback({feedbackId: fid}, function(resp) {
          console.log('WIP');
        });
      };

      $scope.register = function() {
        training.register({uid: 2, id: $route.current.params.trainingId}, function(resp) {
          $scope.registrated = !$scope.registrated;
        });
      };

      $scope.unregister = function() {
        $scope.registrated = !$scope.registrated;
      };

    }]);
