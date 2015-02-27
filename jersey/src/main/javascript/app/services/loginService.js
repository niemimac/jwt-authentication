app.service('loginService', function($http, $rootScope, $window) {
//delete $http.defaults.headers.common['X-Requested-With'];
this.login = function(credentials) {
    $http({
        method: 'GET',
        url: '/jersey/api/rest/login',
        params: {username: credentials.username, password: credentials.password}
//        headers: {'Authorization': 'Token token=xxxxYYYYZzzz'}
     }).success(function(authenticationToken){
    	 //$cookieStore.put('authToken', authenticationToken);
    	 $rootScope.authenticationToken = authenticationToken;
//    	 $window.sessionStorage.token = authenticationToken;
    }).error(function (data, status, headers, config) {
        // Erase the token if the user fails to login
//        delete $window.sessionStorage.token;
        $rootScope.authenticationToken = null;
        alert(error);
        $scope.message = 'Error: Invalid email or password';
    });
 }
this.logout = function(callbackFunc) {
	$http({
		method: 'POST',
		url: '/jersey/api/rest/logout'
//		headers: {'Authorization': $rootScope.authenticationToken}
	}).success(function(authenticationToken){
		//$cookieStore.put('authToken', authenticationToken);
//		delete $window.sessionStorage.token;
		$rootScope.authenticationToken = null;
	}).error(function(){
		alert("error");
	});
}
});