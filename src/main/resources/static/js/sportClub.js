/**
 * Created by dominik on 2017-01-01.
 */


var sportClubApp = angular.module("sportClub", ['ngRoute']).config(['$routeProvider', function($routeProvider){
    $routeProvider.when(
        '/',{templateUrl: 'views/login.html',
            controller:'loginController'}
    ).when('/clubMember',{templateUrl: 'views/clubMember.html'})}]);





sportClubApp.constant('path', 'http://localhost:8080/');


sportClubApp.controller("loginController", ['$scope', '$rootScope', '$log', 'RESTService', 'path', function ($scope, $rootScope, $log, RESTService) {
    $log.log("login controller");

    // saving authentication header and invoke login function
    $scope.loginUser = function () {
        $log.log($scope.user.login);
        $log.log($scope.user.password);
        $rootScope.authenticationHeader = 'Basic ' + btoa($scope.user.login + ":" + $scope.user.password);
        RESTService.login();
    }
}])


sportClubApp.service("RESTService", ['$http', '$rootScope', '$log', 'path', function
    ($http, $rootScope, $log, path) {

    this.login = function () {
        $log.log('RESTService');

        $http({
            method: 'GET', url: path + 'login',
            headers: {'Authorization': $rootScope.authenticationHeader}
        }).then(function (response) {
            sportClubApp.value("roleOfCurrentUser", response.data.role);
            window.location.replace('/#/clubMember');
            $log.log(response.data.role)
        }, function (errResponse) {
            $log.log(errResponse.data.role)
        })
    }
}])