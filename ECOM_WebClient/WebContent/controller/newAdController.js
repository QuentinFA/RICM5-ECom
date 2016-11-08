var myApp = angular.module('myApp', []);

myApp.controller('NewAdController', ['$scope','$http', function ($scope,$http) {
	
/*	$scope.annonces = {};*/
	$scope.categories = [
        {id:1, name:"Categorie1"},
        {id:2, name:"Categorie2"},
        {id:3, name:"Categorie3"}
    ];
/*    $http.get('./annonces.json')
        .success(function(data) {
            $scope.annonces=data;
        })
        .error(function(data,status,error,config){
            $scope.annonces = [{heading:"Error",description:"Could not load json   data"}];
        });*/
}]);