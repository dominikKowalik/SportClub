/**
 * Created by dominik on 2017-01-01.
 */
var sportClubApp = angular.module("sportClub", []);

sportClubApp.constant('path', 'http://localhost:8080/');

sportClubApp.controller("loginController", ['$scope', '$log', 'RESTService','path', function ($scope, $log, RESTService) {
    $log.log("login controller");

  $scope.loginUser =  function () {
    $scope.login = RESTService.login();
}
}])

sportClubApp.service("RESTService", ['$http', '$log','path', function ($http, $log, path) {
    this.login = function () {
        $log.log('RESTService');
        $http.get(path + 'login').then(
            function (response) {
                sportClubApp.value("roleOfCurrentUser",response.data.role)
                $log.log(response.data.role)
            },
            function (errResponse) {
                $log.log(errResponse.data.role)
            }
        )
    }
}])