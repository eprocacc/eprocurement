(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('StateDialogController', StateDialogController);

    StateDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'State', 'District', 'Country'];

    function StateDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, State, District, Country) {
        var vm = this;

        vm.state = entity;
        vm.clear = clear;
        vm.save = save;
        vm.districts = District.query();
        vm.countries = Country.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.state.id !== null) {
                State.update(vm.state, onSaveSuccess, onSaveError);
            } else {
                State.save(vm.state, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipstergatwayApp:stateUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
