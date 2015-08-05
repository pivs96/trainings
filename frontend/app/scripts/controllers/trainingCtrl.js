'use strict';

angular.module('frontendApp')
  .controller('TrainingCtrl', ['$route', '$scope', '$location', '$localStorage', 'ngDialog', 'training',
    'shareTrainingInfo', 'UserFeedbackService',
    function ($route, $scope, $location, $localStorage, ngDialog, training, shareTrainingInfo, UserFeedbackService) {

      $scope.feedbacks = [];
      $scope.training = {};
      $scope.participants = [];
      $scope.entries = [];

      $scope.trainingId = $route.current.params.trainingId;
      $scope.rating = 0;
      $scope.ratings = {
        current: 1,
        max: 5
      };

      $scope.checkDate = function(date) {
        return date < new Date;
      };

      $scope.askFeedback = function (userId, $event) {
        // TODO add new "Ask feedback" Form. See http://chewbacca.myjetbrains.com/youtrack/issue/App-41
        UserFeedbackService.askUserFeedback({userId: userId, trainingId:$route.current.params.trainingId}, function(resp) {
          console.log('DONE');
        });
        $event.stopPropagation();
      };

      $scope.leaveUserFeedbackDialog = function (userId, $event,userName) {
        ngDialog.open({
          template: "views/popups/leaveUserFeedback.html",
          controller: 'LeaveFeedbackCtrl',
          resolve: {
            trainingData: function () {
              return {
                userName: userName,
                userId: userId
              }
            }
          }
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
          controller: 'WaitListCtrl'
        })
      };

      $scope.leaveTrainingFeedback = function (trainingName) {
        ngDialog.open({
          template: "views/popups/leaveTrainingFeedback.html",
          controller: 'LeaveFeedbackCtrl',
          resolve: {
            trainingData: function() {
              return {
                trainingName : trainingName,
                trainingId : $scope.training.id
              }
            }
          }
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

      $scope.cancel = function() {
        training.cancel({id: $route.current.params.trainingId}, function (resp) {
          console.log(resp);
        });
      };

      $scope.deleteFeedback = function(fid) {
        training.deleteFeedback({feedbackId: fid}, function(resp) {
          training.getFeedbacks({id: $route.current.params.trainingId}, function (resp) {
            $scope.feedbacks = angular.copy(resp);
          });
        });
      };

      $scope.register = function() {
        training.register({uid: $localStorage.userData.id, id: $route.current.params.trainingId}, function(resp) {
          $scope.registrated = !$scope.registrated;
        });
      };

      $scope.unregister = function() {
        training.unregister({uid: $localStorage.userData.id, id: $route.current.params.trainingId}, function(resp) {
          $scope.registrated = !$scope.registrated;
        });
      };

    }]);
