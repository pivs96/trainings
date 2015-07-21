angular.module('frontendApp')
  .filter('yesNo', function() {
    return function(input) {
      return input ? 'yes' : 'no';
    }
  });
