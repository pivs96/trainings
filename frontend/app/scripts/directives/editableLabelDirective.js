angular.module('frontendApp').directive("editableLabel", function () {
  return {
    restrict: "E",
    templateUrl: "../../views/common/editableLabel.html",
    scope: {
      editableValue: "=",
      editMode: "="
    }
  }
});
