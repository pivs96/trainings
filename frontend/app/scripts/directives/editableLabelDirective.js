angular.module('frontendApp').directive("trnEditableLabel", function () {
  return {
    restrict: "E",
    templateUrl: "../../views/templates/editableLabel.html",
    scope: {
      editableValue: "=",
      editMode: "="
    }
  }
});
