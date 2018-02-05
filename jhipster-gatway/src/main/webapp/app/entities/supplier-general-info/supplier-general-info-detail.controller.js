(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierGeneralInfoDetailController', SupplierGeneralInfoDetailController);

    SupplierGeneralInfoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'SupplierGeneralInfo', 'Supplier', 'Address', 'Telephone'];

    function SupplierGeneralInfoDetailController($scope, $rootScope, $stateParams, previousState, entity, SupplierGeneralInfo, Supplier, Address, Telephone) {
        var vm = this;

        vm.supplierGeneralInfo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipstergatwayApp:supplierGeneralInfoUpdate', function(event, result) {
            vm.supplierGeneralInfo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
