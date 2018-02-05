(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('DigitalCertificateDeleteController',DigitalCertificateDeleteController);

    DigitalCertificateDeleteController.$inject = ['$uibModalInstance', 'entity', 'DigitalCertificate'];

    function DigitalCertificateDeleteController($uibModalInstance, entity, DigitalCertificate) {
        var vm = this;

        vm.digitalCertificate = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DigitalCertificate.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
