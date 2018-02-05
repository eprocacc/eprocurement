(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('StateController', StateController);

    StateController.$inject = ['State', 'StateSearch'];

    function StateController(State, StateSearch) {

        var vm = this;

        vm.states = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            State.query(function(result) {
                vm.states = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            StateSearch.query({query: vm.searchQuery}, function(result) {
                vm.states = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
