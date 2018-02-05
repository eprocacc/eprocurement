(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierDetailController', SupplierDetailController);

    SupplierDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Supplier', 'Address', 'SupplierNominee', 'SupplierGeneralInfo'];

    function SupplierDetailController($scope, $rootScope, $stateParams, previousState, entity, Supplier, Address, SupplierNominee, SupplierGeneralInfo) {
        var vm = this;

        vm.supplier = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipstergatwayApp:supplierUpdate', function(event, result) {
            vm.supplier = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
