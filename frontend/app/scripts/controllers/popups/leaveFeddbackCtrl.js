'use strict';

angular.module('frontendApp')
  .controller('LeaveFeedbackCtrl', ['$scope', '$rootScope', '$localStorage', 'trainingData', 'TrainingFeedbackService', 'UserFeedbackService', 'UserDataService', 'Levels','Marks',
    function($scope, $rootScope, $localStorage, trainingData, TrainingFeedbackService, UserFeedbackService, UserDataService, Levels, Marks) {

    $scope.rating = 0;
    $scope.ratings = {
      current: 3,
      max: 5
    };

    $scope.userInfo = $localStorage.userData;
      // 0 - admin, 1 - employe,  2 - external trainer, 3 - external visitor

    $scope.levels = angular.copy(Levels);
    $scope.marks  = angular.copy(Marks);

    $scope.leaveTrainingFeedback = function() {
      var feed = new TrainingFeedbackService();

      _.extend(feed, {
        trainingId : trainingData.trainingId,
        feedbacker :  $scope.userInfo,
        effectiveness: $scope.$$childHead.ratingValue,
        date : new Date(),
        eventDescription : "User " + $scope.userInfo.name + " left feedback on training " + trainingData.trainingName
      });
      _.extend(feed, $scope.entity);

      feed.$save();
      $scope.$parent.feedbackInput = "";
      $scope.closeThisDialog();
    };

    $scope.leaveUserFeedback = function() {
      var feed = new UserFeedbackService();

      _.extend(feed, {
        trainerId : $scope.userInfo.id,
        visitorId : trainingData.userId,
        date : new Date(),
        eventDescription : "Trainer " + $scope.userInfo.name + "left feedback on visitor " + trainingData.userName
      });
      $scope.entity.otherInfo = "Training name : " + trainingData.trainingName + "\n" + $scope.entity.otherInfo;
      _.extend(feed, $scope.entity);

      feed.$save();
      $scope.$parent.feedbackInput = "";
      $scope.closeThisDialog();
    };

  }]);
