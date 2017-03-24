angular.module("weatherman-module",[])
		.controller("MainController", function($scope,$http)
			{
				$scope.getWeather = function(valid){
				    console.log(valid+","+$scope.weatherForm);
                    if(valid){
                        $http.get('/weather/v1/city/'+$scope.city+'/'+$scope.unit)
                            .then(function(result,error){
                                $scope.response = result.data;
                                $scope.error = error;
                         });
                    }
				}

				$scope.reset = function(){
                    $scope.response = null;
                    $scope.city = null;
                    $scope.unit = "imperial";
				}
		});