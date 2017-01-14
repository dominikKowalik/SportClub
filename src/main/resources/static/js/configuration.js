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
             templateUrl: "views/admin/admin.html"
         }).state('trainer', {//State demonstrating Nested views
             url: "/trainer",
             templateUrl: "views/trainer.html"
         }).state('admin.trainers', {//nested state [products is the nested state of business state]
             url: "/admin/trainers",
             templateUrl: "views/admin/adminTrainers.html",
         }).state('admin.findTrainer', {//nested state [products is the nested state of business state]
             url: "/admin/findTrainer",
             templateUrl: "views/admin/adminFindTrainer.html",
         }).state('admin.addTrainer', {//nested state [products is the nested state of business state]
             url: "/admin/addTrainer",
             templateUrl: "views/admin/addTrainer.html",
         }).state('admin.trainers.editTrainer',{
             url: "/editTrainer",
             templateUrl: "views/admin/editTrainer.html"
         })
     }]).config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
}]);

sportClubApp.constant('path', 'http://localhost:8080/');
