angular.module('frontendApp').service('translationService', function ($resource) {
  this.getTranslation = function ($rootScope, language) {
    var languageFilePath = 'views/localization/trainings_' + language + '.json';

    $resource(languageFilePath).get(function (data) {
      $rootScope.translation = data;
    });
  };
});
