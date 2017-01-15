sportClubApp.service("AuthenticationService", ['$http', '$rootScope', '$log', 'path', 'CredentialsService', function
    ($http, $rootScope, $log, path, CredentialsService) {
    this.login = function (login) {
        $log.log($rootScope.authHeader);
        $log.log('RESTService');
        $http.get(path + 'login/' + login).then(function (response) {
            var role = response.data.response;
            $log.log(role);
            if (role == 'ROLE_TRAINER') {
                window.location.replace('/#/trainer');
            } else if (role == 'ROLE_CLUBMEMBER') {
                window.location.replace('/#/clubMember');
            } else if (role == 'ROLE_ADMIN') {
                window.location.replace('/#/admin/clubDescription');
            } else {
                alert("Użytkownik nie ma przydzielonej roli.\nPrzepraszamy!");
                window.location.replace('/#/login');
            }
        }, function (errResponse) {
            $log.log(errResponse.data.role);
            alert("Użytkownik nie istnieje!");
            window.location.replace('/#/login');
        })
    }
    this.logout = function () {
        CredentialsService.clear();
    }
}]);

sportClubApp.service("CredentialsService", ['$rootScope', '$log', '$cookies', function ($rootScope, $log, $cookies) {
    this.clear = function () {
        $cookies.remove('authHeader');
        $cookies.remove('login');
    }

    this.setAuthHeader = function (login, password) {
        $rootScope.authHeader = 'Basic ' + btoa(login + ":" + password);
//store cookie that keeps user logged in for one week or util they logout
        var cookieExp = new Date();
        cookieExp.setDate(cookieExp.getDate() + 7);
        $cookies.putObject('authHeader', $rootScope.authHeader, {expires: cookieExp});
    }

    this.setLogin = function (login) {
        $rootScope.login = login;
//store cookie that keeps user logged in for one week or util they logout
        var cookieExp = new Date();
        cookieExp.setDate(cookieExp.getDate() + 7);
        $cookies.putObject('login', $rootScope.login, {expires: cookieExp});
    }

    this.getLogin = function () {
        return $cookies.getObject('login');
    }

    this.getAuthHeader = function () {
        return $cookies.getObject('authHeader');
    }

    return this;
}]);

sportClubApp.factory('AuthInterceptor', ['CredentialsService', function (CredentialsService) {
    return {
        // Send the Authorization header with each request
        'request': function (config) {
            config.headers = config.headers || {};
            config.headers.Authorization = CredentialsService.getAuthHeader();
            return config;
        }
    };
}]);


sportClubApp.service('ClubService', ['$http', 'path', '$log', '$rootScope', function ($http, path, $log, $rootScope) {

    this.setClub = function () {
        $rootScope.club = {
            name: '',
            logo: '',
            description: ''
        }

        $http.get(path + "admin/club").then(function (response) {
            $log.log(response);
            $log.log(response.data);
            $rootScope.club.name = response.data.name;
            $rootScope.club.logo = response.data.logo;
            $rootScope.club.description = response.data.description;
        }, function (errResponse) {
            console.error('Error while fetching');
        })
    }
    return this;
}])

sportClubApp.factory('trainerService', ['$http', 'path','$q', function ($http, path, $q) {
    return {
        'insertTrainer': function (trainer) {
            return $http.post(path + 'admin/trainer/' + trainer.firstname + '/' +
                trainer.lastname + '/' + trainer.education + '/' + trainer.fatherName + '/' + trainer.motherName +
                '/' + trainer.pesel + '/' + trainer.account.login + '/' + trainer.account.password).then(
                function(response){
                    return response;
                },
                function(errResponse){
                    console.error('Error while deleting user');
                    return $q.reject(errResponse);
                }
            );

        },

        'updateClub': function(url, description){
            return $http.patch(path + "admin/club/" + description, url);
        },

        'fetchTrainer': function (pesel) {
            return $http.get(path + "/admin/trainer/" + pesel);
        },

        'fetchTrainerByLogin': function (login) {
            return $http.get(path + "trainer/" + login);
        },

        'fetchAllTrainers': function () {
            return $http.get(path + "/admin/allTrainers");
        },
        'deleteTrainer': function (firstname, pesel) {
            return $http.delete(path + 'admin/trainer/' + firstname + '/' + pesel);
        },

        'update': function (trainer) {
                return $http.patch(path + 'admin/trainer/' + trainer.employeeId + "/" + trainer.firstname + '/' +
                    trainer.lastname + '/' + trainer.education + '/' + trainer.fatherName + '/' + trainer.motherName +
                    '/' + trainer.pesel + '/' + trainer.account.login + '/' + trainer.account.password ).then(
                    function(response){
                        return response;
                    },
                    function(errResponse){
                        console.error('Error while patching user');
                        return $q.reject(errResponse);
                    }
                );
        }
    }
}])





