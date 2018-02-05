(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .controller('SupplierNomineeController', SupplierNomineeController);

    SupplierNomineeController.$inject = ['SupplierNominee', 'SupplierNomineeSearch'];

    function SupplierNomineeController(SupplierNominee, SupplierNomineeSearch) {

        var vm = this;

        vm.supplierNominees = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            SupplierNominee.query(function(result) {
                vm.supplierNominees = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            SupplierNomineeSearch.query({query: vm.searchQuery}, function(result) {
                vm.supplierNominees = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
