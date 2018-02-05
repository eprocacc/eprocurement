(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('DigitalCertificateDetailController', DigitalCertificateDetailController);

    DigitalCertificateDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DigitalCertificate', 'Supplier'];

    function DigitalCertificateDetailController($scope, $rootScope, $stateParams, previousState, entity, DigitalCertificate, Supplier) {
        var vm = this;

        vm.digitalCertificate = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipstergatwayApp:digitalCertificateUpdate', function(event, result) {
            vm.digitalCertificate = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
