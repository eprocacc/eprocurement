(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierNomineeDeleteController',SupplierNomineeDeleteController);

    SupplierNomineeDeleteController.$inject = ['$uibModalInstance', 'entity', 'SupplierNominee'];

    function SupplierNomineeDeleteController($uibModalInstance, entity, SupplierNominee) {
        var vm = this;

        vm.supplierNominee = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            SupplierNominee.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
