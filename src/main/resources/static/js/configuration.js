/**
 * Created by dominik on 2017-01-07.
 */
var sportClubApp = angular.module("sportClub", ['ui.router','ngCookies']).config(['$stateProvider',
    '$urlRouterProvider',
     function($stateProvider,$urlRouterProvider){
         $stateProvider
             .state('login', {//State demonstrating Nested views
                 url: "/login",
                 templateUrl: "views/login.html"
             }).state('clubMember', {//State demonstrating Nested views
             url: "/clubMember",
             templateUrl: "views/clubMember.html"
         }).state('admin', {//State demonstrating Nested views
             url: "/admin",
             templateUrl: "views/admin.html"
         }).state('trainer', {//State demonstrating Nested views
             url: "/trainer",
             templateUrl: "views/trainer.html"
         })
     }]).config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
}]);





sportClubApp.constant('path', 'http://localhost:8080/');
