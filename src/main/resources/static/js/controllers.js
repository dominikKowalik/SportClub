/**
 * Created by dominik on 2017-01-01.
 */
sportClubApp.controller("main", ['$scope', '$rootScope', '$log', 'AuthenticationService', 'CredentialsService',
    function ($scope, $rootScope, $log, AuthenticationService, CredentialsService) {
        $log.log("main controller");
        var login = CredentialsService.getLogin();
        var authHeader = CredentialsService.getAuthHeader();
        $log.log(login + ':' + authHeader);
        if (authHeader != null && login != null) {
            AuthenticationService.login(login);
        } else {
            window.location.replace('/#/login');
        }
    }]);

sportClubApp.controller("loginController", ['$scope', '$rootScope', '$log', 'AuthenticationService', 'CredentialsService',
    function ($scope, $rootScope, $log, AuthenticationService, CredentialsService) {
        $log.log("login controller");
        // saving authentication header and invoke login function
        $scope.loginUser = function () {
            var login = $scope.user.login;
            var password = $scope.user.password;
            $log.log(login);
            $log.log(password);
            CredentialsService.setAuthHeader(login, password);
            CredentialsService.setLogin(login);
            AuthenticationService.login(login);
        }
    }]);


sportClubApp.controller("adminController", ['$scope', '$rootScope', '$log', 'AuthenticationService',
    'CrudService','path','$http', function ($scope, $rootScope, $log, AuthenticationService, CrudService, path, $http) {
        $log.log("Admin Controller");
        // saving authentication header and invoke login function
        $scope.logout = function () {
            AuthenticationService.logout();
        }

        $http.get(path + "admin/club").then(function (response) {
            $scope.clubName = response.data.name;
        }, function (errResponse) {
            console.error('Error while fetching');
        })
        $log.log($scope.clubName);






    }]);






