var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/CronAppCodeChallengerApplication',
    USER_SERVICE_API : 'http://localhost:8080/CronAppCodeChallengerApplication/api/pessoa/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/pagina',
                controller:'PessoaController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, PessoaService) {
                        var deferred = $q.defer();
                        PessoaService.loadAll().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

