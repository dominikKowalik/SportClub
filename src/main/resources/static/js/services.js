sportClubApp.service("RESTService", ['$http', '$rootScope', '$log', 'path', function
    ($http, $rootScope, $log, path) {
    this.login = function (login) {
        $log.log('RESTService');
        $http({
            method: 'GET', url: path + 'login/' + login,
            headers: {'Authorization': $rootScope.authenticationHeader}
        }).then(function (response) {
            var role = response.data.response;
            $log.log(role);
            if(role == 'ROLE_DIRECTOR'){
                window.location.replace('/#/director');
            } else if(role == 'ROLE_CLUBMEMBER'){
                window.location.replace('/#/clubMember');
            } else if(role == 'ROLE_ADMIN'){
                window.location.replace('/#/admin');
            } else {
                alert("Użytkownik nie ma przydzielonej roli.\nPrzepraszamy!");
            }
        }, function (errResponse) {
            $log.log(errResponse.data.role);
            alert("Użytkownik nie istnieje!");
        })
    }
}])