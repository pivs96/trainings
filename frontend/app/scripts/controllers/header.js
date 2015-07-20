/**
 * Created by Natsik on 11.07.2015.
 */
angular.module('frontendApp')
  .controller('HeaderCtrl', ['$location','$scope','$modal','$aside', function ($location, $scope,$modal, $aside) {

    this.companyName = companyName;

    this.goToUserProfile = function (){
      $location.path('/userprofile');
    }

    this.logout = function (){
      alert("we are working...");
      //TODO logout functionality
    }

    $scope.isActive = function (page) {
      var currentRoute = $location.path().substring(1) || 'home';
      return page === currentRoute ? 'active' : '';
    };

    $scope.openAside = function(position, backdrop) {
      $scope.asideState = {
        open: true,
        position: position
      };

      function postClose() {
        $scope.asideState.open = false;
      }

      $aside.open({
        templateUrl: 'views/popups/aside.html',
        placement: position,
        size: 'sm',
        backdrop: false,
        controller: function($scope,$modalInstance) {
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
