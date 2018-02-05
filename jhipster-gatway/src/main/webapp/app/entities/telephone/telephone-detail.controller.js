(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('TelephoneDetailController', TelephoneDetailController);

    TelephoneDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Telephone', 'SupplierGeneralInfo'];

    function TelephoneDetailController($scope, $rootScope, $stateParams, previousState, entity, Telephone, SupplierGeneralInfo) {
        var vm = this;

        vm.telephone = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipstergatwayApp:telephoneUpdate', function(event, result) {
            vm.telephone = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
