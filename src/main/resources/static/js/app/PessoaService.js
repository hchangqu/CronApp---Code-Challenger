'use strict';

angular.module('crudApp').factory('PessoaService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAll: loadAll,
                getAll: getAll,
                getPessoa: getPessoa,
                createPessoa: createPessoa,
                updatePessoa: updatePessoa,
                removePessoa: removePessoa
            };

            return factory;

            function loadAll() {
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAll(){
                return $localStorage.users;
            }

            function getPessoa(id) {
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createPessoa(pessoa) {
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, pessoa)
                    .then(
                        function (response) {
                            loadAll();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updatePessoa(pessoa, id) {
                var deferred = $q.defer();
                $http.put(urls.USER_SERVICE_API + id, pessoa)
                    .then(
                        function (response) {
                            loadAll();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removePessoa(id) {
                var deferred = $q.defer();
                $http.delete(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAll();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
        }
    ]);