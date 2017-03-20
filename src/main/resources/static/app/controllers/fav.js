/**
 * Created by LUL on 28.01.2017.
 */
angular.module('RedditAlbum')
.controller('FavController', function($http, $scope) {
    $scope.images = [];
    /*
     fetch data from sub
     */
    /*
     get all images from user
     */
    $http.get("api/user/images").then(
        function successCallback(response) {
            $scope.images = response.data;
            angular.forEach($scope.images, function (value, key, obj) {
                obj[key].date_added = new Date(value.date_added);
            });
            console.log($scope.images);
        });
    /*
     delete image from user
     */
    $scope.deleteImage = function (image) {
        console.log(image + ' ' + image.id);
        $http.delete("api/user/images/" + image.id)
            .then(
                function successCallback(response) {
                    var removeImage = $scope.images.indexOf(image);
                    $scope.images.splice(removeImage, 1);
                },
                function errorCallback(response) {
                });
    };
})