'use strict';

/**
 * @ngdoc overview
 * @name frontendApp
 * @description
 * # frontendApp
 *
 * Main module of the application.
 */
angular
  .module('frontendApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/userprofile', {
        templateUrl: 'views/userprofile.html',
        controller: 'UserProfileCtrl',
        controllerAs: 'userprofile'
      })
      .otherwise({
        redirectTo: '/'
      });
  }).run(function($rootScope) {
    //TODO provide role from userService here
      $rootScope.getRole = function (){return 'admin'};
  });;
