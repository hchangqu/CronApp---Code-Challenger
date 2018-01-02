
    <div class="panel panel-default dados">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Dados do Funcionário </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.pessoa.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="unome">Nome</label>
	                        <div class="col-md-12">
	                            <input type="text" ng-model="ctrl.pessoa.nome" id="unome" class="username form-control input-sm" placeholder="Digite seu nome com no mínimo 2 caracteres." required ng-minlength="2"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="idade">Idade</label>
	                        <div class="col-md-12">
	                            <input type="text" ng-model="ctrl.pessoa.idade" id="idade" class="form-control input-sm" placeholder="Digite sua idade." required ng-pattern="ctrl.onlyIntegers"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="endereco">Endereço</label>
	                        <div class="col-md-12">
	                            <input type="text" ng-model="ctrl.pessoa.endereco" id="endereco" class="form-control input-sm" placeholder="Digite seu endereço com no mínimo 4 caracteres." required ng-minlength="4"/>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="cargo">Cargo</label>
	                        <div class="col-md-12">
	                            <input type="text" ng-model="ctrl.pessoa.cargo" id="cargo" class="form-control input-sm" placeholder="Digite seu cargo com no mínimo 4 caracteres." required ng-minlength="4"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatLeft">
	                        <input type="submit"  value="{{!ctrl.pessoa.id ? 'Adicionar' : 'Atualizar'}}" class="btn btn-primary btn-md" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-md" ng-disabled="myForm.$pristine">Limpar</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default lista">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Lista de Funcionários</span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>Id</th>
		                <th>Nome</th>
		                <th>Idade</th>
		                <th>Endereço</th>
		                <th>Cargo</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAll()">
		                <td>{{u.id}}</td>
		                <td>{{u.nome}}</td>
		                <td>{{u.idade}}</td>
		                <td>{{u.endereco}}</td>
		                <td>{{u.cargo}}</td>
		                <td><button type="button" ng-click="ctrl.editPessoa(u.id)" class="btn btn-success custom-width">Editar</button></td>
		                <td><button type="button" ng-click="ctrl.removePessoa(u.id)" class="btn btn-danger custom-width">Remover</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>