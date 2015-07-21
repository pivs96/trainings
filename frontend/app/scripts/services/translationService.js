angular.module('frontendApp').service('translationService', function ($resource, $rootScope) {
  this.getTranslation = function (language) {
    var languageFilePath = 'views/localization/trainings_' + language + '.json';

    $resource(languageFilePath).get(function (data) {
      $rootScope.translation = data;
    });
  };
});
