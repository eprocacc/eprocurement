(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('TaluqDetailController', TaluqDetailController);

    TaluqDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Taluq', 'District'];

    function TaluqDetailController($scope, $rootScope, $stateParams, previousState, entity, Taluq, District) {
        var vm = this;

        vm.taluq = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipstergatwayApp:taluqUpdate', function(event, result) {
            vm.taluq = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
