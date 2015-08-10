'use strict';

angular.module('frontendApp')
  .factory('AuthenticationService',
  ['$base64', '$http', '$cookies', '$rootScope', '$localStorage',
    function ($base64, $http, $cookies, $rootScope, $localStorage) {
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
              $rootScope.authenticated = false;
              callback && callback();
          });
      };

      service.SetCredentials = function (username, sessionId, isAuthorized, userRole) {
        $rootScope.globals = {
          username: username,
          authorized: isAuthorized,
          sessionId: sessionId
        };
        $localStorage.userName = username;
        var now = new Date(),
        exp = new Date(now.getFullYear(), now.getMonth(), now.getDate()+1);
        $cookies.put('globals', $rootScope.globals, {
          expires: exp
        });
      };

      service.ClearCredentials = function () {
        $rootScope.globals = {};
        $cookies.remove('globals');
        $localStorage.$reset();
      };

      return service;
  }]);
