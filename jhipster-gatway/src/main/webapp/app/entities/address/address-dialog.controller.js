(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('AddressDialogController', AddressDialogController);

    AddressDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Address', 'State', 'Country'];

    function AddressDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Address, State, Country) {
        var vm = this;

        vm.address = entity;
        vm.clear = clear;
        vm.save = save;
        vm.states = State.query();
        vm.countries = Country.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.address.id !== null) {
                Address.update(vm.address, onSaveSuccess, onSaveError);
            } else {
                Address.save(vm.address, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipstergatwayApp:addressUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
