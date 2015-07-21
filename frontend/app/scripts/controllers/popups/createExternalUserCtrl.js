'use strict';

angular.module('frontendApp')
    .controller('CreateExternalUserCtrl', ['$scope', 'createExternalTrainer', 'createExternalListener', 'type',
        function($scope, createExternalTrainer, createExternalListener, type) {
        $scope.type = type;

        $scope.createExternalListener = function() {
            var extListner = new createExternalListener();
            extListner.trainingId = 'aba';
            extListner.data = {
                name: $scope.name,
                surname: $scope.surname,
                username: $scope.username,
                email: $scope.email,
                phone: $scope.phone
            };
            extListner.$save();

            $scope.closeThisDialog();
        };

        $scope.createExternalTrainer = function() {
            var extTrainer =  new createExternalTrainer();
            extTrainer.data =  {
                name: $scope.name,
                surname: $scope.surname,
                username: $scope.username,
                email: $scope.email,
                phone: $scope.phone

            };
            extTrainer.$save();
            $scope.closeThisDialog();
        };
    }]);

