angular.module('RedditAlbum')

// Creating the Angular Controller
.controller('NavController', function($http, $scope, AuthService, $state, $rootScope,ImageService) {
	$scope.$on('LoginSuccessful', function() {
		$scope.user = AuthService.user;
		console.log($scope.user.principal.name);
	});
	$scope.$on('LogoutSuccessful', function() {
		$scope.user = null;
	});
	$scope.logout = function() {
		AuthService.user = null;
		$rootScope.$broadcast('LogoutSuccessful');
		$state.go('login');
	};

    $rootScope.populate = function () {
        //noinspection JSUnresolvedVariable
        ImageService.search($scope.query,$scope.subredit).then(
            function successCallback(response) {
                $rootScope.images = response.data;

            });
    };
    $rootScope.deleteAll = function () {
        ImageService.deleteAll().then(
                function successCallback() {
                    $scope.images = [];
                },
                function errorCallback(response) {
                    console.log(response);
                });
    };
});
