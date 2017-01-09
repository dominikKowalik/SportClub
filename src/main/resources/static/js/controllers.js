/**
 * Created by dominik on 2017-01-01.
 */
sportClubApp.controller("main", ['$scope', '$rootScope', '$log', 'AuthenticationService', 'CredentialsService', 'ClubService',
    function ($scope, $rootScope, $log, AuthenticationService, CredentialsService, ClubService) {
        $log.log("main controller");

        var login = CredentialsService.getLogin();
        var authHeader = CredentialsService.getAuthHeader();
        $log.log(login + ':' + authHeader);
        if (authHeader != null && login != null) {
            ClubService.setClubName();
            AuthenticationService.login(login);
        } else {
            window.location.replace('/#/login');
        }
    }]);

sportClubApp.controller("loginController", ['$scope', '$rootScope', '$log', 'AuthenticationService', 'CredentialsService',
    'ClubService',
    function ($scope, $rootScope, $log, AuthenticationService, CredentialsService, ClubService) {
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
            ClubService.setClubName();
        }
    }]);

sportClubApp.controller("adminController", ['$scope', '$rootScope', '$log', 'AuthenticationService'
    , 'path','trainerService', function ($scope, $rootScope, $log, AuthenticationService, path,trainerService) {
        $log.log("Admin Controller");
        // saving authentication header and invoke login function
        $scope.logout = function () {
            AuthenticationService.logout();
        }


        $scope.employee = {
            firstname: '',
            lastname: '',
            education: '',
            motherName: '',
            fatherName: '',
            pesel: '',

            account: {
                name: '',
                password: ''
            }
        }


        $scope.clearForm = function () {
            $scope.employee.firstname = '';
            $scope.employee.lastname = '';
            $scope.employee.education = '';
            $scope.employee.motherName = '';
            $scope.employee.fatherName = '';
            $scope.employee.pesel = '';

            $scope.employee.account.login = '';
            $scope.employee.account.password = '';
        }

        $scope.insertTrainer = function () {
            var employee = $scope.employee;
            $log.log(employee);

            var promise =
                trainerService.insertTrainer(employee);

            promise.then(
            function(payload) {
                $log.log(payload.status);
            },
            function(errorPayload) {
                $log.log(errorPayload.status);
            });
        };
        $log.log($scope.club.name);
    }]);






