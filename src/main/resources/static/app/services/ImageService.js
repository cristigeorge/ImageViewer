angular.module('RedditAlbum')
// Creating the Angular Service for storing logged user details
    .service('ImageService', function($http) {
        this.findAll = function(){
            console.log('from angular service');
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/api/images'
            });
        };

        this.delete = function (image) {
            console.log(image + ' ' + image.id);
            return $http({
                method: 'DELETE',
                url: 'http://localhost:8080/api/images/'+image.id
            });
        };
        this.deleteAll = function () {
            return $http({
                method: 'DELETE',
                url: 'http://localhost:8080/api/images'
            });
        };
        this.search = function(q,url){
            console.log('from angular service');
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/api/images/?q='+q+'&url='+url
            });
        };
    });
