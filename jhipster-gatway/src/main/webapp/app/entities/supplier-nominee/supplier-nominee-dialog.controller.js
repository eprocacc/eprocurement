(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierNomineeDialogController', SupplierNomineeDialogController);

    SupplierNomineeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'SupplierNominee', 'Supplier'];

    function SupplierNomineeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, SupplierNominee, Supplier) {
        var vm = this;

        vm.supplierNominee = entity;
        vm.clear = clear;
        vm.save = save;
        vm.suppliers = Supplier.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.supplierNominee.id !== null) {
                SupplierNominee.update(vm.supplierNominee, onSaveSuccess, onSaveError);
            } else {
                SupplierNominee.save(vm.supplierNominee, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipstergatwayApp:supplierNomineeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
