/**
 * Created by dominik on 2017-01-01.
 */

sportClubApp.controller("loginController", ['$scope', '$rootScope', '$log', 'RESTService', 'path', function ($scope, $rootScope, $log, RESTService) {
    $log.log("login controller");
    // saving authentication header and invoke login function
    $scope.loginUser = function () {
        $log.log($scope.user.login);
        $log.log($scope.user.password);
        $rootScope.authenticationHeader = 'Basic ' + btoa($scope.user.login + ":" + $scope.user.password);
        RESTService.login($scope.user.login);
    }
}])





