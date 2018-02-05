(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('DigitalCertificateDialogController', DigitalCertificateDialogController);

    DigitalCertificateDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DigitalCertificate', 'Supplier'];

    function DigitalCertificateDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DigitalCertificate, Supplier) {
        var vm = this;

        vm.digitalCertificate = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
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
            if (vm.digitalCertificate.id !== null) {
                DigitalCertificate.update(vm.digitalCertificate, onSaveSuccess, onSaveError);
            } else {
                DigitalCertificate.save(vm.digitalCertificate, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipstergatwayApp:digitalCertificateUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.termStartDate = false;
        vm.datePickerOpenStatus.termEndDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
