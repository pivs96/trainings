'use strict';

angular.module('frontendApp')
  .controller('UserFeedbackCtrl', ['$scope', '$http', '$localStorage', 'FeedbackUserService',
    function($scope, $http, $localStorage, FeedbackUserService) {

      $scope.userUeedbacks = [];

      FeedbackUserService.getFeedbacks({id: $localStorage.userData.id},function(resp){
        $scope.userUeedbacks = resp;
      });
    }
  ]);

