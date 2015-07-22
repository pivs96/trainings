/**
 * Created by Natsik on 11.07.2015.
 */
angular.module('frontendApp')
  .controller('HeaderCtrl', ['EventService','$location','$scope','$aside', function (EventService,$location, $scope, $aside) {

    this.companyName = companyName;

    this.goToUserProfile = function (){
      $location.path('/userprofile');
    };

    this.logout = function (){
      alert("we are working...");
      //TODO logout functionality
    };

    $scope.eventList = angular.copy(EventService.list);

    $scope.asideState = {
      open: false
    };

    $scope.openAside = function(position) {
      if(!$scope.asideState.open) {
        $scope.asideState = {
          open: true,
          position: position
        };
      }
      else {
        postClose();
        return;
      }

      function postClose() {
        $scope.asideState.open = false;
        angular.element('.modal.fade.horizontal.right.in').remove();
      }

      $aside.open({
        templateUrl: 'views/popups/eventMenu.html',
        placement: position,
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

var companyName = "Exadel";
