/*jshint unused: false */
/*global angular:true */
// Declare app level module
var App = angular.module('App', []);
(function() {
  'use strict';

  App.controller("HelloCtrl", ['$scope', '$http',
    function($scope, $http) {
      $scope.name = "User";
      $scope.message = "";

      $scope.getName = function() {
        $http.get('hello/' + $scope.name).then(function(response) {
          $scope.message = "Hello " + response.data;
        });
      };

    }
  ]);


})();