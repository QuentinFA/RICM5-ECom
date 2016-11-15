var myApp = angular.module('myApp', []);

myApp.controller('MainCtrl', ['$scope','$http', function ($scope,$http) {
	
	$scope.annonces = [];
	$scope.nbAnnonces = 4;
    $scope.nbPages = $scope.nbAnnonces /2;

    $scope.getNumber = function(num) {
        return new Array(num);   
    }

    $http.get('./annonces.json')
        .success(function(data) {
            $scope.annonces=data;
        })
        .error(function(data,status,error,config){
            $scope.annonces = [{heading:"Error",description:"Could not load json   data"}];
        });
}]);