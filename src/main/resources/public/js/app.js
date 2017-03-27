angular.module("weatherman-module",[])
		.controller("MainController", function($scope,$http)
			{
				$scope.getWeather = function(valid){
				    console.log(valid+","+$scope.weatherForm);
                    if(valid){
                        $http.get('/v1/city/'+$scope.city+'/'+$scope.unit)
                            .then(function(result,error){
                                $scope.response = result.data;
                                $scope.error = error;
                                console.log(result.data+","+error);
                         });
                    }
				}

				$scope.reset = function(){
                    $scope.response = null;
                    $scope.city = null;
                    $scope.unit = "imperial";
				}

				$scope.addCity = function(){
				    //alert("Add city clicked - "+$scope.response);
				    var res = $http.post('/v1/add/'+$scope.city,$scope.response);
				    res.success(function(status){
				        $scope.onload();
				    });
				}

                $scope.removePref = function(id){
                    var res = $http.delete('/v1/delete/'+id)
                                .then(
                                    function(result,error){
                                        $scope.onload();
                                    });
                }

				$scope.onload = function(){
		    	    //alert("onload call..");
		    	    $http.get('/v1/city/all')
		    	        .then(function(result,error){
		    	                $scope.prefResponse = result.data;
		    	                console.log(" pref city - "+result.data)
		    	            })
				}


		});