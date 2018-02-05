(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('TaluqDialogController', TaluqDialogController);

    TaluqDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Taluq', 'District'];

    function TaluqDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Taluq, District) {
        var vm = this;

        vm.taluq = entity;
        vm.clear = clear;
        vm.save = save;
        vm.districts = District.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.taluq.id !== null) {
                Taluq.update(vm.taluq, onSaveSuccess, onSaveError);
            } else {
                Taluq.save(vm.taluq, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipstergatwayApp:taluqUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
