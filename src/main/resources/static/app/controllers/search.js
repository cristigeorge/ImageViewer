

angular.module('RedditAlbum')
    .controller('SearchController', function($http, $scope,ImageService) {
        $scope.addImage = function (image) {
            console.log(image);
            $http.post("api/user/images/", image);
        };
        /*
         delete image from db
         */
        $scope.deleteImage = function (image) {
            console.log(image + ' ' + image.id);
            ImageService.delete(image)
                .then(
                    function successCallback() {
                        var removeImage = $scope.images.indexOf(image);
                        $scope.images.splice(removeImage, 1);
                    },
                    function errorCallback(response) {
                    });
        };
});