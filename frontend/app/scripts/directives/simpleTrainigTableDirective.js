angular.module('frontendApp').directive("trnSimpleTrainingTable", function () {
  return {
    restrict: "E",
    templateUrl: "../../views/tables/simpleTrainingTable.html",
    scope: {
      trainingsList: "=",
      tableLabel: "=",
      translation: "="
    }
  }
});
