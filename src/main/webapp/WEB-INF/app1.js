angular.module('postLogin', [])
    .controller('PostController', ['$scope', '$http', function($scope, $http) {        
            this.postForm = function() {
                var encodedString = 'username=' +
                    encodeURIComponent(this.inputData.username) +
                    '&password=' +
                    encodeURIComponent(this.inputData.password);
 
               
                
                 
            }
    }]);
