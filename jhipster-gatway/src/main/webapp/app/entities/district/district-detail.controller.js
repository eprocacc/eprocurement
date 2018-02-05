(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('DistrictDetailController', DistrictDetailController);

    DistrictDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'District', 'Taluq', 'State'];

    function DistrictDetailController($scope, $rootScope, $stateParams, previousState, entity, District, Taluq, State) {
        var vm = this;

        vm.district = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipstergatwayApp:districtUpdate', function(event, result) {
            vm.district = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
