angular.module('frontendApp').directive("trnEditableLabel", function () {
  return {
    restrict: "E",
    templateUrl: "../../views/common/editableLabel.html",
    scope: {
      editableValue: "=",
      editMode: "="
    }
  }
});
