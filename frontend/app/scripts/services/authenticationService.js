'use strict';

angular.module('frontendApp')
  .factory('AuthenticationService',
  ['$base64', '$http', '$cookies', '$rootScope',
    function ($base64, $http, $cookies, $rootScope) {
      var service = {};

      service.Login = function (credentials, callback) {
        var headers = credentials ? {
          authorization: "Basic "
          + $base64.encode(credentials.username + ':' + credentials.password)
        } : {};

        $http.get('http://localhost:8080/loguser', {headers: headers})
          .success(function (response) {
            if (callback) {
              callback(response);
            }
          }).error(function () {
            callback && callback();
          });
      };

      service.SetCredentials = function (username, sessionId, isAuthorized) {
        $rootScope.globals = {
          username: username,
          authorized: isAuthorized,
          sessionId: sessionId
        };
        var now = new Date(),
          exp = new Date(now.getFullYear(), now.getMonth(), now.getDate()+1);
        $cookies.put('globals', $rootScope.globals, {
          expires: exp
        });
      };

      service.ClearCredentials = function () {
        $rootScope.globals = {};
        $cookies.remove('globals');
      };

      return service;
    }]);
