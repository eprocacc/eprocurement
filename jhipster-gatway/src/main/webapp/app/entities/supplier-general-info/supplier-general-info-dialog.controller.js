(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierGeneralInfoDialogController', SupplierGeneralInfoDialogController);

    SupplierGeneralInfoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'SupplierGeneralInfo', 'Supplier', 'Address', 'Telephone'];

    function SupplierGeneralInfoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, SupplierGeneralInfo, Supplier, Address, Telephone) {
        var vm = this;

        vm.supplierGeneralInfo = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.suppliers = Supplier.query();
        vm.suppliergeneraltoaddresses = Address.query({filter: 'suppliergeneralinfo-is-null'});
        $q.all([vm.supplierGeneralInfo.$promise, vm.suppliergeneraltoaddresses.$promise]).then(function() {
            if (!vm.supplierGeneralInfo.supplierGeneralToAddress || !vm.supplierGeneralInfo.supplierGeneralToAddress.id) {
                return $q.reject();
            }
            return Address.get({id : vm.supplierGeneralInfo.supplierGeneralToAddress.id}).$promise;
        }).then(function(supplierGeneralToAddress) {
            vm.suppliergeneraltoaddresses.push(supplierGeneralToAddress);
        });
        vm.telephones = Telephone.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.supplierGeneralInfo.id !== null) {
                SupplierGeneralInfo.update(vm.supplierGeneralInfo, onSaveSuccess, onSaveError);
            } else {
                SupplierGeneralInfo.save(vm.supplierGeneralInfo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipstergatwayApp:supplierGeneralInfoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.expiryDate = false;
        vm.datePickerOpenStatus.renewedDate = false;
        vm.datePickerOpenStatus.submissionDate = false;
        vm.datePickerOpenStatus.approvaldate = false;
        vm.datePickerOpenStatus.emailOTPDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
