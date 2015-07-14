/**
 * Created by Natsik on 11.07.2015.
 */
angular.module('frontendApp')
  .controller('HeaderCtrl', function ($location,  $scope) {
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

  });;

var companyName = "Exadel";
