angular.module('frontendApp')
  .controller('SideMenu',['$scope','$aside', function($scope, $aside) {

    var asideInstance = $aside.open({
      templateUrl: 'views/popups/aside.html',
      controller: 'AsideCtrl',
      placement: 'left',
      size: 'lg'
    });

    $scope.asideState = {
      open: false
    };

  }]);
