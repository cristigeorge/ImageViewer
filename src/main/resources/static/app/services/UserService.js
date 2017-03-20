/**
 * Created by LUL on 28.01.2017.
 */
angular.module('RedditAlbum')
// Creating the Angular Service for storing logged user details
    .service('UserService', function($http) {
        this.findAll = function () {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/api/users'
            });
        };
        this.save = function (user) {
            console.log(user);
            return $http({
                method: 'POST',
                data : user,
                url: 'register'
            });
        };
        this.delete = function (id) {
            return $http({
                method: 'DELETE',
                url: 'api/users/'+id
            });
        };
        this.edit = function (user) {
            return $http({
                method: 'PUT',
                data : user,
                url: 'api/users'
            });
        };
    });