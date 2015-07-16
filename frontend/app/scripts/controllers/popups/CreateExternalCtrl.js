'use strict';

angular.module('frontendApp')
    .controller('CreateExternalCtrl', ['$scope', '$rootScope', function($scope, $rootScope) {
        $scope.type = $rootScope.type;

        $scope.createExternalListener = function() {
            //TODO add actions
            $scope.closeThisDialog();
        };

        $scope.createExternalTrainer = function() {
            //TODO add actions
            $scope.closeThisDialog();
        };
    }]);