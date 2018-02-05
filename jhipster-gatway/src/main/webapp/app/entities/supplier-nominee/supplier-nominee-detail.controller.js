(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierNomineeDetailController', SupplierNomineeDetailController);

    SupplierNomineeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'SupplierNominee', 'Supplier'];

    function SupplierNomineeDetailController($scope, $rootScope, $stateParams, previousState, entity, SupplierNominee, Supplier) {
        var vm = this;

        vm.supplierNominee = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipstergatwayApp:supplierNomineeUpdate', function(event, result) {
            vm.supplierNominee = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
