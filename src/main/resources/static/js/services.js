sportClubApp.service("AuthenticationService", ['$http', '$rootScope', '$log', 'path','CredentialsService', function
    ($http, $rootScope, $log, path, CredentialsService) {
    this.login = function (login) {
        $log.log($rootScope.authHeader);
        $log.log('RESTService');
        $http.get(path + 'login/' + login).then(function (response) {
            var role = response.data.response;
            $log.log(role);
            if (role == 'ROLE_DIRECTOR') {
                window.location.replace('/#/director');
            } else if (role == 'ROLE_CLUBMEMBER') {
                window.location.replace('/#/clubMember');
            } else if (role == 'ROLE_ADMIN') {
                window.location.replace('/#/admin');
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
        $cookies.putObject('login', $rootScope.login , {expires: cookieExp});
    }

    this.getLogin = function () {
        return $cookies.getObject('login');
    }

    this.getAuthHeader = function () {
        return $cookies.getObject('authHeader');
    }

    return this;
}]);

sportClubApp.factory('AuthInterceptor', ['CredentialsService',function(CredentialsService) {
    return {
        // Send the Authorization header with each request
        'request': function(config) {
            config.headers = config.headers || {};
            config.headers.Authorization = CredentialsService.getAuthHeader();
            return config;
        }
    };
}]);


sportClubApp.factory('CrudService', ['$http','$log','$q', function ($http,path,$log,$q) {
    return {
        // Send the Authorization header with each request
        'fetchOne': function(path) {
       return $http.get(path).then(function (response) {
            return response.data;
        }, function (errResponse) {
            console.error('Error while fetching');
            return $q.reject(errResponse);
        })

        }
    }
}])




