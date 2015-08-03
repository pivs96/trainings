'use strict';

angular.module('frontendApp')
  .factory('shareTrainingInfo', function() {
    var data = {};

    return {
      getData: function() {
        return data;
      },

      setData: function(info) {
        data = angular.copy(info);
      }
    }
  });
