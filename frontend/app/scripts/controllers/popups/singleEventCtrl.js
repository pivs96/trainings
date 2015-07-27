'use strict';

angular.module('frontendApp')
  .controller('EventCtrl', ['$scope', '$http', '$location', 'userService', 'data', 'userServiceDelegate', 'SingleEventService',
    function($scope, $http, $location, userService, data, userServiceDelegate, SingleEventService) {
     $scope.singleevent = data;

      $scope.userId = 0;
     userServiceDelegate.getUserByFeedbackId($scope.singleevent.subjectId).then(function(resp){
       $scope.userId =  resp.data;
     });

      $scope.redirectToEntity = function(singleevent){
        if(singleevent.eventType == "TRAINING"){
          $location.path("/training/editTraining/"+singleevent.subjectId);
        }
        if(singleevent.eventType == "USER_FEEDBACK"){
          $scope.setWatched();
          userService.setRedirectUserId($scope.userId);
          $location.path("/userprofile/"+$scope.userId);
        }
        if(singleevent.eventType == "TRAINING_FEEDBACK"){
          $scope.setWatched();
          $location.path("/training/"+singleevent.subjectId);
        }
        $scope.closeThisDialog();
      };

      $scope.setWatched = function() {
        $scope.singleevent.watched = true;
        var event = new SingleEventService();
        _.extend(event, {
          id : $scope.singleevent.id,
          description : $scope.singleevent.description,
          watched: $scope.singleevent.watched,
          subjectId: $scope.singleevent.subjectId,
          eventType: $scope.singleevent.eventType,
          date : $scope.singleevent.date
        });
        SingleEventService.update(event);
        $scope.closeThisDialog();
      };

      $scope.isTrainingEvent = function(eventType) {
        return eventType == "TRAINING"
      };

      $scope.isFeedbackEvent = function(eventType) {
        return eventType == "USER_FEEDBACK" || eventType == "TRAINING_FEEDBACK"
      };

    }
  ]);

