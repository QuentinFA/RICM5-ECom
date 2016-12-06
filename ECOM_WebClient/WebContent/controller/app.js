//script.js

//create the module and name it scotchApp
//also include ngRoute for all our routing needs
var scotchApp = angular.module('myApp', ['ngRoute']);

//configure our routes
scotchApp.config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : './home.html',
		controller  : 'mainController'
	})

	// route for the about page
	.when('/offres', {
		templateUrl : './offres.html',
		controller  : 'MainCtrl'
	})

	.when('/login', {
		templateUrl : './login.html'
	})

	// route for the contact page
	.when('/newAdvert', {
		templateUrl : './newAdvert.html',
		controller  : 'NewAdController'
	})

	.when('/detailAdvert/:IdAdvert', {
		templateUrl : './detail.html',
		controller  : 'detailController'
	})
	.otherwise({redirectTo:'/'});
});

//create the controller and inject Angular's $scope
scotchApp.controller('MainCtrl', ['$scope','$http','$location',function ($scope,$http,$location) {
	$scope.showAnnonce = function(annonce) {
		$location.path('/detailAdvert/' + annonce.idProduct);
		//$window.location.href = '/detailAdvert/' + client.idProduct;
		// console.log(annonce.idProduct);
	};

	$scope.annonces = [];
	$scope.nbAnnonces = 4;
	$scope.nbPages = $scope.nbAnnonces /2;

	$scope.getNumber = function(num) {
		return new Array(num);   
	}; 

	$http.get('/rest/product/getAllproducts')
	.success(function(data) {
		$scope.annonces=data;
	})
	.error(function(data,status,error,config){
		$scope.annonces = [{heading:"Error",description:"Could not load json   data"}];
	});
}]);

scotchApp.controller('NewAdController', function($scope) {
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
});

scotchApp.controller('detailController', function($scope, $routeParams, $http) {

	$scope.IdAdvert = $routeParams.IdAdvert;	
	$scope.annonce;
	$scope.vendeur;
	$scope.transform = function(origin){
		console.log(origin);
		origin = origin.imgurl;
		var etat1 = origin.split("/web-ecom/");
		var etat2 = etat1[0]+".rsz.io/web-ecom/"+etat1[1]+"?height=100";
		return etat2;
	};
	var req = {
			method: 'POST',
			url: '/rest/product/getProductByID/'+$scope.IdAdvert
	};

	$http(req).then(function (data, status, headers, config) {
				// success function
				console.log(data.data[0]);
				$scope.annonce = data.data[0];
				var req2 = {
						method: 'POST', 
						url: '/rest/user/getUserByID/'+data.data[0].idUser
				};
				
				$http(req2).then(function (data2, status, headers, config) {
					// success function
					console.log(data2.data[0]);
					$scope.vendeur = data2.data[0];
				}, function (data2, status, headers, config) {
					alert( "failure message: " + JSON.stringify({data: data2}));
				});
	}, function (data, status, headers, config) {   
		alert( "failure message: " + JSON.stringify({data: data}));
	});

});