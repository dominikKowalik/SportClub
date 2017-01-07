/**
 * Created by dominik on 2017-01-07.
 */
var sportClubApp = angular.module("sportClub", ['ngRoute']).config(['$routeProvider', function($routeProvider){
    $routeProvider.when(
        '/',{templateUrl: 'views/login.html',
            controller:'loginController'}
    ).when('/clubMember',{templateUrl: 'views/clubMember.html'})
    .when('/admin',{templateUrl: 'views/admin.html'})}]);

sportClubApp.constant('path', 'http://localhost:8080/');
