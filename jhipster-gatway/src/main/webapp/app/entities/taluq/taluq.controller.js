(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('TaluqController', TaluqController);

    TaluqController.$inject = ['Taluq', 'TaluqSearch'];

    function TaluqController(Taluq, TaluqSearch) {

        var vm = this;

        vm.taluqs = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Taluq.query(function(result) {
                vm.taluqs = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            TaluqSearch.query({query: vm.searchQuery}, function(result) {
                vm.taluqs = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
