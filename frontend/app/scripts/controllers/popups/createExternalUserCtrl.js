'use strict';

angular.module('frontendApp')
  .controller('CreateExternalUserCtrl', ['$scope', 'createExternalTrainer', 'createExternalListener', 'data',
    function($scope, createExternalTrainer, createExternalListener, data) {
      $scope.type = data.type;

      $scope.createExternalListener = function() {
        var extListner = new createExternalListener();
        extListner.trainingId = data.trainingId;
        extListner = _.extend(extListner,{
          name: $scope.name,
          surname: $scope.surname,
          email: $scope.email,
          phone: $scope.phone
        });
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

