'use strict';

angular.module('frontendApp').controller('EventMenuCtrl', [ '$scope', '$location', '$interval', '$aside', 'ngDialog', 'EventService',
  function($scope, $location, $interval, $aside, ngDialog,  EventService) {

  $scope.eventList = [];

  $scope.getUnwachedEvent = function() {
    EventService.getEventList(function (resp){
      $scope.eventList = angular.copy(resp);
    });
  };

  $interval($scope.getUnwachedEvent, 500);

  $scope.openEvent = function(event) {
    ngDialog.open({
      template: "views/popups/singleEvent.html",
      controller: 'EventCtrl',
      controllerAs: 'event',
      resolve: {
        data : function() {
          return event;
        }
      }
    })
  };

  $scope.asideState = {
    open: false
  };

  $scope.openAside = function() {
    if(!$scope.asideState.open) {
      $scope.asideState = {
        open: true
      };
    } else {
      postClose();
      return;
    }

    function postClose() {
      $scope.asideState.open = false;
      angular.element('.modal.fade.horizontal.right.in').remove();
    }

    $aside.open({
      templateUrl: 'views/popups/eventMenu.html',
      placement: 'right',
      size: 'sm',
      backdrop: false,
      controller: function($scope, $modalInstance) {
        $scope.ok = function(e) {
          $modalInstance.close();
          e.stopPropagation();
        };
        $scope.cancel = function(e) {
          $modalInstance.dismiss();
          e.stopPropagation();
        };
      }
    }).result.then(postClose, postClose);
  }

}]);
