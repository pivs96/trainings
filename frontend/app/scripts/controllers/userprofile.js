/**
 * Created by Natsik on 13.07.2015.
 */
angular.module('frontendApp')
  .controller('UserProfileCtrl',['$scope','ngDialog', function ($scope,ngDialog) {
    $scope.leaveTrainingFeedback = function(){
      ngDialog.open({
        template: "views/popups/leaveTrainingFeedback.html",
        controller: 'LeaveFeedbackCtrl'
      })
    }
    $scope.leaveUserFeedback = function(){
      ngDialog.open({
        template: "views/popups/leaveUserFeedback.html",
        controller: 'LeaveFeedbackCtrl'
      })
    }
}]);
