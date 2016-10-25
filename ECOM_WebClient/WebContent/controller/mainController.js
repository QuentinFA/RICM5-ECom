var myApp = angular.module('myApp', []);

myApp.controller('MainCtrl', ['$scope','$http', function ($scope,$http) {
	
	$scope.annonces = {};
	$scope.title = [];
    $http.get('./annonces.json')
        .success(function(data) {
            $scope.annonces=data;
        })
        .error(function(data,status,error,config){
            $scope.annonces = [{heading:"Error",description:"Could not load json   data"}];
        });
}]);