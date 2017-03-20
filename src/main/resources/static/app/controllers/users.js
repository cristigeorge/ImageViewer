angular.module('RedditAlbum')
// Creating the Angular Controller
.controller('UsersController', function($http, $scope, UserService) {
	var edit = false;
	$scope.buttonText = 'Create';
	var init = function() {
        UserService.findAll().success(function(res) {
			$scope.users = res;
			$scope.userForm.$setPristine();
			$scope.message='';
			$scope.appUser = null;
			$scope.buttonText = 'Create';
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	$scope.initEdit = function(appUser) {
		edit = true;
		$scope.appUser = appUser;
		$scope.message='';
		$scope.buttonText = 'Update';
	};
	$scope.initAddUser = function() {
		edit = false;
		$scope.appUser = null;
		$scope.userForm.$setPristine();
		$scope.message='';
		$scope.buttonText = 'Create';
	};
	$scope.deleteUser = function(appUser) {
        UserService.delete(appUser.id).success(function(res) {
			$scope.deleteMessage ="Success!";
			init();
		}).error(function(error) {
			$scope.deleteMessage = error.message;
		});
	};
	var editUser = function(){
        UserService.edit($scope.appUser).success(function(res) {
			$scope.appUser = null;
			$scope.confirmPassword = null;
			$scope.userForm.$setPristine();
			$scope.message = "Editting Success";
			init();
		}).error(function(error) {
			$scope.message =error.message;
            init();
		});
	};
    var addUser = function(){
        $http.post('api/users',$scope.appUser).success(function(res) {
            $scope.appUser = null;
            $scope.confirmPassword = null;
            $scope.userForm.$setPristine();
            $scope.message = "User Created";
            init();
        }).error(function(error) {
            $scope.message = error.message;
        });
    };
	$scope.submit = function() {
		if(edit){
			editUser();
		}else{
			addUser();	
		}
	};
	init();

});
