angular.module('RedditAlbum', [ 'ui.router','ngResource' ])

.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
} ])
// the following method will run at the time of initializing the module. That
// means it will run only one time.


.run(function(AuthService, $rootScope, $state) {
	// For implementing the authentication with ui-router we need to listen the
	// state change. For every state change the ui-router module will broadcast
	// the '$stateChangeStart'.
	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		// checking the user is logged in or not
		if (!AuthService.user) {
			// To avoiding the infinite looping of state change we have to add a
			// if condition.
			if (toState.name != 'login' && toState.name != 'register') {
				event.preventDefault();
				$state.go('login');
			}
		} else {
			// checking the user is authorized to view the states
			if (toState.data && toState.data.role) {
				if (toState.data.role != AuthService.user.principal.role) {
					event.preventDefault();
					$state.go('access-denied');
				}
			}
		}
	});

});
