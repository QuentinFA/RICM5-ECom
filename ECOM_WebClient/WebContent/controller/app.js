//script.js

//create the module and name it scotchApp
//also include ngRoute for all our routing needs
var scotchApp = angular.module('myApp', ['ngRoute','ngCookies']);

//configure our routes
scotchApp.config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : './home.html',
		controller  : 'homeController'

	})

	// route for the about page
	.when('/offres', {
		templateUrl : './offres.html',
		controller  : 'MainCtrl'
	})

	.when('/login', {
		templateUrl : './login.html',
		controller  : 'LoginController'
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

	.when('/register', {
		templateUrl : './register.html'
	})
	.otherwise({redirectTo:'/'});
});
//create the controller and inject Angular's $scope
scotchApp.controller('homeController', ['$scope','$http','$location',function ($scope,$http,$location) {
	$location.path('/offres');
}]);
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

scotchApp.controller('NewAdController', function($scope, $http,$location,$cookies,$cookieStore) {
	$scope.loading = false;
	var user = $cookies.user;
	if(user == undefined || user == null){
		$location.path('/login');
	}
	$scope.stepsModel = [];
	$scope.sizeLimit      = 10585760; // 10MB in Bytes
	$scope.creds          = {};
	$scope.images = [];
	$scope.categories = [
	                     {id:1, name:"Categorie1"},
	                     {id:2, name:"Categorie2"},
	                     {id:3, name:"Categorie3"}
	                     ];
	$http.get('/rest/category/getAllCategory')
	.success(function(data) {
		$scope.categories=data;
	})
	.error(function(data,status,error,config){
		//$scope.annonces = [{heading:"Error",description:"Could not load json   data"}];
	});

	$scope.startUpload =function(product) {
		$scope.PictureNames = [];
		$scope.loading = true;
		for (var i = 0; i < $scope.images.length; i++) {
			$scope.upload($scope.images[i]);
		}

		var req = {
				method: 'POST', 
				url: '/rest/product/createProduct',
				headers: {
					'Content-Type': 'application/json'
				},
				data: {
					"idProduct" : 0,
					"description" : product.description,
					"idUser" : user,
					"price" : product.prix,
					"title" : product.titre,
					"type" : product.category
				}
		};

		$http(req).then(function (data, status, headers, config) {
			
			if(data.status == 200){
				for (var i = 0; i < $scope.PictureNames.length; i++) {
					var url = "https://s3-eu-west-1.amazonaws.com/web-ecom/"+$scope.PictureNames[i];
					var reqIm = {
							method: 'POST', 
							url: '/rest/image/createImage',
							headers: {
								'Content-Type': 'application/json'
							},
							data: {
								"idImage": 0,
								"idProduct" : data.data.idProduct,
								"idUser" : user,
								"imgUrl" : url
							}
					};
					$http(reqIm).then(function (data2, status, headers, config) {
						$scope.loading = false;
						toastr.success("Produit ajouté avec succes");
						$location.path('/offres');


					}, function (data2, status, headers, config) {
						//alert( "failure message: " + JSON.stringify({data: data2}));

					});
				}

			}
			else{

			}
		}, function (data, status, headers, config) {
			//alert( "failure message: " + JSON.stringify({data: data2}));

		});
	};

	$scope.imageUpload = function(event){
		var files = event.target.files; //FileList object
		$scope.images = files;
		for (var i = 0; i < files.length; i++) {
			var file = files[i];
			var reader = new FileReader();
			reader.onload = $scope.imageIsLoaded; 
			reader.readAsDataURL(file);
		}
	};

	$scope.imageIsLoaded = function(e){
		$scope.$apply(function() {
			$scope.stepsModel.push(e.target.result);
		});
	};



	$scope.upload = function(file) {

		AWS.config.update({ accessKeyId: 'AKIAIKCAO3X6Y35VXQYA', secretAccessKey: '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' });
		AWS.config.region = 'us-east-1';
		var bucket = new AWS.S3({ params: { Bucket: 'web-ecom' } });

		if(file) {
			// Perform File Size Check First
			var fileSize = Math.round(parseInt(file.size));
			if (fileSize > $scope.sizeLimit) {
				toastr.error('Désolé un fichier est trop grand ');
				return false;
			}
			// Prepend Unique String To Prevent Overwrites
			var uniqueFileName = $scope.uniqueString() + '-' + file.name;
			$scope.PictureNames.push(uniqueFileName);
			var params = { Key: uniqueFileName, ContentType: file.type, Body: file, ServerSideEncryption: 'AES256' };

			bucket.putObject(params, function(err, data) {
				if(err) {
					toastr.error(err.message,err.code);
					return false;
				}


			});
		}
		else {
			// No File Selected
			toastr.error('Please select a file to upload');
		}
	}

	$scope.fileSizeLabel = function() {
		// Convert Bytes To MB
		return Math.round($scope.sizeLimit / 1024 / 1024) + 'MB';
	};

	$scope.uniqueString = function() {
		var text     = "";
		var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		for( var i=0; i < 8; i++ ) {
			text += possible.charAt(Math.floor(Math.random() * possible.length));
		}
		return text;
	};
});

scotchApp.controller('LoginController', function($scope) {

	$scope.connexion = function(){

	};
});

scotchApp.controller('registerController', function($scope,$http,$location) {
	$scope.status ="idle";
	$scope.update = function(user){


		var req2 = {
				method: 'POST', 
				url: '/rest/user/createUserByJSON',
				headers: {
					'Content-Type': 'application/json'
				},
				data: {
					"idUser" : user.username,
					"actived" : false,
					"email" : user.email,
					"firstname" : user.firstname,
					"lastname" : user.lastname,
					"password" : user.password,
					"telephone" : user.telephone
				}
		};

		$http(req2).then(function (data2, status, headers, config) {
			if(data2.status == 201){
				$scope.status ="success";
				toastr.success('Utilisateur créé avec success');
				$location.path('/login');
			}
			else{
				$scope.status ="failed";
				toastr.error('Une erreur s\'est produite au niveau du serveur ');
			}
		}, function (data2, status, headers, config) {
			//alert( "failure message: " + JSON.stringify({data: data2}));
			$scope.status ="failedServeur";
			toastr.error('Une erreur s\'est produite au niveau du serveur ');
		});

	};
}); 

scotchApp.controller('detailController', function($scope, $routeParams, $http) {

	$scope.IdAdvert = $routeParams.IdAdvert;	
	$scope.annonce;
	$scope.vendeur;
	$scope.url;
	$scope.changeImage = function(url) {
		$scope.url = url;
	};
	$scope.transform = function(origin){
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
		$scope.annonce = data.data[0];
		$scope.url = $scope.annonce.images[0].imgUrl;
		var req2 = {
				method: 'POST', 
				url: '/rest/user/getUserByID/'+data.data[0].idUser
		};

		$http(req2).then(function (data2, status, headers, config) {
			// success function
			$scope.vendeur = data2.data[0];
		}, function (data2, status, headers, config) {
			//alert( "failure message: " + JSON.stringify({data: data2}));
			toastr.error("failure message: " + JSON.stringify({data: dataZ}));
		});
	}, function (data, status, headers, config) {   
		//alert( "failure message: " + JSON.stringify({data: data}));
		toastr.error("failure message: " + JSON.stringify({data: data}));
	});

});