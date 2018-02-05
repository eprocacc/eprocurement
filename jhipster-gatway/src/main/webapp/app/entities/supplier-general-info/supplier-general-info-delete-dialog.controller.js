(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierGeneralInfoDeleteController',SupplierGeneralInfoDeleteController);

    SupplierGeneralInfoDeleteController.$inject = ['$uibModalInstance', 'entity', 'SupplierGeneralInfo'];

    function SupplierGeneralInfoDeleteController($uibModalInstance, entity, SupplierGeneralInfo) {
        var vm = this;

        vm.supplierGeneralInfo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            SupplierGeneralInfo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
