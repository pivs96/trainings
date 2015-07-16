'use strict';

angular.module('frontendApp')
  .controller('LeaveFeedbackCtrl', ['$scope','TrainingFeedbackService','UserFeedbackService', function($scope,TrainingFeedbackService,UserFeedbackService) {

    $scope.rating = 0;
    $scope.ratings = {
      current: 3,
      max: 5
    };

    // temp data
    $scope.levles = [
      { Name: 'PreInt'},
      { Name: 'Int'},
      { Name: 'UppInt'},
      { Name: 'Ad'}
    ];

    // temp data
    $scope.marks = [
      { grade: 0},
      { grade: 1},
      { grade: 2},
      { grade: 3},
      { grade: 4}
    ];

    $scope.leaveTrainingFeedback = function() {
      var userId =1;
      var trainingId = 123;
      var feed = new TrainingFeedbackService();
      feed.id = trainingId;
      feed.userId = userId;
      feed.effectiveness = $scope.$$childHead.ratings.current;        //not good
      feed.understandable = $scope.understandable;
      feed.interesting = $scope.interesting;
      feed.newKnowledge = $scope.newKnowledge;
      feed.studyWithTrainer = $scope.studyWithTrainer;
      feed.recommend = $scope.recommend;
      feed.otherInfo = $scope.feedbackInput;
      feed.date = new Date();
      feed.$save();

      $scope.$parent.feedbackInput = "";
      $scope.closeThisDialog();
    };

    $scope.leaveUserFeedback = function() {
      var userId =123; //id of user on we write feedback
      var feedbackerId = 1; // id of feedbacker
      var feed = new UserFeedbackService();
      feed.userId = userId;
      feed.feedbackerId = feedbackerId;
      feed.attendanc = $scope.attendance;
      feed.attitude = $scope.attitude;
      feed.questions = $scope.questions;
      feed.interested = $scope.interested;
      feed.focusOnResult = $scope.focusOnResult;
      feed.englishLevel = $scope.$$childHead.ddlLevle;      ////not good
      feed.grade = $scope.$$childTail.ddlMark;      //not goodo
      feed.otherInfo = $scope.feedbackInput;
      feed.date = new Date();
      feed.$save();

      $scope.$parent.feedbackInput = "";
      $scope.closeThisDialog();
    };
  }]);
