angular.module('RedditAlbum')
// Creating the Angular Controller
.controller('RegisterController', function($http, $scope, UserService) {
	$scope.submit = function() {
	    console.log($scope.appUser);
        UserService.save($scope.appUser).success(function() {
            $scope.appUser = null;
            $scope.confirmPassword = null;
            $scope.register.$setPristine();
            $scope.message = "Registration successfull !";
        }).error(function(error) {
            $scope.message = error.message;
        });
	};
});
