(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('TaluqDeleteController',TaluqDeleteController);

    TaluqDeleteController.$inject = ['$uibModalInstance', 'entity', 'Taluq'];

    function TaluqDeleteController($uibModalInstance, entity, Taluq) {
        var vm = this;

        vm.taluq = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Taluq.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
