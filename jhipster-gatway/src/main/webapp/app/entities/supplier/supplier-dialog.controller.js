(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierDialogController', SupplierDialogController);

    SupplierDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Supplier', 'Address', 'SupplierNominee', 'SupplierGeneralInfo'];

    function SupplierDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Supplier, Address, SupplierNominee, SupplierGeneralInfo) {
        var vm = this;

        vm.supplier = entity;
        vm.clear = clear;
        vm.save = save;
        vm.supplieraddresses = Address.query({filter: 'supplier-is-null'});
        $q.all([vm.supplier.$promise, vm.supplieraddresses.$promise]).then(function() {
            if (!vm.supplier.supplierAddress || !vm.supplier.supplierAddress.id) {
                return $q.reject();
            }
            return Address.get({id : vm.supplier.supplierAddress.id}).$promise;
        }).then(function(supplierAddress) {
            vm.supplieraddresses.push(supplierAddress);
        });
        vm.suppliernominees = SupplierNominee.query();
        vm.suppliergeneralinfos = SupplierGeneralInfo.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.supplier.id !== null) {
                Supplier.update(vm.supplier, onSaveSuccess, onSaveError);
            } else {
                Supplier.save(vm.supplier, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipstergatwayApp:supplierUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
