(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('StateDetailController', StateDetailController);

    StateDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'State', 'District', 'Country'];

    function StateDetailController($scope, $rootScope, $stateParams, previousState, entity, State, District, Country) {
        var vm = this;

        vm.state = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipstergatwayApp:stateUpdate', function(event, result) {
            vm.state = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
