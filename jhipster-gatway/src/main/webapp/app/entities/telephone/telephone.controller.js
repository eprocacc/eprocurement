(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('TelephoneController', TelephoneController);

    TelephoneController.$inject = ['Telephone', 'TelephoneSearch'];

    function TelephoneController(Telephone, TelephoneSearch) {

        var vm = this;

        vm.telephones = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Telephone.query(function(result) {
                vm.telephones = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            TelephoneSearch.query({query: vm.searchQuery}, function(result) {
                vm.telephones = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
