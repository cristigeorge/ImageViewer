angular.module('RedditAlbum')
// Creating the Angular Controller
.controller('ImageController',  function($http, $scope, AuthService,ImageService) {

    console.log(AuthService.user);
    ImageService.findAll().then(function(images){
        console.log(images);
        $scope.images = images.data;
    });
    /*
     add image to user album
     */
    $scope.addImage = function (image) {
        console.log(image);
        $http.post("api/user/images", image);
    };
    /*
     delete image from db
     */
    $scope.deleteImage = function (image) {
        ImageService.delete(image).then(function () {
            var removeImage = $scope.images.indexOf(image);
            $scope.images.splice(removeImage, 1);
        })
    };

});
