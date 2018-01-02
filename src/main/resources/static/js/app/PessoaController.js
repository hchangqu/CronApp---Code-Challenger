'use strict';

angular.module('crudApp').controller('PessoaController',
    ['PessoaService', '$scope',  function( PessoaService, $scope) {

        var self = this;
        self.pessoa = {};
        self.pessoas=[];

        self.submit = submit;
        self.getAll = getAll;
        self.createPessoa = createPessoa;
        self.updatePessoa = updatePessoa;
        self.removePessoa = removePessoa;
        self.editPessoa = editPessoa;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            if (self.pessoa.id === undefined || self.pessoa.id === null) {
                createPessoa(self.pessoa);
            } else {
                updatePessoa(self.pessoa, self.pessoa.id);
            }
        }

        function createPessoa(pessoa) {
            PessoaService.createPessoa(pessoa)
                .then(
                    function (response) {
                        self.successMessage = 'Funcionário criado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        self.pessoa={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        self.errorMessage = 'Erro ao criar funcionário: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updatePessoa(pessoa, id){
            PessoaService.updatePessoa(pessoa, id)
                .then(
                    function (response){
                        self.successMessage='Funcionário atualizado com sucesso';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        self.errorMessage='Erro ao atualizar funcionário:  '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }

        function removePessoa(id){
            PessoaService.removePessoa(id)
                .then(
                    function(){
                    },
                    function(errResponse){
                        console.error('Erro ao remover funcionário: '+id +', Erro :'+errResponse.data);
                    }
                );
        }

        function getAll(){
            return PessoaService.getAll();
        }

        function editPessoa(id) {
            self.successMessage='';
            self.errorMessage='';
            PessoaService.getPessoa(id).then(
                function (pessoa) {
                    self.pessoa = pessoa;
                },
                function (errResponse) {
                	console.error('Erro ao remover funcionário: '+id +', Erro :'+errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.pessoa={};
            $scope.myForm.$setPristine(); 
        }
    }


    ]);