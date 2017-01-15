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
            ClubService.setClub();
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
        }
    }]);

sportClubApp.controller("adminController", ['$scope', '$rootScope', '$log', 'AuthenticationService'
    , 'path','trainerService', 'ClubService', function ($scope, $rootScope, $log, AuthenticationService, path,trainerService, ClubService) {
        $log.log("Admin Controller");
        // saving authentication header and invoke login function
        $scope.logout = function () {
            AuthenticationService.logout();
        }

        ClubService.setClub();
        $rootScope.club1 = {
            logoToSend: '',
            descriptionToSend:''
        }

        var newEmployee = function () {
            return  {
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
            };
        }


        $scope.employee = newEmployee();
        $scope.employeeToEdit = newEmployee()

        $scope.trainers = '' ;
        $scope.employeesToEdit = '';

        $scope.patchClub = function(){

            $log.log("patchClub");
            $log.log($rootScope.club1.descriptionToSend);
            $log.log($rootScope.club1.logoToSend);

            var promise =
                trainerService.updateClub($rootScope.club1.logoToSend, $rootScope.club1.descriptionToSend);
            promise.then(
                function (payload) {
                    $log.log(payload.status);
                    ClubService.setClub();
                },
                function (errPayload) {
                    $log.log(errPayload.status);
                }
            )
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

        $scope.fetchAllTrainers = function () {
            var promise =
                trainerService.fetchAllTrainers();
            promise.then(
                function (payload) {
                    $log.log(payload.data);
                    $scope.trainers = payload.data;
                },
                function (errPayload) {
                    $log.log(errPayload.data);
                }
            )
        }

        $scope.updateTrainer = function () {
            $log.log("updateTrainer");
            var promise = trainerService.update($scope.employeesToEdit[0]);
            promise.then(
                function (payload) {
                    $log.log(payload.status);
                    alert("Operacja powiodła się")
                },function(errPayload) {
                    $log.log(errPayload.status);
                    alert("Operacja nie powiodła się")
                }
            )
        };

        $scope.insertTrainer = function () {
            var employee = $scope.employee;
            $log.log(employee);

            var promise =
                trainerService.insertTrainer(employee);

            promise.then(
            function(payload) {
                alert("Operacja powiodła się")
                $log.log(payload.status);
            },
            function(errorPayload) {
                alert("Użyktownik już istnieje!");
                $log.log(errorPayload.status);
            });
        };

        $scope.deleteTrainer = function (firstname, pesel) {
            var promise =
                trainerService.deleteTrainer(firstname, pesel);

            promise.then(
                function(payload) {
                    $log.log(payload.status);
                    alert("Operacja powiodła się")
                    $scope.fetchAllTrainers();
                },
                function(errorPayload) {
                    $log.log(errorPayload.status);
                });
        };


        $scope.findedTrainer = [ newEmployee() ];

        $scope.findTrainer = function (pesel) {
            var promise =
                trainerService.fetchTrainer(pesel);
            promise.then(
                function(payload) {
                    $log.log(payload.data);
                    $scope.findedTrainer =  [ payload.data ];
                 },
                function(errorPayload) {
                    $log.log(errorPayload.status);
                    $scope.findedTrainer =  [ newEmployee() ];
                });
        }

        $scope.setTrainersToEdit = function(pesel){
            for (var i = 0; i < $scope.trainers.length ; i++) {
                if($scope.trainers[i].pesel = pesel){
                    $log.log($scope.trainers[i]);
                    $scope.trainers[i].pesel = parseInt($scope.trainers[i].pesel);
                    $scope.employeesToEdit =  [ $scope.trainers[i] ] ;
                    $log.log($scope.employeesToEdit);
                    break;
                }
            }
        }

       $scope.setTrainerToAddressEdit = function (id) {
           $log.log(id);
           $scope.employeeToAddressEditId = id;
       }

        $log.log($scope.club.name);
    }]);


sportClubApp.controller("trainerController", ['$scope', '$rootScope', '$log', 'AuthenticationService'
    ,'path','trainerService','CredentialsService', function ($scope, $rootScope, $log, AuthenticationService, path,trainerService,CredentialsService) {
        var promise =
                trainerService.fetchTrainerByLogin(CredentialsService.getLogin());
            promise.then(
                function(payload) {
                    $log.log('findTrainerByName');
                    $log.log(payload.data);
                    $scope.trainer = payload.data;
                    $scope.navbarText = 'Witaj ' + $scope.trainer.firstname + '!';
                },
                function(errorPayload) {
                    $log.log(errorPayload.status);
                    $scope.findedTrainer =  [ newEmployee() ];
                });



        $scope.logout = function () {
            AuthenticationService.logout();
        }
    }]);