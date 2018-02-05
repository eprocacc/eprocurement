(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('TelephoneDialogController', TelephoneDialogController);

    TelephoneDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Telephone', 'SupplierGeneralInfo'];

    function TelephoneDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Telephone, SupplierGeneralInfo) {
        var vm = this;

        vm.telephone = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.suppliergeneralinfos = SupplierGeneralInfo.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.telephone.id !== null) {
                Telephone.update(vm.telephone, onSaveSuccess, onSaveError);
            } else {
                Telephone.save(vm.telephone, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipstergatwayApp:telephoneUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.mobileOTPDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
