angular.module('frontendApp').controller('EventsListCtrl', ['$scope', 'ngDialog', 'data',
  function ($scope, ngDialog, data) {
    $scope.eventsList = data;

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

  }]);
